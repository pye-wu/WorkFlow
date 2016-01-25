package com.lsl.helloworld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class helloworld {

	//获取流程引擎
	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 部署流程定义
	 */
	@Test
	public void bulidProcess(){
//		RepositoryService	管理流程定义
	Deployment deploy = pe.getRepositoryService()//与流程定义和部署对象相关的service
						  .createDeployment()//创建一个部署对象
						  .name("又他妈的请假") //创建部署的流程名称
						  .addClasspathResource("diagrams/process.bpmn")//加载相关的资源
						  .addClasspathResource("diagrams/process.png")
						  .deploy();//完成部署
	System.out.println("部署id"+deploy.getId());
	System.out.println("部署名称"+deploy.getName());	
	}
	
	
	/**
	 * 启动流程实例
	 */
	@Test
	public void startprocess(){
		String processDefineKey="leave";
		Map<String,Object> o=new HashMap<String, Object>();
		o.put("name", "李森林");
		ProcessInstance processObject = pe.getRuntimeService()//与正在执行的流程实例和执行对象相关的service
//		.startProcessInstanceByKey(processDefineKey);//使用流程定义的key启动流程实例（process.bpmn的id ）
				.startProcessInstanceByKey(processDefineKey, o);
		System.out.println("流程实例的id："+processObject.getId());//流程实例id
		System.out.println("流程定义的id："+processObject.getProcessDefinitionId());//流程定义id
	}
	
	
	/**
	 * 查询当前人的个人任务
	 */
	@Test
	public void findMyTesk(){
		String arg0="张三";
		List<Task> tasklist=pe.getTaskService()//与正在执行的任务管理的相关的service
			.createTaskQuery()//创建任务查询对象
			.taskAssignee(arg0)//指定个人任务查询
			.list();
		if(tasklist!=null&&tasklist.size()>=0){
			for (int i = 0; i < tasklist.size(); i++) {
				System.out.println("任务id："+tasklist.get(i).getId());
				System.out.println("任务名称："+tasklist.get(i).getName());
				System.out.println("任务创建时间："+tasklist.get(i).getCreateTime());
				System.out.println("任务办理人名称："+tasklist.get(i).getAssignee());
				System.out.println("流程实例的id："+tasklist.get(i).getAssignee());
				System.out.println("执行对象的id："+tasklist.get(i).getExecutionId());
				System.out.println("流程定义id："+tasklist.get(i).getProcessDefinitionId());
				
			}
		}
	}
	
	/**
	 * 完成我的任务
	 */
	@Test
	public void completemyTask(){
		//任务id
		String taskid="5102";
//		Map<String,Object> o=new HashMap<String, Object>();
//		o.put("money", 440);
		pe.getTaskService().complete(taskid);
		System.out.println("完成任务id:"+taskid);
	}
	
	
	
	
	
}
