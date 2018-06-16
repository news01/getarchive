<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>执行案件封面打印</title>
<link rel="stylesheet" href="/getarchive/static/js/layui/css/layui.css">
<style type="text/css">
.all {
	margin-left: 650px;
	width: 700px;
	margin-top: 50px;
}

div {
	font-size: 17px;
}
</style>
</head>
<script type="text/javascript"
	src="/getarchive/static/Jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="/getarchive/static/js/layui/layui.js"></script>
<script>
Date.prototype.Format = function(fmt) 
{ //author: meizz 
  var o = { 
    "M+" : this.getMonth()+1,                 //月份 
    "d+" : this.getDate(),                    //日 
    "h+" : this.getHours(),                   //小时 
    "m+" : this.getMinutes(),                 //分 
    "s+" : this.getSeconds(),                 //秒 
    "q+" : Math.floor((this.getMonth()+3)/3),
    "S"  : this.getMilliseconds()             
  }; 
  if(/(y+)/.test(fmt)) 
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); //格式化年份
  for(var k in o) //循环获取上面定义的月、日、小时等，格式化对应的数据。
    if(new RegExp("("+ k +")").test(fmt)) 
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
  return fmt; 
}
	//一般直接写在一个js文件中
	var id = 1;
	var dec = 2;
	function dd(d) {
		var a = d.split('-');
		var m;
		var da

		if (a[1].length == 1) {
			m = '-0' + a[1]
		} else {
			m = '-' + a[1]
		}
		if (a[2].length == 1) {
			da = '-0' + a[2]
		} else {
			da = '-' + a[2]
		}
		var mydate = '' + a[0] + m + da
		$("input[name='inDate']").val(mydate);
		$("input[name='addDate']").val(mydate);
	}
	$(document).ready(function() {
		var MyDate = new Date();
		$("input[name='inDate']").val(MyDate.Format("yyyy-MM-dd"))
		$("input[name='addDate']").val(MyDate.Format("yyyy-MM-dd"))
		$("input[name='year']").val(MyDate.getFullYear())
		$("input[name=lb]").val("执")
		});
	layui
			.use(
					[ 'layer', 'form' ],
					function() {
						var layer = layui.layer, form = layui.form();
						form
								.on(
										"select(sss)",
										function(data) {

											var kind = $(
													"select[name='RoleName']")
													.val();
											var kind2, title, role;
											if (kind == "0") {
												alert("请选择当事人类型")
											} else {
												if (kind == "1") {
													kind2 = "申请执行人";
													title = "pla";
													role = "plaRoleName";
												} else if (kind == "2") {
													kind2 = "被申请执行人";
													title = "defen";
													role = "defenRoleName";
												} else if (kind == "3") {
													kind2 = "原告";
													title = "pla";
													role = "plaRoleName";
												} else if (kind == "4") {
													kind2 = "被告";
													title = "defen";
													role = "defenRoleName";
												}else if(kind == "5"){
													kind2 = "被申请人";
													title = "defen";
													role = "defenRoleName";
												}

												var fdc = $("<div id='"+id+"' class='layui-form-item'>"
														+ "<div class='layui-inline' style='width: 200px'>"
														+ "<label class='layui-form-label' style='width: 180px'>"
														+ kind2
														+ "</label>"
														+ "<input type='hidden' id='"+id+998+"' value='"+kind2+"'>"
														+ "</div>"
														+ "<div class='layui-inline' style='width: 370px'>"
														+ "<input type='text' id='"
														+ id
														+ 999
														+ "' onblur='fun2("
														+ id
														+ 999
														+ ")' name='"
														+ title
														+ "' "
														+ "placeholder='请输入当事人名称' autocomplete='off' class='layui-input'>"
														+ "</div>"
														+ "<div class='layui-inline'>"
														+ "<button id='"
														+ dec
														+ "' class='layui-btn layui-btn-small layui-btn-primary' onclick='fun("
														+ dec
														+ ")'>"
														+ "<i class='layui-icon'>&#x1007;</i> 删除"
														+ "</button>"
														+ "</div>" + "</div>");
												$("#allbd").append(fdc);
												id = id + 2;
												dec = dec + 2;
											}

										});
						form.on("select(ah)", function(data) {
							$("input[name='lb']").val(
									$("select[name='ah']").val())
						})
					});
	function fun(ob) {
		document.getElementById('allbd').removeChild(
				document.getElementById(ob - 1 + ''));
	}
	function add(){
		var thi = $("<div id = '"+id+"' class='layui-form-item'>"
				+ "<div class='layui-inline' style='width: 200px'>"
				+ "<label class='layui-form-label' style='width: 180px'>"
				+ "第三人"
				+ "</lable>"
				+ "</div>"
				+ "<div class='layui-inline' style='width: 370px'>"
				+ "<input type='text' id='"+id+999+"' onblur='fun3("+id+999+")'"
				+ "placeholder='请输入第三人名称' autocomplete='off' class='layui-input'>"
				+ "</div>"
				+ "<div class='layui-inline'>"
				+ "<button id='"
				+ dec
				+ "' class='layui-btn layui-btn-small layui-btn-primary' onclick='fun1("
				+ dec
				+ ")'>"
				+ "<i class='layui-icon'>&#x1007;</i> 删除"
				+ "</button>"
				+ "</div>"
				+ "</div>");
		$("#dsr").append(thi);
		id = id + 2;
		dec = dec + 2;
	}
	function fun1(ob) {
		document.getElementById('dsr').removeChild(
				document.getElementById(ob - 1 + ''));
	}
	function fun2(op) {
		var a = document.getElementById(op + '').value
		var role = document.getElementById(op - 1 + '').value
		alert(role)
		if (role == "申请执行人"||role == "原告") {
			var plas = $("input[name='plas']").val().replace(/\s/ig,'')
			if (plas == '') {
				plas += document.getElementById(op + '').value
			} else {
				var pla = plas.split(',')
				var flag = true
				for (var i = 0; i < pla.length; i++) {
					if (pla[i] == document.getElementById(op + '').value) {
						flag = false
					}
				}
				if(flag){
					plas += ',' + document.getElementById(op + '').value
				}
				
			}
			$("input[name='plas']").val(plas)
			$("input[name='plaR']").val(role)
		}else{
			var defens = $("input[name='defens']").val().replace(/\s/ig,'')
			if (defens == '') {
				defens += document.getElementById(op + '').value
			} else {
				var defen = defens.split(',')
				var flag = true
				for (var i = 0; i < defen.length; i++) {
					if (defen[i]==document.getElementById(op + '').value) {
						flag = false
					}
				}
				if (flag) {
				defens += ',' + document.getElementById(op + '').value
				}
			}
			$("input[name='defens']").val(defens)
			$("input[name='defenR']").val(role)
		}
		
	}
	function fun3(op){
		var third = $("input[name='thirdp']").val().replace(/\s/ig,'')
		var dsr = document.getElementById(op + '').value
		if(third == ''){
			third += dsr
		}else{
			var t = third.split(',')
			var flag = true
			for (var i = 0; i < t.length; i++) {
				if(t[i]==dsr){
					flag = false
				}
			}
			if(flag){
			third += ','+dsr
			}
		}
		$("input[name='thirdp']").val(third)
	}
	layui.use('laydate', function() {
		var laydate = layui.laydate;

		var start = {
			min : '1970-01-01 00:00:00',
			max : laydate.now(),
			istoday : false,
			choose : function(datas) {
				end.min = datas; //开始日选好后，重置结束日的最小日期
				end.start = datas //将结束日的初始值设定为开始日
			}
		};

		var end = {
			min : '1970-01-01 00:00:00',
			max : laydate.now(),
			istoday : false,
			choose : function(datas) {
				start.max = datas; //结束日选好后，重置开始日的最大日期
			}
		};

		document.getElementById('inDate').onclick = function() {
			start.elem = this;
			laydate(start);
		}
		document.getElementById('addDate').onclick = function() {
			end.elem = this
			laydate(end);
		}

	});
