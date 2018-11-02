package com.aotain.message.dao;

import com.aotain.common.config.annotation.MyBatisDao;
import com.aotain.message.entity.MonitorTaskDetail;

/**
 * 
 * @author cym
 *
 */

@MyBatisDao
public interface MonitorTaskDetailDao {

	/**
	 * <pre>新增监控详情任务</pre>
	 * @return
	 */
	void insertMonitorTaskDetail(MonitorTaskDetail monitorTaskDetail);
}
