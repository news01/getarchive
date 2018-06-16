package com.nsfy.util;

import java.util.List;

import com.nsfy.entity.Zxxx;

import net.sf.json.JSONObject;

public class StringUtil {
	public static final String url = "";
//	 public static final String barpath =
//	 "C:\\Users\\zmj\\Desktop\\image\\BarCode";
//	 public static final String qrpath =
//	 "C:\\Users\\zmj\\Desktop\\image\\BarCode";
	public static final String jbfy = "2580";
	public static final String lx = "SA";
	public static final String barpath = "D:\\jztemp\\BarCode";
	public static final String qrpath = "D:\\jztemp\\QRCode";
//23004286721303610
	public String tostr(List<String> l, int a, String s) {
		String str = "";
		int b = 0;
		if (a == 1) {
			b = 60;
		}
		if (a == 2) {
			b = 90;
		}
		if (a == 3) {
			b = 30;
		}
		if (l.size() != 0) {
			for (int i = 0; i < l.size(); i++) {
				if (str.length() > (b - s.length())) {
					str = str.substring(0, str.length() - 1);
					str += "等";
					break;
				}
				if (i != l.size() - 1) {
					str += l.get(i) + ",";
				} else {
					str += l.get(i);
				}
			}
		}
		return str;
	}
	
	

	public static final int barwith = 105;
	public static final int qrwith = 250;
	public static final int barhiegth = 40;
	public static final int qrhiegth = 250;
}
