<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div align="center">
    <div>
      <h1>增加进货单</h1>
      <form action="FrmTranABAppend" method="Post">
         <p>进货单号: <input type="text" name="TBNo" /></p>
         <p>厂商名称: <input type="text" name="SupName" /></p>
         <p>单据日期: <input type="text" name="TBDate" readonly="readonly" value="${TBDate}"/></p>
         <p>建档人员: <input type="text" name="AppUser" /></p>
         <input type="submit" name="submit" value="保存" />
         ${msg}
      </form>
    </div>
  </div>
  <div align="left">
    <a href="FrmTranABInfo">返回</a>
  </div>
</body>
</html>