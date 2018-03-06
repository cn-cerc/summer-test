<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品详细信息</title>
</head>

<body>

	<div style="align-content: center">
		<table border="1px">
			<tr>
				<td>编号</td>
				<td>品名</td>
				<td>规格</td>
				<td>单位</td>
				<td>库存</td>
				<td colspan="3">操作</td>
			</tr>
			<c:forEach items="${items}" var="item">
				<tr>
					<td>${item.code}</td>
					<td>${item.desc}</td>
					<td>${item.spec}</td>
					<td>${item.unit}</td>
					<td>${item.stock}</td>
					<td><a href="FrmProductDelete?code=${item.code}">删除</a></td>
					<td><a href="FrmProductModify?code=${item.code}">修改</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<div>
		<a href="FrmProductAdd">增加</a>
	</div>
	<div style="padding: 0.5em;">
		<a href="common/FrmInvoicing">返回</a>
	</div>

</body>
</html>