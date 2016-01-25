package com.lsl.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import com.lsl.entity.LeaveBill;
import com.lsl.web.WorkFlowAction;
import com.lsl.web.from.WorkFlowBean;



public interface IWorkflowService {

	void saveNewDeploye(File file, String filename);

	List<Deployment> findDeploymentList();

	List<ProcessDefinition> findProcessDefinitionList();

	InputStream findImageInputStream(String deploymentId, String imageName);


	void saveStartProcess(WorkFlowBean workflowBean);









	List<Task> findEmpTaskList(String name);

	

}
