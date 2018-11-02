package com.aotain.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aotain.common.config.ContextUtil;
import com.aotain.message.service.TaskStatusService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-base-message.xml" })
public class test_deleteAzkabanProject_2004 {
	
	@Test
	public void test2(){
		TaskStatusService taskStatusService = (TaskStatusService) ContextUtil.getContext().getBean("taskStatusService");
		String message = "{\"content\":{\"createtime\":\"2018-01-17 18:32:18\",\"projectname\":\"UrlDuplicate_export_720\",\"projectid\":\"760\",\"azkabanurl\":\"http://192.168.5.101:8081\",\"flowname\":\"UrlDuplicate_export_718_end\",\"execid\":\"4837\"},\"createip\":\"server-2/192.168.50.202\",\"createtime\":1516185138,\"expiretime\":0,\"interval\":1800,\"maxtimes\":1,\"nexttime\":1516186938,\"status\":4,\"taskid\":2389456,\"tasktype\":5,\"times\":1,\"toptaskid\":2389455}";
		System.out.println(taskStatusService);
		taskStatusService.execute(message, 1515631867L,"");
	}
	
}
