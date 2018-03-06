<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品资料增加</title>
</head>
<body>
  <div align="center">
    <div>
      <h1>增加商品</h1>
      <form action="FrmPartAppend" method="Post">
         <p>商品料号: <input type="text" name="Code" /></p>
         <p>商品品名: <input type="text" name="Desc" /></p>
         <p>商品规格: <input type="text" name="Spec" /></p>
         <p>商品单位: <input type="text" name="Unit" /></p>
         <input type="submit" name="submit" value="保存" />
      </form>
    </div>
  </div>
  <div align="left">
    <a href="FrmPartinfo">返回</a>
</div>
</body>
</html>