<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品的数据展示主页</title>
<style type="text/css">
td {
	border: 1px solid red;
	width: 300px;
	text-align: center;
}
</style>
</head>
<body>
	<div align="center">
	
		<div>
			<h3>商品信息管理</h3>
		</div>
		<table class="gridtable">
			<tr>
				<td style="width: 150px;">编号</td>
				<td>名称</td>
				<td>说明</td>
				<td>单位</td>
			</tr>
			<c:forEach var="item" items="${item}">
				<tr>
					<td>${item.corpNo}</td>
					<td>${item.code}</td>
					<td>${item.unit}</td>
					<td><a href="#">删除</a>|<a href="#">更改</a></td>
				<tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>