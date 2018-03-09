<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<form action="FrmInAndOutSearch" method="post">
		<div>
			单号<input type="text" name="tbNo" value="${tbNo }" /> 商品搜索<input
				type="text" name="productName" value="${productName }" />
		</div>
		<div>
			对象<input type="text" name="object" value="${object}" /> 日期范围<input
				type="text" name="startTime" value="${startTime }" />--<input
				type="text" name="endTime" value="${endTime }" />
		</div>
		<div>
			搜索<input type="submit" name="submit" value="提交" />
		</div>
	</form>

	<table border="1px">
		<tr>
			<td>单号</td>
			<td>序</td>
			<td>对象</td>
			<td>日期</td>
			<td>商品编号</td>
			<td>品名</td>
			<td>规格</td>
			<td>单位</td>
			<td>入库数</td>
			<td>出库数</td>
			<td>调整数</td>
		</tr>
		<c:forEach items="${dataSet}" var="item">
			<tr>
				<td>${item.items.TBNo_ }</td>
				<td>${item.items.It_ }</td>
				<td>${item.items.ObjName_ }</td>
				<td><fmt:formatDate value="${item.items.TBDate_ }"
						pattern="yyyy-MM-dd " /></td>
				<td>${item.items.Code_}</td>
				<td>${item.items.Desc_ }</td>
				<td>${item.items.Spec_ }</td>
				<td>${item.items.Unit_ }</td>
				<td>${item.items.InNum_ }</td>
				<td>${item.items.OutNum_ }</td>
				<td>${item.items.AENum_ }</td>
			</tr>
		</c:forEach>
	</table>
	<div style="padding: 0.5em;">
		<a href="FrmInvoicing">返回</a>
	</div>
</body>
</html>