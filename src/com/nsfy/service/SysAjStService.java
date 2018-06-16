package com.nsfy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.nsfy.dao.SysAjStDao;
import com.nsfy.entity.SysAjSt;
import com.nsfy.util.SqlSessionUtil;

public class SysAjStService implements SysAjStDao {

	private SqlSession session;

	@Override
	public List<SysAjSt> findList(String ajid) {
		// TODO Auto-generated method stub
		session = SqlSessionUtil.newSqlSession();
		List<SysAjSt> list = new ArrayList<SysAjSt>();
		try {
			list = session.selectList("findList", ajid);
			if (list.size() == 0) {
				SysAjSt s = new SysAjSt();
				s.setAjid(ajid);
				s.setStid("1");
				list.add(s);
			} else {
				SysAjSt s = new SysAjSt();
				s.setAjid(ajid);
				String lastSt = list.get(list.size() - 1).getStid();
				int i = list.size() - 1;
				while (list.get(i) == null) {
					i--;
				}

				String newSt = (Integer.parseInt(lastSt) + 1) + "";
				s.setStid(newSt);
				list.add(s);
			}
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<SysAjSt> findStid(SysAjSt ajSt) {
		// TODO Auto-generated method stub
		session = SqlSessionUtil.newSqlSession();
		List<SysAjSt> stids = new ArrayList<SysAjSt>();
		if (ajSt.getStid() == null || ajSt.getStid().equals(null) || ajSt.getStid() == ""
				|| ajSt.getStid().equals("")) {
			return null;
		} else {
			try {

				stids = session.selectList("findStid", ajSt);
			} finally {
				session.close();
			}
			return stids;
		}
	}

	@Override
	public int insert(SysAjSt ajSt) {
		// TODO Auto-generated method stub
		session = SqlSessionUtil.newSqlSession();
		int a = 0;
		try {
			a = session.insert("insert", ajSt);
		} finally {
			if (a != 0) {
				session.commit();
			}
			session.close();
		}
		return a;
	}

	@Override
	public int updateId(SysAjSt ajSt) {
		// TODO Auto-generated method stub
		session = SqlSessionUtil.newSqlSession();
		int a = 0;
		try {
			if (a != 0) {
				session.commit();
			}
			a = session.update("updateId", ajSt);
		} finally {
			session.close();
		}
		return a;
	}

	public List<SysAjSt> findList2(String ajid) {
		session = SqlSessionUtil.newSqlSession();
		List<SysAjSt> list = new ArrayList<SysAjSt>();
		try {
			list = session.selectList("findList", ajid);
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return list;
	}

}
