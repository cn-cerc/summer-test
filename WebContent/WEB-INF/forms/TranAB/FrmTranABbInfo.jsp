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
      <h1>进货单资料维护</h1>
    </div>
      <form action="FrmTranABbInfo.submit" method="Post">     
      <table align="center" width="90%">   
   
         <tr>
           <th>          
             <p>进货单号: <input type="text" name="TBNo" readonly="readonly" value="${item.tbno}"/></p>
           </th>
           <th>
             <p>单据日期: <input type="text" name="TBDate" readonly="readonly" value="${item.tbdate}"/></p>
           </th>
         </tr> 
         <tr>
           <th>  
             <p>厂商名称: <input type="text" name="SupName" value="${item.supname}"/></p>       
           </th>
           <th>
             <p>建档人员: <input type="text" name="AppUser" readonly="readonly" value="${item.appuser}"/></p>   
           </th>       
         </tr>
         <tr>
            <th> 
            </th>
           <th align="center">
             <input type="submit" name="submit" value="保存" />
           </th>
         </tr> 
       </table>
       </form> 
    <hr width=90% size=3 color=#5151A2 style="border:3 double green">
    <table align="center" width="90%">
      <tr>      
        <th>序</th>
        <th>商品料号</th>
        <th>商品品名</th>
        <th>商品规格</th>
        <th>商品单位</th>
        <th>商品数量</th>
        <th>操作</th>
      </tr>
      <c:if test="${not empty items}">
      <c:forEach items="${items}" var="item">
      <tr>
          <td align="center" >${item.it}</td>
          <td align="center" >${item.code}</td>
          <td align="center" >${item.desc}</td>
          <td align="center" >${item.spec}</td>
          <td align="center" >${item.unit}</td>
          <td align="center" >${item.num}</td>
          <td align="center" >
             <a href="FrmTranABbModify?tbno=${item.tbno}&it=${item.it}">内容</a> | 
          </td>
      </tr>
      </c:forEach>
      </c:if>
    </table>
  </div>
  <div align="left">
    <a href="FrmTranABbAppend?tbno=${item.tbno}">新增</a>
    <a href="FrmTranABInfo">返回</a>
</div>
</body>
</html>