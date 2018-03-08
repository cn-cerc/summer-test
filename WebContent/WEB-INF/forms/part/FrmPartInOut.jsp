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
<h2>商品进出查询条件</h2>
</div>
<form action="FrmPartInOut" method="post">
<table align="center" width="50%">
    <tr>
        <th><p>商品信息：<input type="text" name="PartCode"  ></input></p></th>
        <th><p>单据单号：<input type="text" name="TBNo"></input></p></th>
    </tr>
    <tr>
        <th><p>开始日期：<input type="text" name="TBDateFrm"></input></p></th>
        <th><p>截止日期：<input type="text" name="TBDateTo"></input></p></th>
    </tr>
    <tr>
        <th><p>往来对象：<input type="text" name="CupSup" ></input></p></th>
        <th><p><input type="submit" name="submit" value="搜索"></input></p></th>
    </tr>
</table>
<div>
    <hr style="height:5px;border:none;border-top:5px ridge green;" />
</div>
<div align="center">
<h2>商品进出异动数据</h2>
<table  width="100%">
    <tr>
        <th>异动日期</th>
        <th>异动单号</th>
        <th>序号</th>
        <th>商品编号</th>
        <th>商品名称</th>
        <th>商品规格</th>
        <th>商品单位</th>
        <th>入库数量</th>
        <th>出库数量</th>
        <th>调整数量</th>
        <th>当前库存</th>
        <th>往来厂商</th>
        <th>往来客户</th>
        <th>往来部门</th>
        <th>操作人员</th>
    </tr>
    <c:if test="${not empty dataSet}">
    <c:forEach items="${dataSet.records}" var="item">
    <tr>
        <th></th>
          <td align="left" >${item.items.TBDate_}</td>
          <td align="left" >${item.items.TBNo_}</td>
          <td align="left" >${item.items.It_}</td>
          <td align="left" >${item.items.Code_}</td>
          <td align="left" >${item.items.Desc_}</td>
          <td align="left" >${item.items.Spec_}</td>
          <td align="left" >${item.items.Unit_}</td>
          <td align="left" >${item.items.InNum_}</td>
          <td align="left" >${item.items.OutNum_}</td>
          <td align="left" >${item.items.AENum_}</td>
          <td align="left" >${item.items.Stock_}</td>
          <td align="left" >${item.items.SupName_}</td>
          <td align="left" >${item.items.CusName_}</td>
          <td align="left" >${item.items.DeptName_}</td>
          <td align="left" >${item.items.AppUser_}</td>
    </tr>
    </c:forEach>
    </c:if>

</table>
</div>
</form>

<div>${msg}</div>
<div align="left">
    <a href="FrmInvoicing">返回</a>
</div>
</body>
</html>