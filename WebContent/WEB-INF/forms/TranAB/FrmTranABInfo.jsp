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
    <div>
      <h1>进货单维护</h1>
    </div>
    <table align="center" width="100%">
      <tr>      
        <th>进货单号</th>
        <th>进货单别</th>
        <th>单据日期</th>
        <th>厂商名称</th>
        <th>建档人员</th>
        <th>建档日期</th>
      </tr>
      <c:if test="${not empty items}">
      <c:forEach items="${items}" var="item">
      <tr>
         <td><a href="FrmTranABbInfo?tbno=${item.tbno}">${item.tbno}</a></td>
         <td>${item.tb}</td>
         <td>${item.tbdate}</td>
         <td>${item.supname}</td>
         <td>${item.appuser}</td>
         <td>${item.appdate}</td>
      </tr>
      </c:forEach>
      </c:if>
    </table>
  </div>
  <div align="left">
    <a href="FrmTranABAppend">新增</a>
    <a href="FrmInvoicing">返回</a>
  </div>
</body>
</html>