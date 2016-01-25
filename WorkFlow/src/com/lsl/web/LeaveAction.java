package com.lsl.web;

import java.util.List;

import com.lsl.entity.LeaveBill;
import com.lsl.service.LeaveService;
import com.lsl.util.ValueContext;
import com.lsl.util.sessionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class LeaveAction  extends ActionSupport implements ModelDriven<LeaveBill>{

	
	private static final long serialVersionUID = -3726685214490899502L;
	private LeaveBill leave;
	private LeaveService ls;
	
	public String saveleave(){
	Long id=	leave.getId();
		if(id==0){
		leave.setUser(sessionContext.getEmp());
		ls.saveLeave(leave);
		}else{
			ls.updateLeave(leave);
		}
		return "list";
	}
//	获取请假列表
	public String getleaveList(){
		List<LeaveBill> fl = ls.findLeaveLIst();
		ValueContext.putValueContext("leavelist", 	fl);
		return SUCCESS;
	}
	
	public String deleteleaveById(){
		ls.deleteLeaveByID(leave.getId());
		return "list";
	}
	public String getleaveByID(){
	leave=	ls.getleaveByID(leave.getId());
		return SUCCESS;
	}
public String updateleave(){
		
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public LeaveBill getLeave() {
		return leave;
	}

	public void setLeave(LeaveBill leave) {
		this.leave = leave;
	}

	public LeaveService getLs() {
		return ls;
	}

	public void setLs(LeaveService ls) {
		this.ls = ls;
	}

	@Override
	public LeaveBill getModel() {
		if(leave==null)leave=new LeaveBill();
		return leave;
	}
	
	
}
