package com.nsfy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nsfy.dao.StxxDao;
import com.nsfy.entity.Stxx;
import com.nsfy.entity.Stxx3;
import com.nsfy.util.SqlSessionUtil;

public class StxxService implements StxxDao {
	private SqlSession session;
	@Override
	public Stxx3 findByCaseNum(String caseNum) {
		// TODO Auto-generated method stub
		session = SqlSessionUtil.newSqlSession();
		Stxx3 st = new Stxx3();
		try{
			st = session.selectOne("findByCaseNum",caseNum);
		}finally {
			session.close();
		}
		return st;
	}
	@Override
	public boolean insertStxx(Stxx3 stxx) {
		// TODO Auto-generated method stub
		session = SqlSessionUtil.newSqlSession();
		boolean flag = false;
		int a = 0;
		try {
			a = session.insert("insertStxx", stxx) ;
		} finally {
			// TODO: handle finally clause
			if (a!=0) {
				flag = true;
				session.commit();
			}
		}
		return flag;
	}
	@Override
	public Stxx3 findByCaseId(String Id) {
		// TODO Auto-generated method stub
		session = SqlSessionUtil.newSqlSession();
		Stxx3 st = new Stxx3();
		try{
			st = session.selectOne("findByCaseId",Id);
		}finally {
			session.close();
		}
		return st;
	}

}
