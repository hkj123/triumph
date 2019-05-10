package com.accumulate.business.service;

import com.accumulate.business.entity.JobOperateLog;
import com.accumulate.business.model.JobAndTrigger;
import com.accumulate.business.model.MyPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface JobOperateLogService extends IService<JobOperateLog> {

	MyPage<JobAndTrigger> getJobAndTriggerDetails(MyPage<JobOperateLog> myPage);
}