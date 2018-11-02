package com.aotain.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aotain.common.config.ContextUtil;
import com.aotain.message.service.TaskStatusService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-base-message.xml" })
public class test_job_2004 {


	@Test
	public void test1(){
		TaskStatusService taskStatusService = (TaskStatusService) ContextUtil.getContext().getBean("taskStatusService");
	//	String message = \"{\\"content\\":{\\"createtime\\":1515631867,\\"toptaskid\\":0,\\"createip\\":\\"DPI-01/192.168.5.101\\",\\"jobtype\\":2004,\\"isretry\\":0,\\"taskid\\":2361695,\\"params\\":\\"[{\\\\"operationType\\\\":2,\\\\"reportData\\\\":{\\\\"houseids\\\\":[\\\\"823\\\\"],\\\\"idcid\\\\":\\\\"A2.B1.B2-20090001\\\\"},\\\\"report_type\\\\":2}]\\"},\\"createip\\":\\"DPI-01/192.168.5.101\\",\\"createtime\\":1515631867,\\"expiretime\\":0,\\"interval\\":60,\\"maxtimes\\":3,\\"nexttime\\":1515631927,\\"status\\":4,\\"taskid\\":2361695,\\"tasktype\\":1,\\"times\\":1,\\"toptaskid\\":0}\";
	//	System.out.println(taskStatusService);
	//	taskStatusService.execute(message, 1515631867L);
	}
	
	@Test
	public void test2(){
		TaskStatusService taskStatusService = (TaskStatusService) ContextUtil.getContext().getBean("taskStatusService");
		String message = "{\"content\":{\"messageNo\":68,\"messageType\":209,\"port\":60000,\"messageSequenceNo\":287,\"probeType\":1,\"deploySiteName\":\"I-TJ-GD_ATKJ_IDC\",\"idcHouseIds\":[\"GD_ATKJ_IDC\"],\"ip\":\"192.168.5.100\",\"operationType\":1,\"devName\":\"aaa\"},\"createip\":\"HP-PC/192.168.4.224\",\"createtime\":1516156409,\"expiretime\":0,\"interval\":30,\"maxtimes\":3,\"nexttime\":1516156436,\"status\":1,\"taskid\":2389014,\"tasktype\":4,\"times\":1,\"toptaskid\":0}";
		System.out.println(taskStatusService);
		taskStatusService.execute(message, 1515631867L,"");
	}
	
}
