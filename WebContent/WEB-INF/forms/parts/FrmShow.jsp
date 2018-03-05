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
			</tr>
		<%-- 	<c:forEach items="item" var="i"> --%>
				<tr>
					<td>${ item.code}</td>
					<%-- <td>${ item.desc}</td>
					<td>${ item.spec}</td>
					<td>${ item.nuit}</td>
					<td>${ item.stock}</td> --%>
				</tr>
<%-- 
			</c:forEach> --%>

		</table>
	</div>

	<div style="align-content: left">
		<a href="FrmProductModify"></a>
	</div>

	<div style="padding: 0.5em;">
		<a href="javascript:history.go(-1)">返回</a>
	</div>

</body>
</html>