<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border-collapse: collapse;
	border-spacing: 0;
	border-left: 1px solid #888;
	border-top: 1px solid #888;
	background: white;
	margin: 0px auto;
}

th, td {
	border-right: 1px solid #888;
	border-bottom: 1px solid #888;
	padding: 5px 15px;
}

th {
	font-weight: bold;
	background: #ccc;
}

body {
	font-family: "宋体";
	background-color: white;
}

.cen {
	text-align: center;
}

.f16 {
	font-size: 16pt;
}

.f22 {
	font-size: 22pt;
}

.f24 {
	font-size: 24pt;
}

.f12 {
	font-size: 12pt;
}

.fw {
	font-weight: bold;
}

.top10 {
	padding-top: 10px;
}

.w505 {
	width: 257px;
}

.w150 {
	width: 70px;
}

.w355 {
	width: 177px;
}

.h360 {
	height: 180px;
}

.h72 {
	height: 36px;
}

.h88 {
	height: 44px;
}

.h200 {
	height: 100px;
}
</style>
</head>
<script type="text/javascript">
	function preview() {
		bdhtml = window.document.body.innerHTML;
		sprnstr = "<!--startprint-->";
		eprnstr = "<!--endprint-->";
		prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 17);
		prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
		window.document.body.innerHTML = prnhtml;
		window.print();
	}
</script>

<body>
	<div align="center">
	<div>
		<form action="print">
			请选择您要打印的卷宗：第
			<select id = "stid" name="stid">
				<c:forEach items="${list }" var="l">
					<option value="${l.stid }">${l.stid }
				</c:forEach>
			</select>
			卷
			<input type="radio" name="xl" value="1">系列案
			<input type="radio" name="xl" value="0" checked="checked">非系列案
			<input type="submit" value="提交">
		</form>
	</div>
		<!--startprint-->
		<div align="center" class="f22 top10">广东省深圳市南山区人民法院</div>
		<div align="center" class="f24 fw top10">排承办人审批表</div>
		<div class="top10 cen">
			<table>
				<tr>
					<td colspan="2" class="w505 h360 f16 fw">${case_num }
					<td colspan="2" rowspan="2" class="w505">
				<tr>
					<td class="w150 h72 f12 fw">案&nbsp;&nbsp;由
					<td class="w355 h72 f12">
				<tr>
					<td class="w150 h72 f12 fw">收案日期
					<td colspan="3">
				<tr>
					<td class="w150 h72 f12 fw">立案日期
					<td colspan="3">
				<tr>
					<td class="w150 h72 f12 fw">诉讼标的
					<td colspan="3">
				<tr>
					<td class="w150 h72 f12 fw">受&nbsp;理&nbsp;费
					<td colspan="3">
				<tr>
					<td class="w150 h72 f12 fw">是否保全
					<td colspan="3">
				<tr>
					<td class="w150 h72 f12 fw">主审法官
					<td>
					<td class="w150 h72 f12 fw">书记员
					<td>
				<tr>
					<td class="w150 h200 f12 fw">当&nbsp;事&nbsp;人
					<td colspan="3" align="left">
				<tr>
					<td class="w150 h72 f12 fw">庭长确认
					<td>
					<td class="w150 h72 f12 fw">确认日期
					<td>
				<tr>
					<td class="w150 h72 f12 fw">审批意见
					<td colspan="3">
				<tr>
					<td class="w150 h88 f12 fw">备&nbsp;&nbsp;注
					<td colspan="3">
			</table>
		</div>
		<!--endprint-->

	</div>
</body>
</html>