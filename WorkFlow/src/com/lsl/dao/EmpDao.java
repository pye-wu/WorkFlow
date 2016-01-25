package com.lsl.dao;

import com.lsl.entity.Employee;

public interface EmpDao {

	Employee selectNameExits(Employee emp);

	Employee findempByName(String name);

	
	
}
