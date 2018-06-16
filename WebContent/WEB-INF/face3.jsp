<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script type="text/javascript"
	src="/getarchive/static/Jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="/getarchive/static/Jquery/jQuery.print.js"></script>
<script type="text/javascript" 
	src="/getarchive/static/js/layer/layer.js"></script>
<head>
<link rel="stylesheet" href="/getarchive/static/css/table.css"
	type="text/css" />
<link rel="stylesheet" href="/getarchive/static/css/model.css"
	type="text/css" />
<link type="text/css" rel="stylesheet" href="/getarchive/static/showLoading/css/showLoading.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.pri{
	position: fixed;
	top: 10px;

	z-index: 999;
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

<body>
	<div id="pd" align="center">
		<!--startprint-->
		<c:if test="${empty reqstate }">
			<a href="/getarchive/print?ajid=${ajid }&stid=${stid }&xl=0">打印本卷宗封面</a>
		</c:if>
		<div id="print">
			<c:forEach var="c" items="${cases }">
				<div align="center" class="f22 top10">广东省深圳市南山区人民法院</div>
				<div align="center" class="f24 fw top10">排承办人审批表(第${c.stid }卷)</div>
				<div class="top10 cen">
					<table>
						<tr>
							<td colspan="2" class="w510 h360 f16 fw">${c.case_num }
							<td colspan="2" rowspan="2"><img
								src="getqrcode?img=${c.ajid }qr"> <br> <img
								src="getbarcode?img=${c.ajid }bar">
						<tr>
							<td class="w150 h72 f12 fw">案&nbsp;&nbsp;由
							<td class=" h72 f12">${c.caseCase }
						<tr>
							<td class="w150 h72 f12 fw">收案日期
							<td colspan="3"><fmt:formatDate value="${c.inDate }"
									pattern="yyyy-MM-dd" />
						<tr>
							<td class="w150 h72 f12 fw">立案日期
							<td colspan="3"><fmt:formatDate value="${c.addDate }"
									pattern="yyyy-MM-dd" />
						<tr>
							<td class="w150 h72 f12 fw">诉讼标的
							<td colspan="3">${c.ssbd }
						<tr>
							<td class="w150 h72 f12 fw">受&nbsp;理&nbsp;费
							<td>${c.slf }
							<td class="w150 h72 f12 fw">是否保全
							<td>${c.sfbq }
						<tr>
							<td class="w150 h72 f12 fw">主审法官
							<td>${c.zsfg }
							<td class="w150 h72 f12 fw">书记员
							<td>${c.sjg }
						<tr>
							<td class="w150 h72 f12 fw">书记员联系方式
							<td colspan="3">${c.sjyp }
						<tr>
							<td class="w150 h200 f12 fw">当&nbsp;事&nbsp;人
							<td colspan="3"><textarea
									style="border: 0px solid black; overflow: hidden;" rows="6"
									cols="60"><c:if test="${not empty c.pla2 }">${c.plaRoleName}:${c.pla2 }</c:if>&#13;&#10;&#13;&#10;<c:if
										test="${not empty c.defen2 }">${c.defenRoleName }:${c.defen2 }</c:if>
						</textarea>
						<tr>
							<td class="w150 h72 f12 fw">第三人
							<td colspan="3"><c:if test="${not empty c.third2 }">${c.third2 }</c:if>
						<tr>
							<td class="w150 h88 f12 fw">备&nbsp;&nbsp;注
							<td colspan="3">适用程序：${c.sycx }
					</table>
				</div>
				<br><br><br><br><br><br>
			</c:forEach>



		</div>
		<!--endprint-->
		<input class="pri" id="btnPrint" value="打印" type="button"
			 />
	</div>
</body>
</html>