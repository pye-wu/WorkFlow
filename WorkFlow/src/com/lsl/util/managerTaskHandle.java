package com.lsl.util;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lsl.entity.Employee;
import com.lsl.service.EmpService;

public class managerTaskHandle implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		/**先获取登录用户，再获取当前用户的领导*/
		Employee emp=sessionContext.getEmp();
		//当前用户名
		String name=emp.getName();
//		使用当前用户名查询用户的详细信息
//		从web容器获取spring 容器
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		EmpService empservice=(EmpService) ac.getBean("es");
		emp=	empservice.findempByName(name);
		//设置个人任务的办理入
		delegateTask.setAssignee(emp.getManager().getName());
	}

}
