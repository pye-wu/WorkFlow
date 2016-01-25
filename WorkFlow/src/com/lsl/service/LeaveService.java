package com.lsl.service;

import java.util.List;

import com.lsl.entity.LeaveBill;

public interface LeaveService {

	List<LeaveBill> findLeaveLIst();

	void deleteLeaveByID(Long id);

	void saveLeave(LeaveBill leave);

	LeaveBill getleaveByID(Long id);

	void updateLeave(LeaveBill leave);

}
