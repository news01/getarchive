package com.nsfy.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsfy.dao.SysAjStDao;
import com.nsfy.entity.SysAjSt;
import com.nsfy.service.SysAjStService;
import com.nsfy.util.SqlSessionUtil;

@Controller
public class SysAjStController {
	
	private SysAjStService service = new SysAjStService();
	
	/*@RequestMapping(value="test")
	@ResponseBody
	public String test(){
		List<SysAjSt> list = service.findList();
		System.out.println(list.size());
		return "ok";
		
	}*/
}
