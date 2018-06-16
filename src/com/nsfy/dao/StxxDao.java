package com.nsfy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nsfy.entity.Stxx3;

public interface StxxDao {
	public Stxx3 findByCaseNum(String caseNum);
	public Stxx3 findByCaseId(String Id);
	public boolean insertStxx(@Param("Stxx") Stxx3 stxx);
	
}
