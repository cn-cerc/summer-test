<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品增加</title>
</head>
<body>
	<div>
		<form action="FrmProduct.append">
			商品编号<input type="text" name="code" value="" /> <br /> 品名 <input
				type="text" name="desc" value="" /> <br /> 规格 <input type="text"
				name="spec" value="" /> <br /> 单位 <input type="text" name="unit"
				value="" /> <br /> <input type="submit" value="保存" />
		</form>
	</div>
	<div style="padding: 0.5em;">
		<a href="FrmProduct">返回</a>
	</div>
	<div>${message}</div>
</body>
</html>