<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品详细信息</title>
</head>
<body>


<div style="text-align: center;">
<table border="1" cellpadding="3" cellspacing="0" style="width: 60%;margin:auto">
	<caption align="top">商品信息</caption>


	<tr>
		<td>商品编号</td>
		<td>商品品名</td>
		<td>商品规格</td>
		<td>商品单位</td>
		<td>商品库存</td>
		<td>操作</td>
	</tr>

	<c:forEach items="${item}" var="item">
		<tr>
			<td>${item.code }</td>
			<td>${item.desc }</td>
			<td>${item.spec }</td>
			<td>${item.unit }</td>
			<td>${item.stock }</td>
			<td><a href="#">删除</a>/<a href="#">修改</a></td>
		</tr>
	</c:forEach>
	
</table>
</div>
<div>
	<a href="#">增加商品</a>
</div>
<div>
	<a href="#">返回</a>
</div>
</body>
</html>