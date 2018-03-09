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
		<form action="FrmCheck.updateCheckb" method="post">
		商品编号<input type="text" name="code" value="${checkb.items.Code_}" readonly="readonly"/> <br />
		 品名<input type="text" name="desc" value="${checkb.items.Desc_}" readonly="readonly"/> <br />
		 规格<input type="text" name="spec" value="${checkb.items.Spec_}" readonly="readonly"/> <br /> 
		 单位<input type="text" name="unit" value="${checkb.items.Unit_}" readonly="readonly"/> <br /> 
		 数量<input type="text" name="num" value="${checkb.items.NewStock_}" /> <br /> 
		 <input type="hidden" name="tbNo" value="${checkb.items.TBNo_}" />
		    <input type="submit" value="保存" />    <a href="FrmCheck.deleteCheckb?code=${checkb.items.Code_ }&num=${checkb.items.NewStock_}&tbNo=${checkb.items.TBNo_}" >删除</a>
		</form>
	</div>
	<div>${message }</div>
	<div style="padding: 0.5em;">
		<a href="FrmCheck?tbNo=${checkb.items.TBNo_}">返回</a>
	</div>

</body>
</html>