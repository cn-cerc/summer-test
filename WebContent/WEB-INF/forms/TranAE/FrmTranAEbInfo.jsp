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
      <h1>盘点单资料维护</h1>
    </div>
      <form action="FrmTranAEbInfo.submit" method="Post">  
         
      <table align="center" width="90%">   
   
         <tr>
           <th>          
             <p>盘点单号: <input type="text" name="TBNo" readonly="readonly" value="${record.items.TBNo_}"/></p>
           </th>
           <th>
             <p>单据日期: <input type="text" name="TBDate" readonly="readonly" value="${record.items.TBDate_}"/></p>
           </th>
         </tr> 
         <tr>
           <th>  
             <p>部门名称: <input type="text" name="DeptName"  value="${record.items.DeptName_}"/></p>
           </th>
           <th>
             <p>建档人员: <input type="text" name="AppUser" readonly="readonly" value="${record.items.AppUser_}"/></p>   
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
       ${msg}
       </form> 
    <hr width=90% size=3 color=#5151A2 style="border:3 double green">
    <table align="center" width="90%">
      <tr>      
        <th>序</th>
        <th>商品料号</th>
        <th>商品品名</th>
        <th>商品规格</th>
        <th>商品单位</th>
        <th>原库存</th>
        <th>调整数量</th>
        <th>当前库存</th>
        <th>操作</th>
      </tr>
      <c:if test="${not empty dataSet}">
      <c:forEach items="${dataSet}" var="item">
      <tr>
          <td align="center" >${item.items.It_}</td>
          <td align="center" >${item.items.Code_}</td>
          <td align="center" >${item.items.Desc_}</td>
          <td align="center" >${item.items.Spec_}</td>
          <td align="center" >${item.items.Unit_}</td>
          <td align="center" >${item.items.CurStock_}</td>
          <td align="center" >${item.items.Num_}</td>
          <td align="center" >${item.items.Stock_}</td>
          <td align="center" >
             <a href="FrmTranAEbModify?tbno=${item.items.TBNo_}&it=${item.items.It_}">内容</a> | 
          </td>
      </tr>
      </c:forEach>
      </c:if>
    </table>
    
  </div>
  <div align="left">
    <a href="FrmTranAEbAppend?tbno="${record.items.TBNo_}>新增</a>
    <a href="FrmTranAEInfo">返回</a>
</div>
</body>
</html>