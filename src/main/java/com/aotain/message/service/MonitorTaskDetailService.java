package com.aotain.message.service;

import com.aotain.common.utils.tools.MonitorStatisticsUtils;
import com.aotain.message.dao.MonitorTaskDetailDao;
import com.aotain.message.entity.MonitorTaskDetail;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorTaskDetailService {
	/**
     * 写日志
     */
	private org.slf4j.Logger logger = LoggerFactory.getLogger(MonitorTaskDetailService.class);
	
	@Autowired
	private MonitorTaskDetailDao monitorTaskDetailDao;
	
	
	/**
	 * <pre>新增监控任务</pre>
	 * @return
	 */
	public boolean insertMonitorTaskDetail(MonitorTaskDetail monitorTaskDetail){
		try{
			monitorTaskDetailDao.insertMonitorTaskDetail(monitorTaskDetail);
		}
		catch(Exception e){
			logger.error("Failed to insert zf_v2_monitor_task_detail.monitorTaskDetail=" + monitorTaskDetail,e);
			MonitorStatisticsUtils.addEvent(e);
			return false;
		}
		return true;
	}
	
}
