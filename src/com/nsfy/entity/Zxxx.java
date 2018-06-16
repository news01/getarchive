package com.nsfy.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Zxxx {
	private String id; // <案件标识>
	private String case_num; // <案号>
	private String zsfg; // 主审法官 
	private String caseCase; // <立案案由>
	private List<String> pla;// 申请执行人姓名
	private String plaRoleName; // 申请执行人
	private List<String> defen;// 被申请人姓名 
	private String defenRoleName; // 被申请人
	private List<String> bzxr;// 被执行人姓名 
	private String bzxrRoleName;// 被执行人
	private List<String> third;// 第三人姓名  
	private String thirdRoleName;// 第三人
	private String sjyp;// 书记员联系方式  
	private String sycx;// 适用程序   
	private String addDate;// 收案日期 
	private String sjg;// 书记员 
	private String inDate;// 立案日期 
	private String ssbd;// 诉讼标的 
	private String sfbq;// 是否保全  
	private String slf;// 受理费 
	private String spcx;
	public List<String> getPla() {
		return pla;
	}
	public void setPla(List<String> pla) {
		this.pla = pla;
	}
	public List<String> getDefen() {
		return defen;
	}
	public void setDefen(List<String> defen) {
		this.defen = defen;
	}
	public List<String> getBzxr() {
		return bzxr;
	}
	public void setBzxr(List<String> bzxr) {
		this.bzxr = bzxr;
	}
	public List<String> getThird() {
		return third;
	}
	public void setThird(List<String> third) {
		this.third = third;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCase_num() {
		return case_num;
	}
	public void setCase_num(String case_num) {
		this.case_num = case_num;
	}
	public String getZsfg() {
		return zsfg;
	}
	public void setZsfg(String zsfg) {
		this.zsfg = zsfg;
	}
	public String getCaseCase() {
		return caseCase;
	}
	public void setCaseCase(String caseCase) {
		this.caseCase = caseCase;
	}
	public String getPlaRoleName() {
		return plaRoleName;
	}
	public void setPlaRoleName(String plaRoleName) {
		this.plaRoleName = plaRoleName;
	}
	public String getDefenRoleName() {
		return defenRoleName;
	}
	public void setDefenRoleName(String defenRoleName) {
		this.defenRoleName = defenRoleName;
	}
	public String getBzxrRoleName() {
		return bzxrRoleName;
	}
	public void setBzxrRoleName(String bzxrRoleName) {
		this.bzxrRoleName = bzxrRoleName;
	}
	public String getThirdRoleName() {
		return thirdRoleName;
	}
	public void setThirdRoleName(String thirdRoleName) {
		this.thirdRoleName = thirdRoleName;
	}
	public String getSjyp() {
		return sjyp;
	}
	public void setSjyp(String sjyp) {
		this.sjyp = sjyp;
	}
	public String getSycx() {
		return sycx;
	}
	public void setSycx(String sycx) {
		this.sycx = sycx;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public String getSjg() {
		return sjg;
	}
	public void setSjg(String sjg) {
		this.sjg = sjg;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getSsbd() {
		return ssbd;
	}
	public void setSsbd(String ssbd) {
		this.ssbd = ssbd;
	}
	public String getSfbq() {
		return sfbq;
	}
	public void setSfbq(String sfbq) {
		this.sfbq = sfbq;
	}
	public String getSlf() {
		return slf;
	}
	public void setSlf(String slf) {
		this.slf = slf;
	}
	
}
