<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>聚安API演示</title>
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
</head>
<body>
	<h2>聚安API使用演示</h2>
	<ul>
		<li>注册主机
			<form method="post" action="FrmSAPIDemo.serverRegister">
				appKey：<input name="appKey" value="${appKey}" /> appSecret：<input
					name="appSecret" value="${appSecret}" /> <br /> <input
					type="submit" value="sumbit" />
			</form>
		</li>
		<li>新用户注册时，发送验证码
			<form method="post" action="FrmSAPIDemo.requestRegister">
				注册手机号码：<input name="mobile" value="${mobile}" /> <br /> <input
					type="submit" value="sumbit" />
			</form>
		</li>
		<li>检查新用户收到的验证码
			<form method="post" action="FrmSAPIDemo.checkRegister">
				注册手机号码：<input name="mobile" value="${mobile}" /> 收到校验码：<input
					name="verifyCode" value="" /><br /> <input type="submit"
					value="sumbit" />
			</form>
		</li>
		<li>注册用户的密保手机
			<form method="post" action="FrmSAPIDemo.userRegister">
				用户帐号：<input name="user" value="${user}" /> 密保手机：<input
					name="mobile" value="${mobile}" /> <br /> <input type="submit"
					value="sumbit" />
			</form>
		</li>
		<li>检查环境是否安全
			<form method="post" action="FrmSAPIDemo.isSecurity">
				用户帐号：<input name="user" value="${user}" /> 用户设备Id：<input
					name="deviceId" value="${deviceId}" /><br /> <input type="submit"
					value="sumbit" />
			</form>
		</li>
		<li>在检查到环境不安全时，请求发送验证码
			<form method="post" action="FrmSAPIDemo.requestVerify">
				用户帐号：<input name="user" value="${user}" /> 用户设备Id：<input
					name="deviceId" value="${deviceId}" /><br /> <input type="submit"
					value="sumbit" />
			</form>
		</li>
		<li>检查收到的验证码是否正确
			<form method="post" action="FrmSAPIDemo.checkVerify">
				用户帐号：<input name="user" value="${user}" /> 用户设备Id：<input
					name="deviceId" value="${deviceId}" /> 收到校验码：<input
					name="verifyCode" value="" /><br /> <input type="submit"
					value="sumbit" />
			</form>
		</li>
		<li>检查当前环境是否安全（整合使用）
			<form method="post" action="FrmSAPIDemo.check">
				用户帐号：<input name="user" value="${user}" /> 用户设备Id：<input
					name="deviceId" value="${deviceId}" /> <input type="submit"
					value="sumbit" />
			</form>
		</li>
	</ul>
</body>
</html>