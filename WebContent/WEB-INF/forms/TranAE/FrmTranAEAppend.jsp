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
      <h1>增加盘点单</h1>
      <form action="FrmTranAEAppend" method="Post">
         <p>盘点单号: <input type="text" name="TBNo" value="AE" /></p>
         <p>单据日期: <input type="text" name="TBDate" readonly="readonly" value="${TBDate}"/></p>
         <p>部门名称: <input type="text" name="DeptName" /></p>
         <p>建档人员: <input type="text" name="AppUser" /></p>
         <input type="submit" name="submit" value="保存" />
         ${msg}
      </form>
    </div>
  </div>
  <div align="left">
    <a href="FrmTranAEInfo">返回</a>
  </div>
</body>
</html>