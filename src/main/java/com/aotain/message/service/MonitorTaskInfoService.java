package com.aotain.message.service;

import com.aotain.common.utils.tools.MonitorStatisticsUtils;
import com.aotain.message.dao.MonitorTaskInfoDao;
import com.aotain.message.entity.DpiV1PolicyStatus;
import com.aotain.message.entity.MonitorTaskInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorTaskInfoService {
    /**
     * 写日志
     */
    private static Logger logger = Logger.getLogger(MonitorTaskInfoService.class);

    @Autowired
    private MonitorTaskInfoDao monitorTaskInfoDao;

    /**
     * <pre>新增监控任务</pre>
     *
     * @return
     */
    public boolean insertMonitorTask(MonitorTaskInfo monitorTaskInfo) {
        try {
            monitorTaskInfoDao.insertMonitorTask(monitorTaskInfo);
        } catch (Exception e) {
            logger.error("Failed to insert zf_v2_monitor_task_info.monitorTask=" + monitorTaskInfo, e);
            MonitorStatisticsUtils.addEvent(e);
            return false;
        }
        return true;
    }

    /**
     * <pre>修改监控任务</pre>
     *
     * @return
     */
    public boolean updateMonitorTask(MonitorTaskInfo monitorTaskInfo) {
        try {
            monitorTaskInfoDao.updateMonitorTask(monitorTaskInfo);
        } catch (Exception e) {
            logger.error("Failed to update zf_v2_monitor_task_info.monitorTask=" + monitorTaskInfo, e);
            MonitorStatisticsUtils.addEvent(e);
            return false;
        }
        return true;
    }

    /**
     * <pre>新增策略状态</pre>
     *
     * @return
     */
    public boolean insertPolicyStatus(DpiV1PolicyStatus dpiV1PolicyStatus) {
        try {
            monitorTaskInfoDao.insertPolicyStatus(dpiV1PolicyStatus);
        } catch (Exception e) {
            logger.error("Failed to insert zf_v2_policy_status.dpiV1PolicyStatus=" + dpiV1PolicyStatus.toString(), e);
            MonitorStatisticsUtils.addEvent(e);
            return false;
        }
        return true;
    }

    /**
     * <pre>更新策略状态</pre>
     *
     * @return
     */
    public int updatePolicyStatus(DpiV1PolicyStatus dpiV1PolicyStatus) {
        try {
            int ret = monitorTaskInfoDao.updatePolicyStatus(dpiV1PolicyStatus);
            return ret;
        } catch (Exception e) {
            logger.error("Failed to update zf_v2_policy_status.dpiV1PolicyStatus=" + dpiV1PolicyStatus.toString(), e);
            MonitorStatisticsUtils.addEvent(e);
            return -1;
        }

    }
}
