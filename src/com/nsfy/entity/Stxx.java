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
public class Stxx {
	private String id;
    private String case_num = "";
    private String zsfg = "";
    @JsonProperty("case_case")
    private String caseCase = "";
    private String spcx;
    private List<String> pla = new ArrayList<>();
    private List<String> defen = new ArrayList<>();
    private List<String> third = new ArrayList<>();
    private String thirdRoleName;
    private String sjyp;
    private String sycx;
    
    

    public String getSpcx() {
		return spcx;
	}

	public void setSpcx(String spcx) {
		this.spcx = spcx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
