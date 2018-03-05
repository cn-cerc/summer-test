<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <div>
      <table>
        <tr>
           <td>编号</td>
           <td>品名</td>
           <td>规格</td>
           <td>单位</td>
           <td>库存</td>
        </tr>
        <c:forEach items="${item }" var="item">
             <tr>
               <td>${item.Code_}</td>
               <td>${item.Desc_}</td>
               <td>${item.Spec_}</td>
               <td>${item.Unit_}</td>
               <td>${item.Stock_}</td>
             </tr>              
        </c:forEach>
      </table>   
   </div>
</body>
</html>