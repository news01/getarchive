package com.nsfy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by viper on 2017/6/8.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stxx2 {
    private String case_num = "";
    private String zsfg = "";
    @JsonProperty("case_case")
    private String caseCase = "";
    private String case_id;
    private List<String> pla = new ArrayList<>();
    private List<String> defen = new ArrayList<>();
    private List<String> third = new ArrayList<>();
    private String thirdRoleName;
    private String sjyp;
    private String sycx;
    private String pla2;
    private String third2;
    private String stid;
    private String spcx;
    
    public String getSpcx() {
		return spcx;
	}

	public void setSpcx(String spcx) {
		this.spcx = spcx;
	}

	public String getStid() {
		return stid;
	}

	public void setStid(String stid) {
		this.stid = stid;
	}

	public String getThird2() {
		return third2;
	}

	public void setThird2(String third2) {
		this.third2 = third2;
	}

	public String getCase_id() {
		return case_id;
	}

	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}

	private String defen2;
    private String ajid;
    

    public String getPla2() {
		return pla2;
	}

	public void setPla2(String pla2) {
		this.pla2 = pla2;
	}

	public String getDefen2() {
		return defen2;
	}

	public void setDefen2(String defen2) {
		this.defen2 = defen2;
	}

	public String getAjid() {
		return ajid;
	}

	public void setAjid(String ajid) {
		this.ajid = ajid;
	}

	public String getSycx() {
		return sycx;
	}

	public void setSycx(String sycx) {
		this.sycx = sycx;
	}

	public String getSjyp() {
		return sjyp;
	}

	public void setSjyp(String sjyp) {
		this.sjyp = sjyp;
	}

	public List<String> getThird() {
		return third;
	}

	public void setThird(List<String> third) {
		this.third = third;
	}

	public String getThirdRoleName() {
		return thirdRoleName;
	}

	public void setThirdRoleName(String thirdRoleName) {
		this.thirdRoleName = thirdRoleName;
	}

	private String plaRoleName = "ԭ��";
    private String defenRoleName = "����";

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

    @JsonProperty("cog_date")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date addDate;

    private String sjg;

    @JsonProperty("reg_date")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date inDate;


    @JsonProperty("book_num")
    private String bookNum = "0";
    @JsonProperty("all_books")
    private String allBooks = "0";

    @JsonProperty("page_num")
    private String pageNum = "0";

    private String ssbd;

    private String slf = "0.00";


    @JsonProperty("bq")
    private String sfbq = "��";

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

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getSjg() {
        return sjg;
    }

    public void setSjg(String sjg) {
        this.sjg = sjg;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getBookNum() {
        return bookNum;
    }

    public void setBookNum(String bookNum) {
        this.bookNum = bookNum;
    }

    public String getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(String allBooks) {
        this.allBooks = allBooks;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getSsbd() {
        return ssbd;
    }

    public void setSsbd(String ssbd) {
        this.ssbd = ssbd;
    }

    public String getSlf() {
        return slf;
    }

    public void setSlf(String slf) {
        this.slf = slf;
    }


    public String getSfbq() {
        return sfbq;
    }

    public void setSfbq(String sfbq) {
        this.sfbq = sfbq;
    }
}
