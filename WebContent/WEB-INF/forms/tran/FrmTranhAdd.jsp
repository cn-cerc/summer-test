<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>单头增加</title>
</head>
<body>
	<div>
		<form action="FrmTran.appendTranh" method="post">
			单号<input type="text" name="tbNo" value="" /> <br /> 
			日期 <input type="text" name="tbDate" value="${date}" /> <br /> 
			厂商 <input type="text" name="superName" value="" /> <br /> 
			建档人员 <input type="text" name="appUser" value="" /> <br /> 
			<input type="submit" value="保存" />
		</form>
	</div>
	<div style="padding: 0.5em;">
		<a href="FrmTran.listTranh">返回</a>
	</div>
	<div>${message}</div>
</body>
</html>