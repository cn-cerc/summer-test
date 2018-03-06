<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>单头增加</title>
</head>
<body>
	<div>
		<form action="FrmTranhAppend">
			单号<input type="text" name="tbNo" value="${tranh.tbNo }" readonly="readonly"/> 日期 <input type="text" name="tbDate" value="${tranh.tbDate }" readonly="readonly" /> <br /> 
			厂商 <input type="text" name="superName" value="${tranh.supName }" /> <br /> 
			建档人员 <input type="text" name="appUser" value="${tranh.appUser }" readonly="readonly"/> <br /> 
			<input type="submit" value="保存" />
		</form>
	</div>
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
			<%-- <c:forEach items="${items}" var="item">
				<tr>
					<td>${item.code}</td>
					<td>${item.desc}</td>
					<td>${item.spec}</td>
					<td>${item.unit}</td>
					<td>${item.stock}</td>
					<td><a href="FrmProductDelete?code=${item.code}">删除</a></td>
					<td><a href="FrmProductModify?code=${item.code}">修改</a></td>
				</tr>
			</c:forEach> --%>

		</table>
	</div>
	
	
	<div style="padding: 0.5em;">
		<a href="">返回</a>
	</div>
	<div>${message}</div>
</body>
</html>