<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BC单身详细单笔数据 Insert title here</title>
</head>
<body>

<div align="center">
    <div>
        <h1>单身详细单笔数据</h1>
        <form action="FrmTranBCdetail.modify" method="post">
            <p>单据编号：<input type="text" name="TBNo" value="${item.tbno}"  readonly="readonly"></input></p>
            <p>单身序号：<input type="text" name="It" value="${item.it}"  readonly="readonly"></input></p>
            <p>商品编号：<input type="text" name="Code" value="${item.code}"  readonly="readonly"></input></p>
            <p>商品名称：<input type="text" name="Desc" value="${item.desc}"  readonly="readonly"></input></p>
            <p>商品规格：<input type="text" name="Spec" value="${item.spec}"  readonly="readonly"></input></p>
            <p>商品单位：<input type="text" name="Unit" value="${item.unit}"  readonly="readonly"></input></p>
            <p>销售数量：<input type="text" name="Num" value="${item.num}" ></input></p>
            <input type="submit" name="delete" value="删除"></input>
            <input type="submit" name="save" value="保存"></input>
            <p><a href="FrmTranBCInfo?tbno=${item.tbno}">返回</a></p>
        </form>
    </div>
</div>

</body>
</html>