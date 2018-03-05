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
<table>
	<tr>
		<td>商品编号</td>
		<td>商品品名</td>
		<td>商品规格</td>
		<td>商品单位</td>
		<td>商品库存</td>
	</tr>
	<c:forEach items="item" var="i">
		<tr>
			<td></td>
		</tr>
	</c:forEach>

</table>
</body>
</html>