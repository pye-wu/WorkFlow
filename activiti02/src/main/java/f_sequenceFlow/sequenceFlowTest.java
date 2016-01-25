package f_sequenceFlow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.sun.mail.handlers.message_rfc822;

public class sequenceFlowTest {

	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	
	//流程部署
	@Test
	public void processInstance(){
		Deployment d = pe.getRepositoryService()
		.createDeployment()
		.name("连线判断")
		.addClasspathResource("diagrams/sequenceFlow.bpmn")
		.addClasspathResource("diagrams/sequenceFlow.png")
		.deploy();
		System.out.println("流程部署id："+d.getId());//1
		System.out.println("流程部署名称："+d.getName());
		System.out.println("流程部署时间："+d.getDeploymentTime());
	}
//	启动流程
	@Test
	public void startprocessInstance(){   //101
		ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey("sequenceFlow");
		System.out.println("流程实例id"+pi.getId());
		System.out.println("流程部署id"+pi.getProcessDefinitionId());
		System.out.println("流程实例id"+pi.getProcessInstanceId());
	}
	//查询任务
	@Test
	public void checkmyTask(){//104
		String arg0="李四";
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
	
	//提交任务(条件)
	@Test
	public void completetask(){
		String taskId="503";
		Map<String, Object> variables=new HashMap<String, Object>();
		//完成任务的同时，保存流程变量，使用流程变量来制定走那条线
		variables.put("message", "重要");
		pe.getTaskService().complete(taskId, variables);
		System.out.println("完成任务id："+taskId);
		
	}
	//设置流程变量
	@Test
	public void setvar(){
		pe.getRuntimeService().setVariable("804", "ccc", "ccc");
		
	}
	//获取流程变量
	@Test
	public void setvar2(){
	String s=	(String) pe.getRuntimeService().getVariable("804", "abc");
	String ss=	(String) pe.getRuntimeService().getVariable("801", "ccc");
		System.out.println(s);
		System.out.println(ss);
	}
	//认领任务
	@Test
	public void getTask(){
		pe.getTaskService().claim("任务id", "fozzie");
		
	}
	
	
	 // 查看流程是否结束
	@Test
	public void checkFinish(){
		String procId="启动线程id";
		procId="5501";
		HistoricProcessInstance historicProcessInstance =
				pe.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();
		System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
		
	}
	//查看线程实例
	@Test
	public void getProcessInstance(){
		//查看正在运行的实例对象
		List<ProcessInstance> list = pe.getRuntimeService().createProcessInstanceQuery().list();
		//查看历史运行的实例对象
		List<HistoricProcessInstance> list1 = pe.getHistoryService().createHistoricProcessInstanceQuery().list();
		
		System.out.println(list1.size());
	}
	
	//通过部署id删除部署
	@Test
	public void deleteDepartment(){
		String deploymentId="流程定义id";
		deploymentId="";
		pe.getRepositoryService().deleteDeployment(deploymentId, true);
		
	}
	
	@Test
	public void findprocessInstance(){
		String deploymentId="301";
		Deployment d=pe.getRepositoryService().createDeploymentQuery().deploymentId(deploymentId).singleResult();
		System.out.println(d.getName());
		System.out.println(d.getId());
		
	}
	
}
