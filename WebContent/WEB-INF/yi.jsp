<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/getarchive/static/js/layui/css/layui.css">
<style type="text/css">
.content {
	text-align: center;
}

.datachoose {
	width: 300px;
}

.title {
	font-size: 40px;
}

.f20 {
	font-size: 20px;
}

.con {
	margin-top: -10px;
	width: 700px;
	height: 700px;
	overflow-y: auto;
	overflow-x: hidden;
}

.con2 {
	margin-top: -10px;
	width: 600px;
	height: 700px;
	overflow: hidden;
}
</style>
<title>Insert title here</title>
</head>
<script type="text/javascript"
	src="/getarchive/static/Jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="/getarchive/static/js/layui/layui.js"></script>
<script type="text/javascript">
window.index = "";
window.layer = "";
	$("document").ready(function() {
		var d = new Date()
		var year = d.getFullYear() + ''
		$("input[name='year']").val(d.getFullYear())
		$("input[name=lb]").val("执")
		layui.use([ 'layer', 'form', 'laydate' ], function() {
			layer = layui.layer, form = layui.form, laydate = layui.laydate;
			form.on("select(ah)", function(data) {
				$("input[name='lb']").val($("select[name='ah']").val())
			})
		});
		$("#select").click(function(){
			if(!$("input[name='year']").val()||!$("input[name='lb']").val()||!$("input[name='xh']").val()){
				alert("内容输入不完整，请重新输入")
			}else{
				var year = $("input[name='year']").val()
				var lb = $("input[name='lb']").val()
				var xh = $("input[name='xh']").val()
				var url = "/getarchive/getYsAjByCaseNum?year="+year+"&lb="+lb+"&xh="+xh;
				url = encodeURI(url)
				window.location.href = url
			}
		})
	})
	function ajax(year,lb,xh){
		$.ajax({
			url:'/getarchive/getYsAjByCaseNum',
			type : 'post',
			data : {
				year : year,
				lb : lb,
				xh : xh
			},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$("#tb").empty()
				layui.use('layer', function() {
					layer = layui.layer
					index = layer.load(2)
				})
			},
			success:function(res){
				layer.close(index)
				alert(res)
			}
			
		})
	}
	
</script>
<body style="text-align: center;">
	<div class="content">
		<div class="title">移送案件打印</div>
		<div id="t2" class="layui-form">
			<div id="case" class="layui-form-item">
				<div class="layui-inline" style="width: 80px;">
					<label class="layui-form-label" style="width: 90px;">案号：</label>
				</div>
				<div class="layui-inline" style="width: 10px;">
					<label class="layui-form-label" style="width: 5px;">(</label>
				</div>
				<div class="layui-inline" style="width: 40px;">
					<input style="width: 75px;" type="text" name="year"
						placeholder="年份" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-inline" style="width: 100px;">
					<label class="layui-form-label" style="width: 80px;">)&nbsp;&nbsp;粤0305</label>
				</div>
				<div class="layui-inline" style="width: 80px;">
					<input type="text" name="lb" style="width: 100px"
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-inline" style="width: 25px;">
					<select name="ah" lay-filter="ah" style="width: 20px;"
						lay-verify="required" lay-search>
						<option value="执">执</option>
						<option value="执保">执保</option>
						<option value="执恢">执恢</option>
						<option value="执异">执异</option>
					</select>
				</div>
				<div class="layui-inline" style="width: 10px;">
					<label class="layui-form-label" style="width: 10px;"></label>
				</div>
				<div class="layui-inline" style="width: 80px;">
					<input type="text" name="xh" style="width: 100px" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-inline" style="width: 50px;">
					<label class="layui-form-label" style="width: 10px;">号</label>

				</div>
				<div class="layui-inline" style="width: 80px;">
					<button id="select" class="layui-btn">
						搜索 <i class="layui-icon">&#xe615;</i>
					</button>
				</div>
			</div>
		</div>
	</div>
	

</body>
</html>