<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE>
<html>
<script type="text/javascript"
	src="/getarchive/static/Jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="/getarchive/static/Jquery/jQuery.print.js"></script>
<script type="text/javascript" 
	src="/getarchive/static/js/layer/layer.js"></script>
<script src="<spring:url value="/static/js/jquery.qrcode.min.js"/>" type="text/javascript" ></script>
<head>
<link rel="stylesheet" href="/getarchive/static/css/table.css"
	type="text/css" />
<link rel="stylesheet" href="/getarchive/static/css/model.css"
	type="text/css" />
<link type="text/css" rel="stylesheet" href="/getarchive/static/showLoading/css/showLoading.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
var index
$(document).ready(function() {
var loginName = ''
	$("#btnPrint").click(function() {
		layer.prompt({title:'请输入业务系统登录名'},function(val, index){
			loginName = val
			  	layer.close(index);
				var url = "loginName?loginName="+loginName
				ajax(url)
			});
	})
})
function ajax(url){
	$.ajax({
		url:url,
		type:'GET',
		dataType:'json',
		beforeSend : function(xhr) {
			index=layer.load(1, {
				  shade: [0.1,'#fff'] //0.1透明度的白色背景
				});
		},
		success:function(result){
			layer.close(index)
			if(result.errCode == 0){
				jQuery('#print').print();
			}else{
				layer.alert(result.errMsg)
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			layer.close(index)
			layer.alert(result.errMsg)
		}
	})
}
</script>
<script type="text/javascript">
function utf16to8(str) {  
    var out, i, len, c;  
    out = "";  
    len = str.length;  
    for (i = 0; i < len; i++) {  
        c = str.charCodeAt(i);  
        if ((c >= 0x0001) && (c <= 0x007F)) {  
            out += str.charAt(i);  
        } else if (c > 0x07FF) {  
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));  
            out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));  
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));  
        } else {  
            out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));  
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));  
        }  
    }  
    return out;  
}  
$(function () {  
    jQuery('#qrcode').qrcode(utf16to8('${content}'));  
    var canvas  = document.getElementsByTagName("canvas")[0];
    var img = document.getElementById("image");
    img.src = canvas.toDataURL();
    
})    
</script>
<body>
	<div id="pd" align="center">
		<div id="print">
		<div align="center" class="f22">民商事、行政上诉案件移送表</div>
		<table class="top10" style="table-layout:fixed;"
		  border="1" align="center">
		
		    <tr>
		    	<td colspan="2" style="width: 70px">一审法院名称</td>
		    	<td style="width: 130px">深圳市南山区人民法院</td>
		    	<td style="width: 180px">法官电话：<input type="text" style="width: 60px;border: none;"></td>
		    	<td style="width: 180px">助理电话：<input type="text" style="width: 60px;border: none;"></td>
		    </tr>
		    <tr>
		    	<td colspan="2" style="width: 70px">一审案号</td>
		    	<td style="width: 130px">${stxx.case_num }</td>
		    	<td style="width: 180px">案由：${stxx.caseCase }</td>
		    	<td style="width: 180px">诉讼标的：${stxx.ssbd }</td>
		    </tr>
		   	<tr>
		   		<td colspan="3" style="width: 260px;">财产保全日期：<input type="text" style="width: 60px;border: none;"></td>
		   		<td colspan="2" style="width: 300px;">到期日：<input type="text" style="width: 60px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td colspan="3" style="width: 260px;">
		   			上诉费交纳情况(在□打√)(缓<input type="checkbox" value="">、&emsp;减<input type="checkbox" value="">、&emsp;免<input type="checkbox" value="">)
		   			<br>
		      		是否涉外(在□打√)&emsp;是<input type="checkbox" value="">&emsp;否<input type="checkbox" value="">
		   		</td>
		   		<td colspan="2" style="width: 300px;">
		   			上诉费缴费情况(在□打√)
			      	<br>
			      	有<input type="checkbox" value="">&emsp;&emsp;&emsp;金额：<input style="border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-color:black;width: 50px">
			      	<br>
			      	无&emsp;<input type="checkbox" value="">驳回起诉&emsp;<input type="checkbox" value="">不予受理&emsp;<input type="checkbox" value="">逾期未缴&emsp;<input type="checkbox" value="">管辖权异议&emsp;<input type="checkbox" value="">判决
		   		</td>
		   	</tr>
		   	<tr>
		   		<td align="center" colspan="5">卷宗材料<input type="text" style="border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-color:black;width: 50px">册</td>
		   	</tr>
		    <tr>
		   		<td rowspan="6" colspan="1" align="center" style="width: 25px">
		   			上诉人
		   		</td>
		   		<td rowspan="2" colspan="2" style="width: 235px">
		   			名称：<input type="text" style="width: 100px; border: none;">
		   			<br/>
		   			手机号码：<input type="text" style="width: 80px; border: none;">
		   		</td>
		   		<td align="center">裁判文书送达时间</td>
		   		<td><input type="text" style="width: 80px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td align="center">提交上诉时间</td>
		   		<td><input type="text" style="width: 80px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td rowspan="2" colspan="2">
		   			名称：<input type="text" style="width: 100px; border: none;">
		   			<br/>
		   			手机号码：<input type="text" style="width: 80px; border: none;">
		   		</td>
		   		<td align="center">裁判文书送达时间</td>
		   		<td><input type="text" style="width: 80px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td align="center">提交上诉时间</td>
		   		<td><input type="text" style="width: 80px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td rowspan="2" colspan="2">
		   			名称：<input type="text" style="width: 100px; border: none;">
		   			<br/>
		   			手机号码：<input type="text" style="width: 80px; border: none;">
		   		</td>
		   		<td align="center">裁判文书送达时间</td>
		   		<td><input type="text" style="width: 80px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td align="center">提交上诉时间</td>
		   		<td><input type="text" style="width: 80px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td rowspan="3" style="width: 25" align="center">
		   			被上诉人
		   		</td>
		   		<td rowspan="3" colspan="2">
		   			名称：<input type="text" style="width: 100px; border: none;">
		   			<br/>
		   			手机号码：<input type="text" style="width: 80px; border: none;">
		   		</td>
		   		<td align="center">裁判文书送达时间</td>
		   		<td><input type="text" style="width: 80px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td align="center">上诉状送达时间</td>
		   		<td><input type="text" style="width: 80px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td align="center">
		    		上诉状送达方式(在□打√)
		    	</td>
		    	<td align="center" >
		    		直接<input type="checkbox" value="">&emsp;邮寄<input type="checkbox" value="">
		    		<br>
		    		公告<input type="checkbox" value="">&emsp;其它<input type="checkbox" value="">
		    	</td>
		   	</tr>
		   	<tr>
		   		<td rowspan="3" style="width: 25" align="center">
		   			被上诉人
		   		</td>
		   		<td rowspan="3" colspan="2">
		   			名称：<input type="text" style="width: 100px; border: none;">
		   			<br/>
		   			手机号码：<input type="text" style="width: 80px; border: none;">
		   		</td>
		   		<td align="center">裁判文书送达时间</td>
		   		<td><input type="text" style="width: 80px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td align="center">上诉状送达时间</td>
		   		<td><input type="text" style="width: 80px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td align="center">
		    		上诉状送达方式(在□打√)
		    	</td>
		    	<td align="center">
		    		直接<input type="checkbox" value="">&emsp;邮寄<input type="checkbox" value="">
		    		<br>
		    		公告<input type="checkbox" value="">&emsp;其它<input type="checkbox" value="">
		    	</td>
		   	</tr>
		   	<tr>
		   		<td rowspan="3" style="width: 25" align="center">
		   			被上诉人
		   		</td>
		   		<td rowspan="3" colspan="2">
		   			名称：<input type="text" style="width: 100px; border: none;">
		   			<br/>
		   			手机号码：<input type="text" style="width: 80px; border: none;">
		   		</td>
		   		<td align="center">裁判文书送达时间</td>
		   		<td><input type="text" style="width: 80px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td align="center">上诉状送达时间</td>
		   		<td><input type="text" style="width: 80px; border: none;"></td>
		   	</tr>
		   	<tr>
		   		<td align="center">
		    		上诉状送达方式(在□打√)
		    	</td>
		    	<td align="center" style="font-size: 12px">
		    		直接<input type="checkbox" value="">&emsp;邮寄<input type="checkbox" value="">
		    		<br>
		    		公告<input type="checkbox" value="">&emsp;其它<input type="checkbox" value="">
		    	</td>
		   	</tr>
		   	<tr>
			   	<td colspan="16" colspan="4" style="text-align:left;">
					            一审法院移送案件时间：<input type="text" style="width: 80px; border: none;">
					            &emsp;
				    	移送人：<input type="text" style="width: 80px; border: none;">
						    	&emsp;
				    	接收人：<input type="text" style="width: 80px; border: none;">
			 	</td>  
		 	</tr>
		 	<tr>
		    	<td align="right" colspan="5" style="text-align:left;" height="100px">
		    	<div id="qrcode" hidden="true"></div>
		    	<img id="image" style="width: 120px;height: 120px;"> </td>
		    </tr>
		</table>
		</div>
		<input id="btnPrint" value="打印" type="button" 
		 />
	</div>
</body>
</html>