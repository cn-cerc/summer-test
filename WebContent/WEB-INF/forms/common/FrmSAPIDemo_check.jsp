<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>聚安API演示</title>
<script src="js/jquery.js"></script>
<style>
div {
	font-size: 1em;
}

li {
	padding-top: 2em;
}

input {
	width: 8em;
}
</style>
<script>
	function onSend() {
		$.ajax({
			type : 'POST',
			url : "FrmSAPIDemo.requestVerify",
			data : {
				"user" : "${user}",
				"deviceId": "${deviceId}"
			},
			success : function(dataOut) {
				var json = JSON.parse(dataOut);
				if (json.result) {
					$("#msg").text("ok: " + json.message);
				} else {
					$("#msg").text("err: " + json.message);
				}
			},
			error : function(dataOut) {
				var json = JSON.parse(dataOut);
				alert("error: " + json.message);
			}
		});
	}
</script>
</head>
<body>
	<h2>当使用环境不够安全时，会出现此画面：</h2>
	<ul>
		<li>当前环境发生变化，请输入检验码：
			<form method="post" action="FrmSAPIDemo.check">
				<input type="hidden" name="user" value="${user}" /> 
				<input type="hidden" name="deviceId" value="${deviceId}" /><input
					name="securityCode" value="" /> <a href="javascript:onSend()">发送</a>
				<div style="color: blue" id="msg"></div>
				<input type="submit" value="sumbit" />
			</form>
		</li>
	</ul>
</body>
</html>