package com.lsl.service.impl;

import com.lsl.dao.EmpDao;
import com.lsl.entity.Employee;
import com.lsl.service.EmpService;

public class EmpServiceImpl implements  EmpService{

	private EmpDao ed;
	
	public Employee login(Employee emp){
		Employee e = ed.selectNameExits(emp);
	return 	e;
		
	}

	public EmpDao getEd() {
		return ed;
	}

	public void setEd(EmpDao ed) {
		this.ed = ed;
	}

	@Override
	public Employee findempByName(String name) {
		// TODO Auto-generated method stub
		return ed.findempByName(name);
	}
	
	
	
}
