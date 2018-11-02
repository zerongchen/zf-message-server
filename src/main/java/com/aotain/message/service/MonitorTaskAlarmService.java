package com.aotain.message.service;

import com.aotain.common.utils.tools.MonitorStatisticsUtils;
import com.aotain.message.dao.MonitorTaskAlarmDao;
import com.aotain.message.entity.MonitorTaskAlarm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorTaskAlarmService {
	/**
     * 写日志
     */
	private Logger logger = LoggerFactory.getLogger(MonitorTaskAlarmService.class);
	@Autowired
	private MonitorTaskAlarmDao monitorTaskAlarmDao;
	
	
	/**
	 */
	public boolean insertMonitorTaskAlarm(MonitorTaskAlarm monitorTaskAlarm){
		try{
			monitorTaskAlarmDao.insertMonitorTaskAlarm(monitorTaskAlarm);
		}
		catch(Exception e){
			MonitorStatisticsUtils.addEvent(e);
			logger.error("Failed to insert zf_v2_monitor_task_alarm.monitorTaskAlarm=" + monitorTaskAlarm.toString(),e);
			return false;
		}
		return true;
	}
	
}
