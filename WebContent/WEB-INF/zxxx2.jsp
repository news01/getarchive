<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>执行案件封面打印</title>
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
.con{
	margin-top:-10px;
	width:700px;
	height: 700px;
	overflow-y: auto;  
    overflow-x: hidden;	
}
.con2{
	margin-top:-10px;
	width:600px;
	height: 700px;
	overflow: hidden;
}

</style>
</head>
<script type="text/javascript"
	src="/getarchive/static/Jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="/getarchive/static/js/layui/layui.js"></script>
<script>
	window.index = "";
	window.layer = "";
	
	function aja(year, lb, num) {
		$
				.ajax({
					url : '/getarchive/getcasenum',
					type : 'post',
					data : {
						year : year,
						lb : lb,
						num : num
					},
					dataType : 'json',
					beforeSend : function(XMLHttpRequest) {
						$("#tb").empty()
						layui.use('layer', function() {
							layer = layui.layer
							index = layer.load(2)
						})
					},
					success : function(res) {

						layer.close(index)
						var list = res.list
						var title = "<colgroup><col width=\"450\"><col></colgroup>"
						$("#tb").append(title);

						if (list.length == 0) {
							$("#tb").append("请搜索与日期对应的案号")
							//$('#case').hide();
						} else {
							$('#case').show();
							for (var i = 0; i < list.length; i++) {
								var document = "<tr><td>" + list[i].ah
										+ "</td><td><a href='getzxxxinfo?ajbs="
										+ list[i].ajbs + "'>选择</a></td></tr>"
								$("#tb").append(document)
							}

						}
					}

				})

	}
	$("document").ready(function() {
		$("#case").hide()
		var d = new Date();
		var year =  d.getFullYear()+''
		var month = (d.getMonth() + 1)+''
		var date = d.getDate()+''
		if (month.length == 1) {
			month = '0'+month
		}
		if(date.length == 1){
			date = '0'+date
		}
		var str = year + "-" + month + "-" + date;
		$("#select").click(function() {
			var y = $("input[name='year']").val()
			var lb = $("input[name='lb']").val()
			var num = $("input[name='h']").val()
			aja(y,lb,num)
		})
		$("input[name='year']").val(d.getFullYear())
		$("input[name=lb]").val("执")
		layui.use([ 'layer', 'form', 'laydate' ], function() {
			layer = layui.layer, form = layui.form, laydate = layui.laydate;
			form.on("select(ah)", function(data) {
				$("input[name='lb']").val($("select[name='ah']").val())
			})
			laydate.render({
				elem : '#date', //指定元素
				value : str,
				calendar : true,
				format : 'yyyy-MM-dd',
				done : function(value, date, endDate) {
					if (value == "") {
					} else {
						ajax(date.year, date.month, date.date)
					}
				}
			});
		});

		ajax(d.getFullYear(), d.getMonth() + 1, d.getDate())
	})
	function ajax(year, month, date) {
		$
				.ajax({
					url : '/getarchive/zxxx2',
					type : 'post',
					data : {
						year : year,
						month : month,
						date : date
					},
					dataType : 'json',
					beforeSend : function(XMLHttpRequest) {
						$("#tb").empty()
						/* layui.use('layer', function() {
							layer = layui.layer
							index = layer.load(2)
						}) */
					},
					success : function(res) {
						//layer.close(index)
						
						var list = res.list
						var title = "<colgroup><col width=\"450\"><col></colgroup>"
						$("#tb").append(title);

						if (res.status == 0) {
							if (list.length == 0) {
								$("#tb").append("暂无案件")
								$('#case').hide();
							} else {
								$('#case').show();
								for (var i = 0; i < list.length; i++) {
									var document = "<tr><td>"
											+ list[i].ah
											+ "</td><td><a href='getzxxxinfo?ajbs="
											+ list[i].ajbs
											+ "'>选择</a></td></tr>"
									$("#tb").append(document)
								}
							}

						}
					}

				})
	}
</script>
<body style="text-align: center;">
	<div class="content">
		<div class="title">执行案件列表</div>
		<div id="t2" class="layui-form">
			<div class="layui-form-item">
				<div class="layui-inline" style="width: 120px; margin-top: 10px;">
					<label class="layui-form-label f20" style="width: 120px;">请选择日期：</label>
				</div>
				<div class="layui-inline" style="width: 260px;">
					<input type="text" id="date" autocomplete="off" class="layui-input">
				</div>
			</div>
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
						lay-verify="required" lay-search onchange="fun()">
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
					<input type="text" name="h" style="width: 100px" autocomplete="off"
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
		<div align="center">
			
			<div class="content" style="width: 600px;">
				<table class="layui-table" style="width: 600px;">
					<colgroup>
						<col width="450px">
						<col>
					</colgroup>
					<thead>
						<tr>
							<th align="center">案号</th>
							<th align="center">操作</th>
						</tr>
					</thead>
				</table>
				<div class="con2">
				<div class="con">
				<table id="tb" class="layui-table" style="width: 600px;">
					
				</table>
				</div>
				</div>
			</div>
		</div>
		<%-- <table class="layui-table" style="width: 600px; background-color: #f0f0f0;">
				<colgroup>
					<col width="450">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th>案号</th>
						<th>操作</th>
					</tr>
				</thead>
			</table> --%>

	</div>
</body>
</html>