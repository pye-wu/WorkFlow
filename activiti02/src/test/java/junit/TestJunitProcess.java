package junit;

import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class TestJunitProcess {

	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	
	//流程部署
	@Test
	public void processInstance(){

//		ZipInputStream is =new ZipInputStream(getClass().getResourceAsStream("/diagrams/diagrams.zip"));
		Deployment d = pe.getRepositoryService()
		.createDeployment()
		.addClasspathResource("diagrams/processinstance.bpmn")
		.addClasspathResource("diagrams/processinstance.png")
		.deploy();
		System.out.println("流程部署id："+d.getId());
		System.out.println("流程部署名称："+d.getName());
		System.out.println("流程部署时间："+d.getDeploymentTime());
	}
//	启动流程
	@Test
	public void startprocessInstance(){
		ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey("helloprocess");
		System.out.println("流程实例id"+pi.getId());
		System.out.println("流程部署id"+pi.getProcessDefinitionId());
		System.out.println("流程实例id"+pi.getProcessInstanceId());
	}
	//查询任务
	@Test
	public void checkmyTask(){
		String arg0="王五";
		List<Task> tasklist = pe.getTaskService().createTaskQuery().taskAssignee(arg0).list();
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
	
	//提交任务
	@Test
	public void completetask(){
		String taskId="1102";
		pe.getTaskService().complete(taskId);
		
	}
	@Test
	public void setvar(){
		pe.getRuntimeService().setVariable("804", "ccc", "ccc");
		
	}
	@Test
	public void setvar2(){
	String s=	(String) pe.getRuntimeService().getVariable("804", "abc");
	String ss=	(String) pe.getRuntimeService().getVariable("801", "ccc");
		System.out.println(s);
		System.out.println(ss);
	}
	
	
	
	
	@Test
	public void findprocessInstance(){
		String deploymentId="301";
		Deployment d=pe.getRepositoryService().createDeploymentQuery().deploymentId(deploymentId).singleResult();
		System.out.println(d.getName());
		System.out.println(d.getId());
		
	}
	
}
