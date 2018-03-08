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
		<form action="FrmSalehAppend" method="post">
			单号<input type="text" name="tbNo" value="${saleh.tbNo }" /> <br />
			日期 <input type="text" name="tbDate" value="${date}" /> <br /> 客户 <input
				type="text" name="cusName" value="${saleh.cusName  }" /> <br />
			建档人员 <input type="text" name="appUser" value="${saleh.appUser  }" />
			<br /> <input type="submit" value="保存" />
		</form>
	</div>
	<div style="padding: 0.5em;">
		<a href="FrmSalehList">返回</a>
	</div>
	<div>${message}</div>
</body>
</html>