package com.aotain.message;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.aotain.common.config.ContextUtil;
import com.aotain.common.utils.azkaban.AzkabanUtils;
import com.aotain.message.service.TaskStatusService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-base-message.xml" })
public class test_message {

	@Test
	public void test_1() {
		TaskStatusService taskStatusService = (TaskStatusService) ContextUtil.getContext().getBean("taskStatusService");
		System.out.println(taskStatusService);
	}

	@Test
	public void test_2() {

		try {
			AzkabanUtils azkabanUtils = new AzkabanUtils("http://192.168.5.101:8081", "");
			String loginReturn = azkabanUtils.loginAzkaban();
			Map loginReturnMap = (Map) JSON.parse(loginReturn);
			if (loginReturnMap.containsKey("status") && "success".equals(loginReturnMap.get("status"))) {
				String sessionId = (String) loginReturnMap.get("session.id");
				ResponseEntity<String>	m=azkabanUtils.deleteProject(sessionId, "basic_monitor_GD_ATKJ_IDC_20180110");
				int value = m.getStatusCode().value();
				System.out.println(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
