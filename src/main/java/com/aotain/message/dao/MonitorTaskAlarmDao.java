package com.aotain.message.dao;

import com.aotain.common.config.annotation.MyBatisDao;
import com.aotain.message.entity.MonitorTaskAlarm;
@MyBatisDao
public interface MonitorTaskAlarmDao {

	/**
	 * <pre>新增告警信息</pre>
	 */
	void insertMonitorTaskAlarm(MonitorTaskAlarm monitorTaskAlarm);
}
