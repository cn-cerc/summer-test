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
      <h1>进货单单身修改</h1>
      <form action="FrmTranABbModify.TranBPost" method="Post">
            <input type="hidden" name="tbno" value="${tbno}"/>
         <p>进货单序: <input type="text" name="It" readonly="readonly" value="${item.it}"/></p>
         <p>商品编号: <input type="text" name="Code" readonly="readonly" value="${item.code}"/></p>
         <p>商品品名: <input type="text" name="Desc" readonly="readonly" value="${item.desc}"/></p>
         <p>商品规格: <input type="text" name="Spec" readonly="readonly" value="${item.spec}"/></p>
         <p>商品单位: <input type="text" name="Unit" readonly="readonly" value="${item.unit}"/></p>
         <p>商品数量: <input type="text" name="Num"  value="${item.num}"/></p>
         <input type="submit" name="submit" value="保存" />
         <input type="submit" name="delete" value="删除" />
         ${msg}
      </form>
    </div>
  </div>
  <div align="left">
    <a href="FrmTranABbInfo?tbno=${item.tbno}">返回</a>
  </div>
</body>
</html>