package com.lsl.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;

import com.lsl.entity.LeaveBill;
import com.lsl.service.IWorkflowService;
import com.lsl.service.LeaveService;
import com.lsl.util.sessionContext;
import com.lsl.web.WorkFlowAction;
import com.lsl.web.from.WorkFlowBean;

public class WorkflowServiceImpl implements IWorkflowService {
	
	private RepositoryService repositoryService;
	
	private RuntimeService runtimeService;
	
	private TaskService taskService;
	
	private FormService formService;
	
	private HistoryService historyService;
	private LeaveService ls;
	@Override
	public List<Task> findEmpTaskList(String name) {
		return taskService.createTaskQuery().taskAssignee(name).list();
		
	}

	/**
	 * 上传部署新流程
	 */
	@Override
	public void saveNewDeploye(File file, String filename) {
		try {
			ZipInputStream zis=new ZipInputStream(new FileInputStream(file));
			Deployment de = repositoryService.createDeployment()
											.addZipInputStream(zis)
											.name(filename)
											.deploy();
			System.out.println("流程部署成功!");
			System.out.println("部署id："+de.getId());
			System.out.println("部署名称："+de.getName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	/**
	 * 查询所有的部署流程
	 */
	@Override
	public List<Deployment> findDeploymentList() {
		List<Deployment> dlist = repositoryService.createDeploymentQuery()
										.list();
		return dlist;
	}
	
	/**
	 * 查询所有的流程定义对象
	 */
	@Override
	public List<ProcessDefinition> findProcessDefinitionList() {
		return repositoryService.createProcessDefinitionQuery().list();
	}
	@Override
	public void saveStartProcess(WorkFlowBean workflowBean) {
			//	通过业务id获取实例，修改状态值    （使用快照的方式修改状态，在service方法结束后提交事务持久化）
			LeaveBill leave = ls.getleaveByID(workflowBean.getId());
			leave.setState(1);
			//启动流程实例    设置流程开始人（登录用户）
			String inputusers=sessionContext.getEmp().getName();
			Map<String,Object> mo=new HashMap<String, Object>();
			mo.put("inputusers", inputusers);
			String taskkey=LeaveBill.class.getSimpleName();
			//设置流程关联id  格式leaveBil.id 格式（指定什么类型的id） 
//			使用正在执行对象表的一个字段BUSINESS_KEY ，让启动的流程（流程实例）关联业务（leavebill对象）
			String objid=taskkey+"."+workflowBean.getId();
			mo.put("objid", objid);
			//使用流程定义的key，启动流程实例，并且设置流程变量（用户）
			runtimeService.startProcessInstanceByKey(taskkey,objid,mo);
			
			
			
	}
	
	public LeaveService getLs() {
		return ls;
	}
	public void setLs(LeaveService ls) {
		this.ls = ls;
	}



	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	@Override
	public InputStream findImageInputStream(String deploymentId,
			String imageName) {
		// TODO Auto-generated method stub
		return null;
	}

	


	
	
}
