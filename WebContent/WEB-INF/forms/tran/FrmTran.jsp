<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>单头增加</title>
</head>
<body>
	<div>
		<form action="FrmTranhModify" method="post">
			单号<input type="text" name="tbNo" value="${tranh.tbNo }" readonly="readonly"/> 日期 <input type="text" name="tbDate" value="${tranh.tbDate }" readonly="readonly" /> <br /> 
			厂商 <input type="text" name="supName" value="${tranh.supName }" /> 
			建档人员 <input type="text" name="appUser" value="${tranh.appUser }" readonly="readonly"/> <br /> 
			<input type="submit" value="保存" />
		</form>
	</div>
	<div style="align-content: center">
		<table border="1px">
			<tr>
			    <td>序号</td>
				<td>编号</td>
				<td>品名</td>
				<td>规格</td>
				<td>单位</td>
				<td>数量</td>
				<td>操作</td>
			</tr>
				<tr>
				<c:forEach items="${tranbs}" var="tranb">
					<td>${tranb.it}</td>
					<td>${tranb.code}</td>
					<td>${tranb.desc}</td>
					<td>${tranb.spec}</td>
					<td>${tranb.unit}</td>
					<td>${tranb.num}</td>		
				<td><a href="FrmTranbModify?code=${tranb.code}&tbNo=${tranh.tbNo }">内容</a></td>
				</c:forEach>
				</tr>

		</table>
	</div>
	
	<div>
		<a href="FrmTranbAdd?tbNo=${tranh.tbNo }">新增</a>
	</div>
	<div style="padding: 0.5em;">
		<a href="FrmTrachAdd?tbNo=${tranh.tbNo }">返回</a>
	</div>
	<div>${message}</div>
</body>
</html>