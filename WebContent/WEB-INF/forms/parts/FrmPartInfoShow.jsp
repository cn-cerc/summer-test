<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品信息维护</title>
<div style="text-align: center; padding-top: 2em;">
	<table border="1px">
		<tr>
		<td>编号</td>
		<td>品名</td>
		<td>规格</td>
		<td>单位</td>
		<td>库存</td>
		</tr>
		<c:forEach items="item" var="i">		
		</c:forEach>
	</table>	
</div>
</head>
<body>

</body>
</html>