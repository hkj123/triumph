package com.accumulate.business.service.impl;

import com.accumulate.business.entity.JobOperateLog;
import com.accumulate.business.mapper.JobOperateLogMapper;
import com.accumulate.business.model.JobAndTrigger;
import com.accumulate.business.model.MyPage;
import com.accumulate.business.service.JobOperateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class JobOperateLogServiceImpl extends ServiceImpl<JobOperateLogMapper, JobOperateLog> implements JobOperateLogService {

	@Override
	public MyPage<JobAndTrigger> getJobAndTriggerDetails(MyPage<JobOperateLog> myPage) {
		return baseMapper.getJobAndTriggerDetails(myPage);
	}
}