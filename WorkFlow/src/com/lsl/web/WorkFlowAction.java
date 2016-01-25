package com.lsl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;

import com.lsl.service.IWorkflowService;
import com.lsl.service.impl.WorkflowServiceImpl;
import com.lsl.util.ValueContext;
import com.lsl.util.sessionContext;
import com.lsl.web.from.WorkFlowBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class WorkFlowAction  extends ActionSupport implements ModelDriven<WorkFlowBean>{

	private static final long serialVersionUID = -644958768473207482L;

// 业务模型bean
	private WorkFlowBean wfbBean;
	
//	业务处理service
	private IWorkflowService wsi;
	/**
	 * 部署流程
	 * @return
	 * @throws FileNotFoundException 
	 */
	public String bulid() throws FileNotFoundException{
		
		wsi.saveNewDeploye(wfbBean.getFile(), wfbBean.getFilename());
//		List<ProcessDefinition> dlist = wsi.findProcessDefinitionList();
//		System.out.println(dlist.size());
		return SUCCESS;
	}
	/**
	 * startProcess 开始流程
	 */
	public String startProcess(){
		//流程关联业务（更新请假状态，启动流程实例，让启动的流程实例关联业务）
		wsi.saveStartProcess(wfbBean);
		
		return "list";
	}
	
	public String listTask(){
		List<Task> tlist=wsi.findEmpTaskList(sessionContext.getEmp().getName());
		ValueContext.putValueContext("tasklist", tlist);
		return SUCCESS;
	}
	
	
	
	
	
	public IWorkflowService getWsi() {
		return wsi;
	}
	public void setWsi(IWorkflowService wsi) {
		this.wsi = wsi;
	}
	public WorkFlowBean getWfbBean() {
		return wfbBean;
	}
	public void setWfbBean(WorkFlowBean wfbBean) {
		this.wfbBean = wfbBean;
	}
	@Override
	public WorkFlowBean getModel() {
		if(wfbBean==null)wfbBean=new WorkFlowBean();
		return wfbBean;
	}
	
}
