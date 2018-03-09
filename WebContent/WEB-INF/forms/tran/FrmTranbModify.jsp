<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改商品</title>
</head>
<body>

	<div>
		<form action="FrmTran.updateTranb" method="post">
		商品编号<input type="text" name="code" value="${tranb.items.Code_}" readonly="readonly"/> <br />
		 品名<input type="text" name="desc" value="${tranb.items.Desc_}" readonly="readonly"/> <br />
		 规格<input type="text" name="spec" value="${tranb.items.Spec_}" readonly="readonly"/> <br /> 
		 单位<input type="text" name="unit" value="${tranb.items.Unit_}" readonly="readonly"/> <br /> 
		 数量<input type="text" name="num" value="${tranb.items.Num_}" /> <br /> 
		 <input type="hidden" name="tbNo" value="${tranb.items.TBNo_}" />
		    <input type="submit" value="保存" />    <a href="FrmTran.deleteTranb?code=${tranb.items.Code_ }&num=${tranb.items.Num_}&tbNo=${tranb.items.TBNo_}" >删除</a>
		</form>
	</div>
	<div style="padding: 0.5em;">
		<a href="FrmTran?tbNo=${tranb.items.TBNo_}">返回</a>
	</div>

</body>
</html>