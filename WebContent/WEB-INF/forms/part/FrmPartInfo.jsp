<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品资料维</title>
</head>
<body>
<div align="center">
<h2>商品资料维</h2>
<table align="center" width="100%">
    <tr>
        <th>商品编号</th>
        <th>商品品名</th>
        <th>商品规格</th>
        <th>商品单位</th>
        <th>商品库存</th>
        <th>操作</th>
    </tr>
    <c:if test="${not empty items}">
    <c:forEach items="${items}" var="item">
    <tr>
        <th>${item.code}</th>
        <th>${item.desc}</th>
        <th>${item.spec}</th>
        <th>${item.unit}</th>
        <th>${item.stock}</th>
        <th>
            <a href="FrmPartModify?code=${item.code}">修改</a> | 
            <a href="FrmPartInfo.delete?code=${item.code}">删除</a>
        </th>
    </tr>
    </c:forEach>
    </c:if>
</table>
</div>
<div>${msg}</div>
<div align="left">
    <a href="FrmPartAppend">新增</a>
    <a href="FrmInvoicing">返回</a>
</div>
</body>
</html>