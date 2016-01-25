package com.lsl.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lsl.dao.EmpDao;
import com.lsl.entity.Employee;

public class EmpDaoImpl extends HibernateDaoSupport implements EmpDao{

	
	@Override
	public Employee selectNameExits(Employee emp) {
		List<Employee> elist=  getHibernateTemplate() .find("from Employee where name=?", emp.getName());
		if(elist!=null&&elist.size()>0){
			return elist.get(0);
		}
		return null;
	}

	@Override
	public Employee findempByName(String name) {
		List<Employee> elist=  getHibernateTemplate() .find("from Employee where name=?", name);
		if(elist!=null&&elist.size()>0){
			return elist.get(0);
		}
		return null;
	}


}
