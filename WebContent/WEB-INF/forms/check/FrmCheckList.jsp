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
		<form action="FrmCheckhModify" method="post">
			单号<input type="text" name="tbNo" value="${checkh.items.TBNo_ }" readonly="readonly"/> 日期 <input type="text" name="tbDate" value="${checkh.items.TBDate_}" readonly="readonly" /> <br /> 
			部门<input type="text" name="deptName" value="${checkh.items.DeptName_ }" /> 
			建档人员 <input type="text" name="appUser" value="${checkh.items.AppUser_ }" readonly="readonly"/> <br /> 
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
				<td>当前库存</td>
				<td>实际库存</td>
				<td>操作</td>
			</tr>
				<tr>
				<c:forEach items="${Checkbs.records}" var="checkb">
					<td>${checkb.items.It_}</td>
					<td>${checkb.items.Code_}</td>
					<td>${checkb.items.Desc_}</td>
					<td>${checkb.items.Spec_}</td>
					<td>${checkb.items.Unit_}</td>
					<td>${checkb.items.Num_}</td>
					<td>${checkb.items.CurStock_}</td>	
					<td>${checkb.items.NewStock_}</td>			
				<td><a href="FrmCheckbModify?code=${checkb.items.Code_}&tbNo=${checkb.items.TBNo_}">内容</a></td>
				</c:forEach>
				</tr>

		</table>
	</div>
	
	<div>
		<a href="FrmCheckbAdd?tbNo=${checkh.items.TBNo_ }">新增</a>
	</div>
	<div style="padding: 0.5em;">
		<a href="FrmCheckhAdd?tbNo=${checkh.items.TBNo_ }">返回</a>
	</div>
	<div>${message}</div>
</body>
</html>