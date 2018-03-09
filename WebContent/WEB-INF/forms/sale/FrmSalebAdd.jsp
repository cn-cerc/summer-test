<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript" src="../../../forms/js/jquery.js"></script>

<head>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>单头增加</title>
</head>
<body>
	<div>
		<form action="FrmSale.appendSaleb" method="post">
			商品编号<select name="code" id="ProductCode">
				<option  >----请选择----</option>
				<c:forEach items="${items}" var="item">
				<option value="${item.items.Code_}">${item.items.Code_}--${item.items.Desc_}</option>
				</c:forEach>
				
			</select> <br /> 数量<input type="text" name="stock" value="" /> <br />
			<input type="hidden" name="tbNo" value="${tbNo }"/> <input type="submit" value="保存" />
		</form>
	</div>
	<div>${message}</div>
	<div style="padding: 0.5em;">
		<a href="FrmSale?tbNo=${tbNo}">返回</a>
	</div>
</body>
</html>