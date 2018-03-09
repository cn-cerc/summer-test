<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品资料</title>
</head>
<body>
<body>
<div align="center">
    <div>
        <h1>修改商品资料</h1>
        <form action="FrmPartModify.modify" method="post">
            <p>商品编号：<input type="text" name="Code" value="${item.code}"  readonly="readonly"></input></p>
            <p>商品名称：<input type="text" name="Desc" value="${item.desc}"></input></p>
            <p>商品规格：<input type="text" name="Spec" value="${item.spec}"></input></p>
            <p>商品单位：<input type="text" name="Unit" value="${item.unit}"></input></p>
            <input type="submit" name="submit" value="保存"></input>
            <p><a href="FrmPartInfo">返回</a></p>
        </form>
    </div>
</div>
</body>
</body>
</html>