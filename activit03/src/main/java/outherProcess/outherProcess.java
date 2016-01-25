package outherProcess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.h2.constant.SysProperties;
import org.junit.Test;

public class outherProcess {
	// 工作流引擎
	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();

	// 流程部署
	@Test
	public void bulidProcess() {
		Deployment de = pe.getRepositoryService().createDeployment()
				.name("排他网关")
				.addClasspathResource("diagrams/fechetoutherrocess.bpmn")
				.addClasspathResource("diagrams/fechetoutherrocess.png")
				.deploy();
		System.out.println("部署流程成功");
		System.out.println("部署id：" + de.getId());//701
		System.out.println("部署名称：" + de.getName());
		System.out.println("部署时间：" + de.getDeploymentTime());
	}

	// 启动流程
	@Test
	public void startProcess() {
		String key = "outher"; // 指的是fechetoutherrocess配置文件的流程id
		Map<String, Object> ove=new HashMap<String, Object>();
		ove.put("taskperson", "李森林");
		ProcessInstance process = pe.getRuntimeService()
				.startProcessInstanceByKey(key, ove);
		System.out.println("流程启动成功！");
		System.out.println("流程id：" + process.getId());//901

	}

	// 查询个人任务
	@Test
	public void showPersonTask() {
		String personname = "李森林";
		List<Task> tasklist = pe.getTaskService().createTaskQuery()
				.taskAssignee(personname).list();

		if (tasklist != null && tasklist.size() > 0) {
			for (Task task : tasklist) {
				System.out.println("任务id："+task.getId());
				System.out.println("任务执行人："+task.getAssignee());
				System.out.println("任务创建时间："+task.getCreateTime());
				System.out.println("流程实例id："+task.getProcessInstanceId());
				System.out.println("流程部署id："+task.getProcessDefinitionId());
			}
		}
	}
//	执行任务
	@Test
	public void completeTask(){
		String taskid="3004";
		Map<String, Object> ove=new HashMap<String, Object>();
		ove.put("money", 1100);
		pe.getTaskService().complete(taskid,ove);
		System.out.println("完成任务id："+taskid);
	}
	

}
