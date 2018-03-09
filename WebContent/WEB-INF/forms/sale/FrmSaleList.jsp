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
		<form action="FrmSale.modifySaleh" method="post">
			单号<input type="text" name="tbNo" value="${saleh.items.TBNo_ }" readonly="readonly"/> 日期 <input type="text" name="tbDate" value="${saleh.items.TBDate_ }" readonly="readonly" /> <br /> 
			客户 <input type="text" name="cusName" value="${saleh.items.CusName_ }" /> 
			建档人员 <input type="text" name="appUser" value="${saleh.items.AppUser_ }" readonly="readonly"/> <br /> 
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
				<c:forEach items="${salebs}" var="saleb">
					<td>${saleb.items.It_}</td>
					<td>${saleb.items.Code_}</td>
					<td>${saleb.items.Desc_}</td>
					<td>${saleb.items.Spec_}</td>
					<td>${saleb.items.Unit_}</td>
					<td>${saleb.items.Num_}</td>		
				<td><a href="FrmSale.modifySaleb?code=${saleb.items.Code_}&tbNo=${saleb.items.TBNo_ }">内容</a></td>
				</c:forEach>
				</tr>

		</table>
	</div>
	
	<div>
		<a href="FrmSale.addSaleb?tbNo=${saleh.items.TBNo_}">新增</a>
	</div>
	<div style="padding: 0.5em;">
		<a href="FrmSale.listSaleh">返回</a>
	</div>
	<div>${message}</div>
</body>
</html>