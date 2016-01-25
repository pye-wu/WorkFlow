package com.lsl.service;

import com.lsl.entity.Employee;

public interface EmpService {
	Employee login(Employee emp);

	Employee findempByName(String name);
}
