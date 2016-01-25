package com.lsl.web;

import com.lsl.entity.Employee;
import com.lsl.service.EmpService;
import com.lsl.util.sessionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EmpAction extends ActionSupport implements ModelDriven<Employee>{

	private static final long serialVersionUID = 1L;
	
	private Employee emp=new Employee();
	private EmpService es;

		public String login(){
			System.out.println("访问login");
			emp=es.login(emp);
			if(emp!=null){
				sessionContext.setEmp(emp);
			}
			return SUCCESS;
		}
		
		public String loginOut(){
			sessionContext.removeEmp();
			return "login";
		}
		
		
		
		@Override
		public Employee getModel() {
			if(emp==null){
				emp=new Employee();
			}
			return emp;
		}
		public Employee getEmp() {
			return emp;
		}

		public void setEmp(Employee emp) {
			this.emp = emp;
		}

		public EmpService getEs() {
			return es;
		}

		public void setEs(EmpService es) {
			this.es = es;
		}

	
	
	
	
	
}
