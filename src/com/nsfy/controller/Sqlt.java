package com.nsfy.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsfy.entity.Stxx3;
import com.nsfy.entity.SysAjSt;
import com.nsfy.service.StxxService;
import com.nsfy.service.SysAjStService;
import com.nsfy.util.JSONUtil;
import com.nsfy.util.StringUtil;
import com.nsfy.util.ZxingHandler;

import net.sf.json.JSONObject;

@Controller
public class Sqlt {
	private static int barwith = StringUtil.barwith;
	private static int qrwith = StringUtil.qrwith;
	private static int qrhiegth = StringUtil.qrhiegth;
	private static int barhiegth = StringUtil.barhiegth;

	@RequestMapping(value = "sqlt")
	public String sqlt() {
		return "../sqlt";
	}

	@RequestMapping(value = "addInfo")
	public String addInfo(@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "bh", required = false) String bh,
			@RequestParam(value = "caseCase", required = false) String caseCase,
			@RequestParam(value = "addDate", required = false) String addDate,
			@RequestParam(value = "inDate", required = false) String inDate,
			@RequestParam(value = "ssbd", required = false) String ssbd,
			@RequestParam(value = "ltfg", required = false) String ltfg,
			@RequestParam(value = "ltfgzl", required = false) String ltfgzl,
			@RequestParam(value = "ltfgzllxfs1", required = false) String ltfgzllxfs1,
			@RequestParam(value = "ltfgzllxfs2", required = false) String ltfgzllxfs2, HttpServletRequest request,
			Model model,HttpSession session) {
		String yg[] = request.getParameterValues("yg");
		String bg[] = request.getParameterValues("bg");
		String dsr[] = request.getParameterValues("ds");
		String caseNum = "（" + year + "）粤0305诉联调" + bh + "号";
		StxxService service = new StxxService();
		Stxx3 stxx = service.findByCaseNum(caseNum);
		SysAjStService ajStService = new SysAjStService();
		String ajid = "";
		String id = "";
		if (stxx == null) {
			stxx = new Stxx3();
			stxx.setCase_num(caseNum);
			ajid = System.currentTimeMillis() + "";
			stxx.setId(ajid);
			service.insertStxx(stxx);
			String stid = "1";
			SysAjSt ajSt = new SysAjSt();
			ajSt.setAjid(ajid);
			ajSt.setStid(stid);
			id = System.currentTimeMillis() + "";
			ajSt.setId(id);
			ajStService.insert(ajSt);
		} else {
			List<SysAjSt> ajStList = ajStService.findList(stxx.getId());
			ajid = stxx.getId();
			if (ajStList.size() != 0) {
				id = ajStList.get(0).getId();
			} else {
				SysAjSt ajSt = new SysAjSt();
				id = System.currentTimeMillis() + "";
				ajSt.setAjid(ajid);
				ajSt.setId(id);
				ajSt.setStid("1");
				ajStService.insert(ajSt);
			}
		}
		List<String> ygList = new ArrayList<>();
		for (int i = 0; i < yg.length; i++) {
			ygList.add(yg[i]);
		}
		List<String> bgList = new ArrayList<>();
		for (int i = 0; i < bg.length; i++) {
			bgList.add(bg[i]);
		}
		List<String> dsrList = new ArrayList<>();
		for (int i = 0; i < dsr.length; i++) {
			dsrList.add(dsr[i]);
		}
		String pla = new StringUtil().tostr(ygList, 1, "原告");
		String pla2 = "";
		if (ygList.size() != 0) {
			pla2 = ygList.get(0);
		}
		String defen = new StringUtil().tostr(bgList, 2, "被告");
		String defen2 = "";
		if (bgList.size() != 0) {
			defen2 = bgList.get(0);
		}
		String third = new StringUtil().tostr(dsrList, 3, "第三人");
		String third2 = "";
		if (dsrList.size() != 0) {
			third2 = dsrList.get(0);
		}
		JSONObject QRContent = new JSONUtil().getFileJson(id, caseNum, caseCase, pla2, defen2, "1", ajid, "1", "1", "1",
				"");
		String imgPath = StringUtil.barpath + QRContent.getString("id") + "bar.png";
		String imgPath2 = StringUtil.qrpath + QRContent.getString("id") + "qr.png";
		ZxingHandler.encode(id, barwith, barhiegth, imgPath);
		ZxingHandler.encode2(QRContent.toString(), qrwith, qrhiegth, imgPath2);
		try {
			FileInputStream bar = new FileInputStream(imgPath);
			FileInputStream qr = new FileInputStream(imgPath2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("content", QRContent.toString());
		model.addAttribute("content", QRContent.toString());
		model.addAttribute("id", QRContent.getString("id"));
		model.addAttribute("caseNum", caseNum);
		model.addAttribute("caseCase", caseCase);
		model.addAttribute("addDate", addDate);
		model.addAttribute("inDate", inDate);
		model.addAttribute("ssbd", ssbd);
		model.addAttribute("ltfg", ltfg);
		model.addAttribute("ltfgzl", ltfgzl);
		model.addAttribute("ltfgzllxfs1", ltfgzllxfs1);
		model.addAttribute("ltfgzllxfs2", ltfgzllxfs2);
		model.addAttribute("pla", pla);
		model.addAttribute("defen", defen);
		model.addAttribute("third", third);
		return "../sqltface";

	}
}
