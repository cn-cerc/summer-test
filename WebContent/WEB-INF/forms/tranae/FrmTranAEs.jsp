<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h2>盘点单单据查询</h2>
<table align="center" width="100%">
    <tr>
        <th>单据编号</th>
        <th>单据日期</th>
        <th>部门名称</th>
        <th>建档人员</th>
        <th>建档日期</th>
        <th>操作</th>
    </tr>
    <c:if test="${not empty items}">
    <c:forEach items="${items}" var="item">
    <tr>
        <th><a href="FrmTranAEInfo?tbno=${item.tbno}">${item.tbno}</a></th>
        <th>${item.tbDate}</th>
        <th>${item.deptName}</th>
        <th>${item.appUser}</th>
        <th>${item.appDate}</th>
        <th>
            <a href="FrmTranAEs.delete?tbno=${item.tbno}">删除</a>
        </th>
    </tr>
    </c:forEach>
    </c:if>
</table>
</div>
<div>${msg}</div>
<div align="left">
    <a href="FrmTranAEh">新增</a>
    <a href="FrmInvoicing">返回</a>
</div>
</body>
</html>