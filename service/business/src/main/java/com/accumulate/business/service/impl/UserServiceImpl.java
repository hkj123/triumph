package com.accumulate.business.service.impl;

import com.accumulate.business.config.RabbitConfig;
import com.accumulate.business.entity.User;
import com.accumulate.business.mapper.UserMapper;
import com.accumulate.business.model.JobAndTrigger;
import com.accumulate.business.model.MyPage;
import com.accumulate.business.model.ParamSome;
import com.accumulate.business.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * User 表数据服务层接口实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public MyPage<User> findUserPage(MyPage<User> myPage) {
        return baseMapper.findUserPage(myPage);
    }

    @Override
    public String createToken(long userId) {
        String token = UUID.randomUUID().toString().replace("-", "");
        String valiToken = String.format("%s_%s",userId,token);
        return valiToken;
    }

    @Override
    @RabbitListener(queues = "queue.name")
    public void directReceive(Message message){
        // 采用手动应答模式, 手动确认应答更为安全稳定
        System.out.println("【receiveDirect1监听到消息】" + message);
    }

    @Override
    public void directSender(){
        String msg = "hello, 序号: ";
        System.out.println("Producer, " + msg);
        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.TEST_TOPIC_ROUTINGKEY, msg);
    }
//    @Override
//    public Long create(User user){
//        return baseMapper.create(user);
//    }

//	@Override
//	public boolean deleteAll() {
//		return retBool(baseMapper.deleteAll());
//	}
//
//	@Override
//	public List<User> selectListBySQL() {
//		return baseMapper.selectListBySQL();
//	}
//
//	@Override
//	public List<User> selectListByWrapper(Wrapper wrapper) {
//		return baseMapper.selectListByWrapper(wrapper);
//	}
//
//	@Override
//	public MyPage<User> pageFindUserModel(MyPage<User> myPage, ParamSome paramSome){
//       return baseMapper.mySelectPage(myPage,paramSome);
//	}
//
//	@Override
//	public Page<User> pageFindUserModel2(Page myPage, Wrapper<User> wrapper){
//		return baseMapper.mySelectPage2(myPage,wrapper);
//	}
}