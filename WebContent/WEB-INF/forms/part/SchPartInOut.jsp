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
      <h1>商品进出库存查询</h1>
    </div>
      <form action="SchPartInOut" method="Post">  
         
      <table align="center" width="90%">   
   
         <tr>
           <th>          
             <p>单据单号: <input type="text" name="TBNo" /></p>
           </th>
           <th>
             <p>商品查询: <input type="text" name="PartSchText" /></p>
           </th>
         </tr> 
         <tr>
           <th>
             <p>往来查询: <input type="text" name="CupSupSchText" /></p>
           </th>
         </tr> 
         <tr>
           <th>  
             <p>单据日期从: <input type="text" name="TBDateFM"/></p> 
           </th>
           <th>              
             <p>单据日期至: <input type="text" name="TBDateTO"/></p>  
           </th>       
         </tr> 
         <tr>
            <th> 
            </th>
           <th align="center">
             <input type="submit" name="submit" value="查询" />
           </th>
         </tr> 
       </table>
       ${msg}
       </form>
    <hr width=100% size=3 color=#5151A2 style="border:3 double green">
    <table align="center" width="100%">
      <tr>  
        <th>单据单号</th> 
        <th>单据序号</th>
        <th>单据日期</th> 
        <th>往来单位</th> 
        <th>商品料号</th>
        <th>商品品名</th>
        <th>商品规格</th>
        <th>商品单位</th>
        <th>入库数量</th>
        <th>出库数量</th>
        <th>调整数量</th>
        <th>当前库存</th>
      </tr>
      <c:if test="${not empty dataSet}">
      <c:forEach items="${dataSet}" var="item">
      <tr>
          <td align="center" >${item.items.TBNo_}</td>
          <td align="center" >${item.items.It_}</td>
          <td align="center" >${item.items.TBDate_}</td>
          <td align="center" >${item.items.ObjName_}</td>          
          <td align="center" >${item.items.Code_}</td>
          <td align="center" >${item.items.Desc_}</td>
          <td align="center" >${item.items.Spec_}</td>
          <td align="center" >${item.items.Unit_}</td>
          <td align="center" >${item.items.InNum_}</td>
          <td align="center" >${item.items.OutNum_}</td>
          <td align="center" >${item.items.AENum_}</td>
          <td align="center" >${item.items.Stock_}</td>
      </tr>
      </c:forEach>
      </c:if>
    </table>
  </div>
  <div align="left">
    <a href="FrmInvoicing">返回</a>
</div>
</body>
</html>