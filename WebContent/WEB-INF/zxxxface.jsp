<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
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
<style type="text/css">
th{
	background-color: white;
}
</style>
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
    var img = document.getElementById("qrimage");
    img.src = canvas.toDataURL();
    
})    
</script>
<body>
	<div id="pd" align="center">

		<!--startprint-->
		<div id="print">
			<div align="center" class="f22 top10">广东省深圳市南山区人民法院</div>
			<div align="center" class="f24 fw top10">排承办人审批表</div>
			<div class="top10 cen">
				<table>
					<tr>
						<td colspan="2" class="w510 h360 f16 fw">${zxxx.case_num }
						<td colspan="2" rowspan="2"><img src="getqrcode?img=${id }qr">
							<br> <img src="getbarcode?img=${id }bar">
					<tr>
						<td class="w150 h72 f12 fw">案&nbsp;&nbsp;由
						<td class=" h72 f12">${zxxx.caseCase }
					<tr>
						<td class="w150 h72 f12 fw">收案日期
						<td colspan="3">${zxxx.addDate }
					<tr>
						<td class="w150 h72 f12 fw">立案日期
						<td colspan="3">${zxxx.inDate }
					<tr>
						<td class="w150 h72 f12 fw">诉讼标的
						<td colspan="3">${zxxx.ssbd }
					<tr>
						<td class="w150 h72 f12 fw">受&nbsp;理&nbsp;费
						<td>${zxxx.slf }
						<td class="w150 h72 f12 fw">是否保全
						<td>${zxxx.sfbq }
					<tr>
						<td class="w150 h72 f12 fw">主审法官
						<td>${zxxx.zsfg }
						<td class="w150 h72 f12 fw">书记员
						<td>${zxxx.sjg }
					
					<tr>
						<td class="w150 h200 f12 fw">当&nbsp;事&nbsp;人
						<td colspan="3"><textarea 
								style="border: 0px solid black; overflow: hidden; font-size: 20px;" rows="8"
								cols="40"><c:if test="${not empty pla }">${zxxx.plaRoleName}:${pla }</c:if>&#13;&#10;&#13;&#10;<c:if
									test="${not empty defen }">${zxxx.bzxrRoleName }:${defen }</c:if>&#13;&#10;&#13;&#10;<c:if
									test="${not empty defen1 }">${zxxx.defenRoleName }:${defen1 }</c:if>
						</textarea>
					<tr>
						<td class="w150 h72 f12 fw">第三人
						<td colspan="3" style="font-size: 20px;"><c:if test="${not empty third }">${third }</c:if>
					<tr>
						<td class="w150 h72 f12 fw">审批意见
						<td colspan="3">
					<tr>
						<td class="w150 h88 f12 fw">备&nbsp;&nbsp;注
						<td align="left" colspan="3">适用程序：${zxxx.sycx }
				</table>
			</div>
			
		</div>
		<!--endprint-->
		<input id="btnPrint" value="打印" type="button"
		 />
	</div>
</body>
</html>