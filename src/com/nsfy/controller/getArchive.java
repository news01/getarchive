package com.nsfy.controller;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsfy.entity.Stxx;
import com.nsfy.entity.Stxx2;
import com.nsfy.entity.Stxx3;
import com.nsfy.entity.SysAjSt;
import com.nsfy.service.StxxService;
import com.nsfy.service.SysAjStService;
import com.nsfy.util.FileUtil;
import com.nsfy.util.HttpClientUtil;
import com.nsfy.util.JSONUtil;
import com.nsfy.util.StringUtil;
import com.nsfy.util.ZxingHandler;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class getArchive {
	private static int barwith = StringUtil.barwith;
	private static int qrwith = StringUtil.qrwith;
	private static int qrhiegth = StringUtil.qrhiegth;
	private static int barhiegth = StringUtil.barhiegth;
	private static Logger logger = Logger.getLogger(getArchive.class);
	private SysAjStService service = new SysAjStService();
	private StxxService stxxService = new StxxService();

	@RequestMapping(value = "getArchive")
	public String get(@RequestParam(value = "ajid", required = true) String ajid, HttpServletRequest request,
			HttpSession session, Model model) {
		session.setAttribute("ajid", ajid);
		List<SysAjSt> list = service.findList(ajid);
		model.addAttribute("list", list);
		return "../face";
	}
	@RequestMapping(value="QrTest")
	public String QrTest(Model model){
		String str = "{\"book_num\":\"1\",\"pla\":\"官凡\",\"case_case\":\"租赁合同纠纷\",\"spcx\":\"10\",\"ajid\":\"2580161002001672\",\"page_num\":\"0\",\"stid\":\"1\",\"id\":\"1514469132739\",\"case_num\":\"（2016）粤0305执5537号\",\"defen\":\"深圳市太古广场物业管理有限公司\",\"all_books\":\"0\"}";
		model.addAttribute("content", str);
		return "../test";
	}
	@RequestMapping(value="loginName",produces="text/json;charset=utf-8")
	@ResponseBody
	public String getLoginName(@RequestParam(value="loginName",required = true) String loginName,HttpSession session){
		String content = (String) session.getAttribute("content");
		JSONObject json = new JSONObject();
		json.put("loginName", loginName);
		json.put("content", content);
		logger.info(json.toString());
		JSONObject result = HttpClientUtil.Post(HttpClientUtil.synSingleUrl, json);
		JSONObject jsonResult = new JSONObject();
		logger.info(result.toString());
		jsonResult.put("errCode", result.get("errCode"));
		jsonResult.put("errMsg", result.get("errMsg"));
		return jsonResult.toString();
	}
	@RequestMapping(value="loginNameXla")
	@ResponseBody
	public String getLoginNameXla(@RequestParam(value="loginName",required = true) String loginName,HttpSession session){
		String content = (String) session.getAttribute("content");
		JSONObject json = new JSONObject();
		json.put("loginName", loginName);
		json.put("content", content);
		logger.info(json.toString());
		JSONObject result = HttpClientUtil.Post(HttpClientUtil.synXlaUrl, json);
		logger.info(result.toString());
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("errCode", 0);
		jsonResult.put("errMsg","");
		return jsonResult.toString();
	}
	@RequestMapping(value = "print")
	public String getFace(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model,
			@RequestParam(value = "stid", required = false) String stid,
			@RequestParam(value = "xl", required = false) String xl) {
		String ajid = (String) session.getAttribute("ajid");
		if (xl == "0" || xl.equals("0")) {
			System.out.println(stid);
			System.out.println(ajid);
			SysAjSt ajSt = new SysAjSt();
			ajSt.setAjid(ajid);
			ajSt.setStid(stid);
			List<SysAjSt> list = service.findStid(ajSt);
			String id = "";
			if (list.size() == 0) {
				id = ajid + "" + stid;
				ajSt.setId(id);
				service.insert(ajSt);
			} else {
				id = list.get(0).getId();
			}
			String url = HttpClientUtil.url + "?ajid=" + ajid + "&&stid=" + stid;
			String j = new HttpClientUtil().Get(url);
			System.out.println(j);
			JSONObject json = JSONObject.fromObject(j);
			ObjectMapper objMapper = new ObjectMapper();
			Stxx stxx = new Stxx();
			try {
				stxx = objMapper.readValue(j, Stxx.class);
				System.out.println(stxx);
				stxx.setBookNum(stid);
				String pla = new StringUtil().tostr(stxx.getPla(), 1, stxx.getPlaRoleName());
				String pla2 = "";
				if (stxx.getPla().size() != 0) {
					pla2 = stxx.getPla().get(0);
				}

				String defen = new StringUtil().tostr(stxx.getDefen(), 2, stxx.getDefenRoleName());
				String defen2 = "";
				if (stxx.getDefen().size() != 0) {
					defen2 = stxx.getDefen().get(0);
				}
				String third = new StringUtil().tostr(stxx.getThird(), 3, stxx.getThirdRoleName());

				JSONObject QRContent = new JSONUtil().getFileJson(id, stxx.getCase_num(), stxx.getCaseCase(), pla2,
						defen2, stxx.getPageNum(), ajid, stid, stid, stxx.getAllBooks(),stxx.getSpcx());
				//将Content放入session中，方便之后传递到卷宗流转小程序中
				session.setAttribute("content", QRContent.toString());
				String imgPath = StringUtil.barpath + QRContent.getString("id") + "bar.png";
				String imgPath2 = StringUtil.qrpath + QRContent.getString("id") + "qr.png";
				new ZxingHandler();
				ZxingHandler.encode(id, barwith, barhiegth, imgPath);
				ZxingHandler.encode2(QRContent.toString(), qrwith, qrhiegth, imgPath2);
				List<SysAjSt> list2 = service.findList(ajid);
				FileInputStream bar = new FileInputStream(imgPath);
				FileInputStream qr = new FileInputStream(imgPath2);
				System.out.println("123:  " + list2.size());
				model.addAttribute("content", QRContent);
				model.addAttribute("stxx", stxx);
				model.addAttribute("id", QRContent.get("id"));
				model.addAttribute("stid", stid);
				model.addAttribute("pla", pla);
				model.addAttribute("defen", defen);
				model.addAttribute("third", third);
				model.addAttribute("list2", list2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "../face2";
		} else {
			String url = HttpClientUtil.xlurl + ajid;
			String str = new HttpClientUtil().Get(url);
			if (str == "[]" || str.equals("[]")) {
				System.out.println("-----------------");
				model.addAttribute("reqstate", null);
				model.addAttribute("ajid", ajid);
				model.addAttribute("stid", stid);
			} else {
				List<Stxx2> list = new ArrayList<>();
				JSONArray cases = JSONArray.fromObject(str);
				ObjectMapper objMapper = new ObjectMapper();
				String id = "";
				String barpath = "";
				String qrpath = "";
				System.out.println(cases.size());
				List<String> li = new ArrayList<>();
				for (int i = 0; i < cases.size(); i++) {
					Stxx2 stxx = new Stxx2();
					try {
						stxx = objMapper.readValue(cases.get(i).toString(), Stxx2.class);
						System.out.println("+++++++++++++" + stxx.getCase_num());
						if (stxx.getCase_num() == "" || stxx.getCase_num().equals("")) {
							System.out.println("---------------");

						} else {
							String pla = new StringUtil().tostr(stxx.getPla(), 1, stxx.getPlaRoleName());
							String defen = new StringUtil().tostr(stxx.getDefen(), 2, stxx.getDefenRoleName());
							String third = new StringUtil().tostr(stxx.getThird(), 3, stxx.getThirdRoleName());
							id = stxx.getAjid()+"1";
							stxx.setStid("1");
							stxx.setPla2(pla);
							stxx.setDefen2(defen);
							stxx.setThird2(third);
							stxx.setAjid(id);
							String pla2 = "";
							String defen2 = "";
							if (stxx.getPla().size() != 0) {
								pla2 = stxx.getPla().get(0);
							}
							if (stxx.getDefen().size() != 0) {
								defen2 = stxx.getDefen().get(0);
							}
							System.out.println(stxx.getCase_id());
							JSONObject content = new JSONUtil().getFileJson(id + "", stxx.getCase_num(),
									stxx.getCaseCase(), pla2, defen2, stxx.getPageNum(), stxx.getCase_id(), 1 + "",
									"1", stxx.getAllBooks(),stxx.getSpcx());
							barpath = StringUtil.barpath + id + "bar.png";
							qrpath = StringUtil.qrpath + id + "qr.png";
							ZxingHandler.encode2(content.toString(), qrwith, qrhiegth, qrpath);
							ZxingHandler.encode(id + "", barwith, barhiegth, barpath);
							li.add(content.toString());
							
							list.add(stxx);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}
				JSONArray ja = JSONArray.fromObject(li);
				session.setAttribute("content", ja.toString());
				model.addAttribute("ajid", ajid);
				model.addAttribute("stid", stid);
				model.addAttribute("reqstate", 1);
				model.addAttribute("cases", list);

			}
			return "../face3";
		}

	}

	@RequestMapping(value = "zxxx")
	public String Zxxx() {
		return "../zxxx";
	}
	@RequestMapping(value = "yi")
	public String yi(){
		return "../yi";
	}
	@RequestMapping(value = "getYsAjByCaseNum")
	public String getYsAjByCaseNum(HttpSession session,Model model,@RequestParam(value="year",required = true)String year,@RequestParam(value="lb",required=true)String lb,@RequestParam(value="xh",required=true)String xh){
		String caseNum = "（"+year+"）粤0305"+lb+xh+"号";
		logger.info("案号：==========>"+caseNum);
		String url = HttpClientUtil.ysurl+"?ah="+caseNum;
		String str = new HttpClientUtil().Get(url);
		logger.info("案件内容：============>"+str);
		System.out.println(str);
		ObjectMapper objMapper = new ObjectMapper();
		Stxx stxx = new Stxx();
		String ajid = "";
		String id = "";
		SysAjSt ajSt = new SysAjSt();
		ajid = stxx.getId();
		ajSt.setAjid(ajid);
		ajSt.setStid("1");
		List<SysAjSt> list = service.findStid(ajSt);
		if (list.size() == 0) {
			id = ajid+"1";
			ajSt.setId(id);
			service.insert(ajSt);
		} else {
			id = list.get(0).getId();
		}
		try {
			stxx = objMapper.readValue(str, Stxx.class);
			stxx.setBookNum("1");
			String pla = new StringUtil().tostr(stxx.getPla(), 1, stxx.getPlaRoleName());
			String pla2 = "";
			if (stxx.getPla().size() != 0) {
				pla2 = stxx.getPla().get(0);
			}
			String defen = new StringUtil().tostr(stxx.getDefen(), 2, stxx.getDefenRoleName());
			String defen2 = "";
			if (stxx.getDefen().size() != 0) {
				defen2 = stxx.getDefen().get(0);
			}
			String third = new StringUtil().tostr(stxx.getThird(), 3, stxx.getThirdRoleName());
			JSONObject QRContent = new JSONUtil().getFileJson(id, stxx.getCase_num(), stxx.getCaseCase(), pla2,
					defen2, stxx.getPageNum(), ajid, "1", "1", stxx.getAllBooks(),stxx.getSpcx());
			String imgPath = StringUtil.barpath + QRContent.getString("id") + "bar.png";
			String imgPath2 = StringUtil.qrpath + QRContent.getString("id") + "qr.png";
			new ZxingHandler();
			ZxingHandler.encode(id, barwith, barhiegth, imgPath);
			ZxingHandler.encode2(QRContent.toString(), qrwith, qrhiegth, imgPath2);
			List<SysAjSt> list2 = service.findList(ajid);
			FileInputStream bar = new FileInputStream(imgPath);
//			FileInputStream qr = new FileInputStream(imgPath2);
			model.addAttribute("content", QRContent.toString());
			session.setAttribute("content", QRContent.toString());
			model.addAttribute("stxx", stxx);
			model.addAttribute("id", QRContent.get("id"));
			model.addAttribute("stid", "1");
			model.addAttribute("pla", pla);
			model.addAttribute("defen", defen);
			model.addAttribute("third", third);
			model.addAttribute("list2", list2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "../yisong";
	} 
	@RequestMapping(value = "yisong")
	public String yisong(HttpSession session,@RequestParam(value = "ajid",required = true) String ajid,Model model) throws Exception{
		System.out.println(ajid);
		SysAjSt ajSt = new SysAjSt();
		ajSt.setAjid(ajid);
		ajSt.setStid("1");
		List<SysAjSt> list = service.findStid(ajSt);
		String id = "";
		if (list.size() == 0) {
			id = System.currentTimeMillis() + "";
			ajSt.setId(id);
			service.insert(ajSt);
		} else {
			id = list.get(0).getId();
		}
		String url = HttpClientUtil.url + "?ajid=" + ajid + "&&stid=" + "1";
		String j = new HttpClientUtil().Get(url);
		System.out.println(j);
		JSONObject json = JSONObject.fromObject(j);
		ObjectMapper objMapper = new ObjectMapper();
		Stxx stxx = new Stxx();
		stxx = objMapper.readValue(j, Stxx.class);
		System.out.println(stxx);
		stxx.setBookNum("1");
		String pla = new StringUtil().tostr(stxx.getPla(), 1, stxx.getPlaRoleName());
		String pla2 = "";
		if (stxx.getPla().size() != 0) {
			pla2 = stxx.getPla().get(0);
		}

		String defen = new StringUtil().tostr(stxx.getDefen(), 2, stxx.getDefenRoleName());
		String defen2 = "";
		if (stxx.getDefen().size() != 0) {
			defen2 = stxx.getDefen().get(0);
		}
		String third = new StringUtil().tostr(stxx.getThird(), 3, stxx.getThirdRoleName());

		JSONObject QRContent = new JSONUtil().getFileJson(id, stxx.getCase_num(), stxx.getCaseCase(), pla2,
				defen2, stxx.getPageNum(), ajid, "1", "1", stxx.getAllBooks(),stxx.getSpcx());
		String imgPath = StringUtil.barpath + QRContent.getString("id") + "bar.png";
		String imgPath2 = StringUtil.qrpath + QRContent.getString("id") + "qr.png";
		new ZxingHandler();
		ZxingHandler.encode(id, barwith, barhiegth, imgPath);
//		ZxingHandler.encode2(QRContent.toString(), qrwith, qrhiegth, imgPath2);
		List<SysAjSt> list2 = service.findList(ajid);
		FileInputStream bar = new FileInputStream(imgPath);
//		FileInputStream qr = new FileInputStream(imgPath2);
		session.setAttribute("content", QRContent.toString());
		model.addAttribute("content", QRContent.toString());
		System.out.println("123:  " + list2.size());
		model.addAttribute("stxx", stxx);
		model.addAttribute("id", QRContent.get("id"));
		model.addAttribute("stid", "1");
		model.addAttribute("pla", pla);
		model.addAttribute("defen", defen);
		model.addAttribute("third", third);
		model.addAttribute("list2", list2);
		return "../yisong";
		
	}

	@RequestMapping(value = "getzxxx2")
	public String getZxxx2(@RequestParam(value = "stid", required = true) String stid, HttpServletResponse response,
			HttpSession session, Model model) throws FileNotFoundException {
		String ajid = (String) session.getAttribute("ajid");
		String CaseNum = (String) session.getAttribute("caseNum");
		SysAjSt ajSt = new SysAjSt();
		ajSt.setAjid(ajid);
		ajSt.setStid(stid);
		List<SysAjSt> list = service.findStid(ajSt);
		String id = "";
		if (list.size() == 0) {
			id = System.currentTimeMillis() + "";
			ajSt.setId(id);
			service.insert(ajSt);
		} else {
			id = list.get(0).getId();
		}
		Stxx3 stxx = stxxService.findByCaseId(ajid);
		stxx.setCase_num(CaseNum);
		List<String> Defen = new ArrayList<>();
		String[] defen1 = stxx.getDefen().split(",");
		for (int i = 0; i < defen1.length; i++) {
			Defen.add(defen1[i]);
		}
		List<String> Pla = new ArrayList<>();
		String[] pla1 = stxx.getPla().split(",");
		for (int i = 0; i < pla1.length; i++) {
			Pla.add(pla1[i]);
		}
		List<String> Third = new ArrayList<>();
		String[] third1 = stxx.getThird().split(",");
		for (int i = 0; i < third1.length; i++) {
			Third.add(third1[i]);
		}
		String pla = new StringUtil().tostr(Pla, 1, stxx.getPlaRoleName());
		String pla2 = "";
		if (Pla.size() != 0) {
			pla2 = Pla.get(0);
		}
		String defen = new StringUtil().tostr(Defen, 2, stxx.getDefenRoleName());
		String defen2 = "";
		if (Defen.size() != 0) {
			defen2 = Defen.get(0);
		}
		String third = new StringUtil().tostr(Third, 3, "第三人");
		JSONObject QRContent = new JSONUtil().getFileJson(id, stxx.getCase_num(), stxx.getCaseCase(), pla2, defen2,
				stxx.getPageNum(), ajid, stid, stid, stxx.getAllBooks(),stxx.getSpcx());
		String imgPath = StringUtil.barpath + QRContent.getString("id") + "bar.png";
		String imgPath2 = StringUtil.qrpath + QRContent.getString("id") + "qr.png";
		new ZxingHandler();
		ZxingHandler.encode(id, barwith, barhiegth, imgPath);
//		ZxingHandler.encode2(QRContent.toString(), qrwith, qrhiegth, imgPath2);
		List<SysAjSt> list2 = service.findList(ajid);
		FileInputStream bar = new FileInputStream(imgPath);
//		FileInputStream qr = new FileInputStream(imgPath2);
		session.setAttribute("content", QRContent.toString());
		model.addAttribute("content", QRContent.toString());
		model.addAttribute("stxx", stxx);
		model.addAttribute("id", QRContent.get("id"));
		model.addAttribute("stid", stid);
		model.addAttribute("pla", pla);
		model.addAttribute("defen", defen);
		model.addAttribute("third", third);
		model.addAttribute("list2", list2);
		return "../face4";
	}

	@RequestMapping(value = "getzxxx")
	public String getZxxx(@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "lb", required = false) String lb,
			@RequestParam(value = "h", required = false) String h,
			@RequestParam(value = "caseCase", required = false) String caseCase,
			@RequestParam(value = "inDate", required = false) String inDate,
			@RequestParam(value = "addDate", required = false) String addDate,
			@RequestParam(value = "ssbd", required = false) String ssbd,
			@RequestParam(value = "slf", required = false) String slf,
			@RequestParam(value = "sfbq", required = false) String sfbq,
			@RequestParam(value = "zsfg", required = false) String zsfg,
			@RequestParam(value = "sjy", required = false) String sjy,
			@RequestParam(value = "sjyp", required = false) String sjyp,
			@RequestParam(value = "plas", required = false) String plas,
			@RequestParam(value = "plaR", required = false) String plaR,
			@RequestParam(value = "defens", required = false) String defens,
			@RequestParam(value = "defenR", required = false) String defenR,
			@RequestParam(value = "thirdp", required = false) String third,
			@RequestParam(value = "sycx", required = false) String sycx, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws ParseException {
		String caseNum = "(" + year + ")粤0305" + lb + h + "号";
		Stxx3 stxx = stxxService.findByCaseNum(caseNum);
		String id = System.currentTimeMillis() + "";
		System.out.println(defenR);
		System.out.println(plaR);
		if (stxx == null) {
			stxx = new Stxx3();
			stxx.setCase_num(caseNum);
			stxx.setId(id);
			stxx.setAllBooks("0");
			stxx.setBookNum("0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date in_date = sdf.parse(inDate + " 00:00:00");
			Date add_date = sdf.parse(addDate + " 00:00:00");
			stxx.setCaseCase(caseCase);
			stxx.setInDate(in_date);
			stxx.setAddDate(add_date);
			stxx.setSsbd(ssbd);
			stxx.setSlf(slf);
			stxx.setSfbq(sfbq);
			stxx.setZsfg(zsfg);
			stxx.setSjg(sjy);
			stxx.setSjyp(sjyp);
			stxx.setPla(plas);
			stxx.setDefen(defens);
			stxx.setPlaRoleName(plaR);
			stxx.setDefenRoleName(defenR);
			stxx.setThird(third);
			stxx.setSycx(sycx);
			stxx.setSycx(sycx);
			stxxService.insertStxx(stxx);
			SysAjSt ajSt = new SysAjSt();
			ajSt.setAjid(stxx.getId());
			ajSt.setStid("1");
			ajSt.setId(System.currentTimeMillis() + "");
			service.insert(ajSt);
		}
		stxx.setCase_num(caseNum);
		session.setAttribute("ajid", stxx.getId());
		session.setAttribute("caseNum", stxx.getCase_num());
		List<SysAjSt> list = service.findList(stxx.getId());
		List<String> Defen = new ArrayList<>();
		String[] defen1 = stxx.getDefen().split(",");
		for (int i = 0; i < defen1.length; i++) {
			Defen.add(defen1[i]);
		}
		List<String> Pla = new ArrayList<>();
		String[] pla1 = stxx.getPla().split(",");
		for (int i = 0; i < pla1.length; i++) {
			Pla.add(pla1[i]);
		}
		String pla = new StringUtil().tostr(Pla, 1, stxx.getPlaRoleName());
		String pla2 = "";
		if (Pla.size() != 0) {
			pla2 = Pla.get(0);
		}

		String defen = new StringUtil().tostr(Defen, 2, stxx.getDefenRoleName());
		String defen2 = "";
		if (Defen.size() != 0) {
			defen2 = Defen.get(0);
		}
		List<String> Third = new ArrayList<>();
		String[] third1 = stxx.getThird().split(",");
		for (int i = 0; i < third1.length; i++) {
			Third.add(third1[i]);
		}
		String third2 = new StringUtil().tostr(Third, 3, "第三人");
		String ajid = stxx.getId();
		String stid = "1";
		JSONObject QRContent = new JSONUtil().getFileJson(id + "", stxx.getCase_num(), stxx.getCaseCase(), pla2, defen2,
				stxx.getPageNum(), ajid, stid, stid, stxx.getAllBooks(),stxx.getSpcx());
		String imgPath = StringUtil.barpath + QRContent.getString("id") + "bar.png";
		String imgPath2 = StringUtil.qrpath + QRContent.getString("id") + "qr.png";
		new ZxingHandler();
		ZxingHandler.encode(id + "", barwith, barhiegth, imgPath);
//		ZxingHandler.encode2(QRContent.toString(), qrwith, qrhiegth, imgPath2);
		session.setAttribute("content", QRContent.toString());
		model.addAttribute("content", QRContent.toString());
		model.addAttribute("stxx", stxx);
		model.addAttribute("id", QRContent.get("id"));
		model.addAttribute("stid", stid);
		model.addAttribute("pla", pla);
		model.addAttribute("defen", defen);
		model.addAttribute("third", third2);
		model.addAttribute("list2", list);
		System.out.println(sycx);
		return "../face4";
	}
	
	@RequestMapping(value = "getbarcode")
	@ResponseBody
	public String getBarCode(@RequestParam(value = "img", required = false) String img, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {
		System.out.println(img);
		String a = (String) session.getAttribute("bar");
		session.setAttribute("bar", img);
		int b = 0;
		FileInputStream fis = null;
		OutputStream os = null;
		String imgPath = StringUtil.barpath + img + ".png";
		try {
			// String imgPath = "D:\\jztemp\\BarCode" + img + ".png";
			fis = new FileInputStream(imgPath);
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}

			fis.close();

			os.close();
			System.out.println("a:   " + a);
			System.out.println("img:   " + img);
			if (a != null && a.equals(img)) {
				b += 1;
			}
			// if(b == 2){
			// new FileUtil().DelFile(imgPath);
			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "ok";
	}

	@RequestMapping(value = "getqrcode")
	@ResponseBody
	public String getQRCode(@RequestParam(value = "img", required = false) String img, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {
		System.out.println(img);
		String a = (String) session.getAttribute("qr");
		int b = 0;
		session.setAttribute("qr", img);
		FileInputStream fis = null;
		OutputStream os = null;
		String imgPath = StringUtil.qrpath + img + ".png";
		try {
			// String imgPath = "D:\\jztemp\\QRCode" + img + ".png";
			fis = new FileInputStream(imgPath);
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}
			fis.close();
			os.close();
			if (a != null && a.equals(img)) {
				b++;
			}
			// if(b==1){
			// new FileUtil().DelFile(imgPath);
			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "ok";

	}

	
}
