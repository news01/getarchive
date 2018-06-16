<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<script type="text/javascript"
	src="/getarchive/static/Jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="/getarchive/static/BeatPicker/js/BeatPicker.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="/getarchive/static/BeatPicker/css/BeatPicker.min.css">
<title>卷宗信息录入</title>
</head>
<script type="text/javascript">
	var indexPla = 1;
	var indexDefen = 1;
	var indexThird = 1;
	var plaObj = [];
	var defenObj = [];
	var thirdObj = [];
	$(document).ready(
			function() {
				var d = new Date()
				var year = d.getFullYear() + ''
				$("input[name='year']").val(year)
				$("select[name='sel']").change(function() {
					var sel = $("select[name='sel']").val()
					$("input[name='lb']").val(sel)
				})
				$("button[name='addPla']").click(
						function() {
							indexPla += 1;
							var plaInput = "<input type='text' id='yg"
									+ indexPla + "' name='yg'/>"
							var delButton = "<button id='delyg" + indexPla
									+ "' type='button' style='height: 24px; margin-top: 10px;' onclick='delPlas("
									+ indexPla + ")'>删除</button><br>"
							$("#yg").append(plaInput)
							$("#yg").append(delButton)
						})
				$("button[name='addDefen']").click(
						function() {
							indexDefen += 1;
							var defenInput = "<input style='height: 24px; margin-top: 10px;' type='text' id='bg" 
									+ indexDefen + "' name='bg' />";
									
							var delButton = "<button id='delbg" + indexDefen
									+ "' type='button' onclick='delDefen("
									+ indexDefen + ")'>删除</button><br/>"
							$("#bg").append(defenInput)
							$("#bg").append(delButton)
						})
				$("button[name='addThird']").click(
						function() {
							indexThird += 1;
							var thirdInput = "<input style='height: 24px; margin-top: 10px;' type='text' id='ds"
									+ indexThird + "' name='ds'/>"
							var delButton = "<button id='delds" + indexThird
									+ "' type='button' onclick='delThird("
									+ indexThird + ")'>删除</button><br/>"
							$("#third").append(thirdInput)
							$("#third").append(delButton)
						})
			})
	function addThird(index) {
		var index = index || 1
		var id = 'ds' + index
		var thirdOne = document.getElementById(id).value
		if(thirdOne){
			var thirdJson = '{"id":"' + id + '","value":"' + thirdOne + '"}'
			var third = eval('(' + thirdJson + ')')
			thirdObj.push(third)
			$("input[name='ds']").val(thirdObj)
		}
		
	}

	function addDefens(index) {
		var index = index || 1
		var id = 'bg' + index
		var defenOne = document.getElementById(id).value
		if(defenOne){
			var defenJson = '{"id":"' + id + '","value":"' + defenOne + '"}'
			var defen = eval('(' + defenJson + ')')
			defenObj.push(defen)
			$("input[name='bg']").val(defenObj)
		}
		
	}

	function addPlas(index) {
		var index = index || 1
		var id = 'yg' + index
		var plaOne = document.getElementById(id).value
		if(plaOne){
			var plaJson = '{"id":"' + id + '","value":"' + plaOne + '"}'
			var pla = eval('(' + plaJson + ')')
			plaObj.push(pla)
			$("input[name='yg']").val(plaObj)
		}
		
	}
	function delThird(index) {
		var inputId = 'ds' + index
		var buttonId = 'delds' + index
		var elementInput = document.getElementById(inputId)
		var elementButton = document.getElementById(buttonId)
		document.getElementById('third').removeChild(elementInput)
		document.getElementById('third').removeChild(elementButton)
	}
	function delDefen(index) {
		var inputId = 'bg' + index
		var buttonId = 'delbg' + index
		var elementInput = document.getElementById(inputId)
		var elementButton = document.getElementById(buttonId)
		document.getElementById('bg').removeChild(elementInput)
		document.getElementById('bg').removeChild(elementButton)
	}
	function delPlas(index) {
		var inputId = 'yg' + index
		var buttonId = 'delyg' + index
		var elementInput = document.getElementById(inputId)
		var elementButton = document.getElementById(buttonId)
		document.getElementById('yg').removeChild(elementInput)
		document.getElementById('yg').removeChild(elementButton)
		
		//$("input[name='yg']").val(plaObj)
	}
</script>
<body>
	<div align="center" style="margin-top: 30px;">
		<div style="font-size: 30px;">诉前联调审批表信息录入</div>
		<form action="addInfo" method="post">
			<div style="width: 500px; text-align: left; margin-top: 10px;">
				<table>
					<tr>
						<td style="width: 150px">案号</td>
						<td>（<input type="text" name="year" style="width: 35px;">）
							粤0305诉联调 <input type="text" name="bh"
							style="width: 50px; height: 24px;"> &nbsp;&nbsp; 号
						</td>
					</tr>
					<tr>
						<td>案由</td>
						<td colspan="1"><input type="text" name="caseCase"
							style="height: 24px;"></td>
					</tr>
					<tr>
						<td>收案日期</td>
						<td><input name="addDate"
							style="height: 24px; border-radius: 1px;" type="text"
							data-beatpicker="true"></td>
					</tr>
					<tr>
						<td>立案日期</td>
						<td><input name="inDate"
							style="height: 24px; border-radius: 1px;" type="text"
							data-beatpicker="true"></td>
					</tr>
					<tr>
						<td>诉讼标的</td>
						<td><input name="ssbd" style="height: 24px;" type="text">
						</td>
					</tr>
					<tr>
						<td>联调法官</td>
						<td><input name="ltfg" style="height: 24px;" type="text">
						</td>
					</tr>
					<tr>
						<td>联调法官助理</td>
						<td><input name="ltfgzl" style="height: 24px;" type="text">
						</td>
					</tr>
					<tr>
						<td>联调法官助理联系方式1</td>
						<td><input name="ltfgzllxfs1" style="height: 24px;" type="text">
						</td>
					</tr>
					<tr>
						<td>联调法官助理联系方式2</td>
						<td><input name="ltfgzllxfs2" style="height: 24px;" type="text">
						</td>
					</tr>
					
					<tr>
						<td>原告</td>
						<td id="yg"><input id="yg1" name="yg" style="height: 24px;margin-top: 20px;"
							type="text">
							<button type="button" name="addPla">添加</button> 
							<br/>	
						</td>

					</tr>
					<tr>
						<td>被告</td>
						<td id="bg"><input id="bg1" name="bg" style="height: 24px;margin-top: 20px;"
							type="text">
							<button type="button" name="addDefen">添加</button>
							<br/>
						</td>
					</tr>
					<tr>
						<td>第三人</td>
						<td id="third"><input id="ds1" name="ds"
							style="height: 24px;margin-top: 20px;" type="text">
							<button type="button" name="addThird">添加</button> 
							<br/>
						</td>
					</tr>

				</table>

				<div style="width: 500px; text-align: center;">
					<button type="submit">提交</button>
				</div>

			</div>
		</form>
	</div>
</body>
</html>