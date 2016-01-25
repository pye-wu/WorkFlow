package com.lsl.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lsl.dao.LeaveBillDao;
import com.lsl.entity.LeaveBill;
import com.lsl.util.sessionContext;

public class LeaveBillDaoImpl extends HibernateDaoSupport implements LeaveBillDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<LeaveBill> findLeaveLIst() {
		return getHibernateTemplate().find("from LeaveBill where user.id=?", sessionContext.getEmp().getId());
	}

	@Override
	public void deleteLeaveByID(Long id) {
		getHibernateTemplate().delete(getleaveByID(id));
	}

	@Override
	public void saveLeave(LeaveBill leave) {
		getHibernateTemplate().save(leave);
	}

	@Override
	public LeaveBill getleaveByID(Long id) {
		return getHibernateTemplate().get(LeaveBill.class, id);
	}

	@Override
	public void updateLeave(LeaveBill leave) {
			getHibernateTemplate().update(leave);
	}

}
