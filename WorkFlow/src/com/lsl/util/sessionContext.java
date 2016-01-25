package com.lsl.util;

import org.apache.struts2.ServletActionContext;

import com.lsl.entity.Employee;

/**
 * session访问
 * @author 李森林
 *
 */
public class sessionContext {

	public static String LoginUserName="users";
	/**
	 * 获取当前登录用户
	 */
	public static Employee getEmp(){
		return 	(Employee) ServletActionContext.getRequest().getSession().getAttribute(LoginUserName);
	}
	/**
	 * 登录时存放登录信息到session
	 */
	public static void setEmp(Employee emp){
		ServletActionContext.getRequest().getSession().setAttribute(LoginUserName, emp);
	}
	/**
	 * 退出时删除用户session信息
	 */
	public static void removeEmp() {
		ServletActionContext.getRequest().getSession().removeAttribute(LoginUserName);
	}
	
	
}
