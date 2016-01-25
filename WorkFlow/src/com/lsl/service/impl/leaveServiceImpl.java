package com.lsl.service.impl;

import java.util.List;

import com.lsl.dao.LeaveBillDao;
import com.lsl.entity.LeaveBill;
import com.lsl.service.LeaveService;

public class leaveServiceImpl implements LeaveService {

	private LeaveBillDao li;

	public LeaveBillDao getLi() {
		return li;
	}

	public void setLi(LeaveBillDao li) {
		this.li = li;
	}

	@Override
	public List<LeaveBill> findLeaveLIst() {
		return li.findLeaveLIst();
	}
	@Override
	public void deleteLeaveByID(Long id) {
		li.deleteLeaveByID(id);
	}

	@Override
	public void saveLeave(LeaveBill leave) {
		li.saveLeave(leave);
	}

	@Override
	public LeaveBill getleaveByID(Long id) {
return li.getleaveByID(id);		
	}

	@Override
	public void updateLeave(LeaveBill leave) {
		li.updateLeave(leave);
	}

	
}
