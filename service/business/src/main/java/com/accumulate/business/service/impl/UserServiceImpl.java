package com.accumulate.business.service.impl;

//import com.accumulate.business.config.JmsConfig;
import com.accumulate.business.entity.User;
import com.accumulate.business.mapper.UserMapper;
import com.accumulate.business.model.MyPage;
import com.accumulate.business.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import java.util.UUID;

/**
 * User 表数据服务层接口实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;

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

//    @Override
//    @RabbitListener(queues = "queue.name")
//    public void rabbitmqDirectReceive(Message message){
//        // 采用手动应答模式, 手动确认应答更为安全稳定
//        System.out.println("【receiveDirect1监听到消息】" + message);
//    }
//
//    @Override
//    public void rabbitmqDirectSender(){
//        String msg = "hello, 序号: ";
//        System.out.println("Producer, " + msg);
//        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.TEST_TOPIC_ROUTINGKEY, msg);
//    }

//    @Override
//    public void activitymqSender(){
//        Destination destination = new ActiveMQQueue("springboot.queue.test");
//        String msg = "activitymqDirectSender";
//        // 指定消息发送的目的地及内容
//        this.jmsTemplate.convertAndSend(destination,msg);
//    }
//
//    @JmsListener(destination ="springboot.queue.test")
//    public void activitymqReceive(String message) {
//        System.out.println("activitymqReceive接受到消息：" + message);
//        //TODO something
//    }

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
