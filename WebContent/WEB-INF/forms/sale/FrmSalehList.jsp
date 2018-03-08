<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<div style="align-content: center">
		<table border="1px">
			<tr>
				<td>单据类别</td>
				<td>单据编号</td>
				<td>单据日期</td>
				<td>客户</td>
				<td>建档人员</td>
				<td>建档时间</td>
			</tr>
			<c:forEach items="${salehs.records}" var="saleh">
				<tr>
					<td>${saleh.items.TB_ }</td>
					<td><a href="FrmSaleList?tbNo=${saleh.items.TBNo_ }">${saleh.items.TBNo_ }</a></td>
					<td>${saleh.items.TBDate_}</td>
					<td>${saleh.items.CusName_}</td>
					<td>${saleh.items.AppUser_}</td>
					<td>${saleh.items.AppDate_}</td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<div>
		<a href="FrmSalehAdd">增加</a>
	</div>
	<div style="padding: 0.5em;">
		<a href="FrmInvoicing">返回</a>
	</div>

</body>
</html>