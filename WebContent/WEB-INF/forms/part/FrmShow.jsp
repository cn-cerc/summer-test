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
					<td>${item.items.Code_}</td>
					<td>${item.items.Desc_}</td>
					<td>${item.items.Spec_}</td>
					<td>${item.items.Unit_}</td>
					<td>${item.items.Stock_}</td>
					<td><a href="FrmProduct.delete?code=${item.items.Code_}">删除</a></td>
					<td><a href="FrmProduct.modify?code=${item.items.Code_}">修改</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div>
		<a href="FrmProduct.add">增加</a>
	</div>
	<div style="padding: 0.5em;">
		<a href="FrmInvoicing">返回</a>
	</div>

</body>
</html>