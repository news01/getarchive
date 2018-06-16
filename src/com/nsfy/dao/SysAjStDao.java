package com.nsfy.dao;

import java.util.List;

import com.nsfy.entity.SysAjSt;


public interface SysAjStDao {
	public List<SysAjSt> findList(String ajid);
	public List<SysAjSt> findStid(SysAjSt ajSt);
	public int insert(SysAjSt ajSt);
	public int updateId(SysAjSt ajSt);
}
