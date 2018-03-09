<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售单增加</title>
</head>

<body>
<body>

<div align="center">
    <div>
        <h1>增加销售单头数据</h1>
        <form action="FrmTranBCh" method="post">
            <p>销售单号：<input type="text" name="TBNo" value="BC"></input></p>
            <p>单据日期：<input type="text" name="TBDate" value="${TBDate}"></input></p>
            <p>客户名称：<input type="text" name="CusName"></input></p>
            <p>部门名称：<input type="text" name="DeptName"></input></p>
            <input type="submit" name="submit" value="保存"></input>
            <p><a href="FrmTranBCs">返回</a></p>
        </form>
    </div>
</div>
</body>
</body>
</html>