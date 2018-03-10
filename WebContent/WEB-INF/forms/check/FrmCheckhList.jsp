<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>	
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
				<td>部门</td>
				<td>建档人员</td>
				<td>建档时间</td>
			</tr>
			<c:forEach items="${checkhs}" var="checkh">
				<tr>
					<td>${checkh.items.TB_ }</td>
					<td><a href="FrmCheck?tbNo=${checkh.items.TBNo_ }">${checkh.items.TBNo_ }</a></td>
					<td><fmt:formatDate value="${checkh.items.TBDate_}" pattern="yyyy-MM-dd "/></td>
					<td>${checkh.items.DeptName_}</td>
					<td>${checkh.items.AppUser_}</td>
					<td><fmt:formatDate value="${checkh.items.AppDate_}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<div>
		<a href="FrmCheck.addCheckh">增加</a>
	</div>
	<div style="padding: 0.5em;">
		<a href="FrmInvoicing">返回</a>
	</div>

</body>
</html>