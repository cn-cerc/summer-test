<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加进货单单身数据FrmTranBAB</title>
</head>
</head>
<body>

<div align="center">
    <div>
        <h1>增加进货单单身数据</h1>
        <form action="FrmTranABb.appendTranB" method="post">
            <p>单据编号：<input type="text" name="TBNo" value="${item.tbno}"  readonly="readonly"></input></p>
            <p>单身序号：<input type="text" name="It"></input></p>
            <p>商品编号：<input type="text" name="Code"></input></p>
            <p>进货数量：<input type="text" name="Num"></input></p>
            <input type="submit" name="submit" value="保存"></input>
            <p><a href="FrmTranABInfo?tbno=${item.tbno}">返回</a></p>
        </form>
    </div>
</div>

</body>
</html>