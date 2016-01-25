package com.lsl.dao;

import java.util.List;

import com.lsl.entity.LeaveBill;

public interface LeaveBillDao {

	List<LeaveBill> findLeaveLIst();

	void deleteLeaveByID(Long id);

	void saveLeave(LeaveBill leave);

	LeaveBill getleaveByID(Long id);

	void updateLeave(LeaveBill leave);

}