</script>
<body>
	<div class="all">
		<div align="center" style="font-size: 40px; margin-bottom: 50px;">执行案件封面打印</div>
		<form name="form" class="layui-form" action="/getarchive/getzxxx"
			method="post">
			<div class="layui-form-item">
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
				<div class="layui-inline" style="width: 30px;">
					<select name="ah" lay-filter="ah" style="width: 20px;"
						lay-verify="required" lay-search onchange="fun()">
						<option value="执">执</option>
						<option value="执保">执保</option>
						<option value="民初">民初</option>
					</select>
				</div>
				<div class="layui-inline" style="width: 20px;">
					<label class="layui-form-label" style="width: 10px;"></label>
				</div>
				<div class="layui-inline" style="width: 80px;">
					<input type="text" name="h" style="width: 100px" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-inline" style="width: 10px;">
					<label class="layui-form-label" style="width: 10px;">号</label>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline" style="width: 105px;">
					<label class="layui-form-label" style="width: 90px;">案由：</label>
				</div>
				<div class="layui-inline" style="width: 460px;">
					<input type="text" name="caseCase" placeholder="请输入案由"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline" style="width: 105px;">
					<label class="layui-form-label" style="width: 90px;">收案：</label>
				</div>
				<div class="layui-inline" style="width: 150px;">
					<div class="layui-input-inline">
						<input class="layui-input" placeholder="收案日期" id="inDate"
							name="inDate">
					</div>
				</div>
				<div class="layui-inline" style="width: 90px;">
					<label class="layui-form-label" style="width: 90px;">立案：</label>
				</div>
				<div class="layui-inline" style="width: 180px;">
					<div class="layui-input-inline">
						<input class="layui-input" placeholder="立案日期" id="addDate"
							name="addDate">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline" style="width: 105px;">
					<label class="layui-form-label" style="width: 90px;">标的：</label>
				</div>
				<div class="layui-inline" style="width: 460px;">
					<input type="text" name="ssbd" placeholder="请输入标的金额"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline" style="width: 105px;">
					<label class="layui-form-label" style="width: 90px;">受理费：</label>
				</div>
				<div class="layui-inline" style="width: 165px;">
					<div class="layui-input-inline">
						<input class="layui-input" placeholder="受理费" name="slf">
					</div>
				</div>
				<div class="layui-inline" style="width: 90px;">
					<label class="layui-form-label" style="width: 90px;">保全：</label>
				</div>
				<div class="layui-inline" style="width: 80px;">
					<input type="radio" name="sfbq" value="是" title="是">
				</div>
				<div class="layui-inline" style="width: 80px;">
					<input type="radio" name="sfbq" value="否" title="否" checked>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline" style="width: 105px;">
					<label class="layui-form-label" style="width: 90px;">主审法官：</label>
				</div>
				<div class="layui-inline" style="width: 165px;">
					<div class="layui-input-inline">
						<input class="layui-input" placeholder="主审法官" name="zsfg">
					</div>
				</div>
				<div class="layui-inline" style="width: 90px;">
					<label class="layui-form-label" style="width: 90px;">书记员：</label>
				</div>
				<div class="layui-inline" style="width: 180px;">
					<input class="layui-input" placeholder="书记员" name="sjy">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline" style="width: 105px;">
					<label class="layui-form-label" style="width: 90px;">联系方式：</label>
				</div>
				<div class="layui-inline" style="width: 460px;">
					<input type="text" name="sjyp" placeholder="书记员联系方式"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline" style="width: 105px;">
					<label class="layui-form-label" style="width: 90px;">当事人：</label>
				</div>
				<div class="layui-inline" style="width: 460px;">
					<select name="RoleName" lay-filter="sss" lay-verify="required"
						lay-search onchange="fun()">
						<option value="">请选择当事人类型</option>
						<option value="1">申请执行人</option>
						<option value="2">被申请执行人</option>
						<option value="3">原告</option>
						<option value="4">被告</option>
						<option value="5">被申请人</option>

					</select>
				</div>
				<input name="plas" type="hidden"> <input name="defens"
					type="hidden"> <input name="defenR" type="hidden">
				<input name="plaR" type="hidden"> <input name="thirdp"
					type="hidden">
			</div>
			<div class="layui-form-item" id="allbd"></div>
			<div class="layui-form-item">
				<div class="layui-inline" style="width: 105px;">
					<label class="layui-form-label" style="width: 90px;">第三人：</label>
				</div>
				<div class="layui-inline">
					<button type="button" onclick="add()"
						class="layui-btn layui-btn-primary">
						<i class="layui-icon">&#xe608;</i> 增加第三人
					</button>
				</div>
			</div>
			<div class="layui-form-item" id="dsr"></div>
			<div class="layui-form-item">
				<div class="layui-inline" style="width: 105px;">
					<label class="layui-form-label" style="width: 90px;">适用程序：</label>
				</div>
				<div class="layui-inline" style="width: 460px;">
					<select name="sycx" lay-verify="required" lay-search
						onchange="fun()">
						<option value="">请选择适用程序</option>
						<option value="简易程序">简易程序</option>
						<option value="普通程序">普通程序</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item" align="center">
				<button type="submit" class="layui-btn">
					<i class="layui-icon">&#xe618;</i> 提交
				</button>
				<button type="reset" class="layui-btn layui-btn-primary">
					<i class="layui-icon">&#x1002;</i> 重置
				</button>
			</div>
		</form>
	</div>
</body>
</html>