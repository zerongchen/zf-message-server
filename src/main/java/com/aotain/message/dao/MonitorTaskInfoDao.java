package com.aotain.message.dao;

import com.aotain.common.config.annotation.MyBatisDao;
import com.aotain.message.entity.DpiV1PolicyStatus;
import com.aotain.message.entity.MonitorTaskInfo;

@MyBatisDao
public interface MonitorTaskInfoDao {

	/**
	 * <pre>新增监控任务</pre>
	 * @return
	 */
	void insertMonitorTask(MonitorTaskInfo monitorTaskInfo);
	/**
	 * <pre>修改监控任务</pre>
	 * @return
	 */
	void updateMonitorTask(MonitorTaskInfo monitorTaskInfo);


	/**
	 * <pre>新增策略状态</pre>
	 * @return
	 */
	void insertPolicyStatus(DpiV1PolicyStatus dpiV1PolicyStatus);
	/**
	 * <pre>更新策略状态</pre>
	 * @return
	 */
	int updatePolicyStatus(DpiV1PolicyStatus dpiV1PolicyStatus);
}
