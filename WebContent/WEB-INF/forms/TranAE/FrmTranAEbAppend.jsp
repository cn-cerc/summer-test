<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src='js/jquery.js'></script>
<script type="text/javascript">
$(function(){
	$('input[name=Code]').on('blur',function(){
	    var param = {};
	    param.Code = $(this).val();
	    $.get("FrmTranAEbAppend.getStock",param,function(data){
	        $('input[name=Stock]').val(data.stock);
	    },'json');
	});
});
</script>
</head>
<body>
  <div align="center">
    <div>
      <h1>增加盘点单单身</h1>
      <form action="FrmTranAEbAppend.TranBPost" method="Post">
            <input type="hidden" name="tbno" value="${tbno}"/>
         <p>商品编号: <input type="text" name="Code" /></p>
         <p>库存数量: <input type="text" name="Stock" /></p>
         <p>盘点数量: <input type="text" name="Num" /></p>
         <input type="submit" name="submit" value="保存" />
         ${msg}
      </form>
    </div>
  </div>
  <div align="left">
    <a href="FrmTranAEbInfo?tbno=${item.tbno}">返回</a>
  </div>
</body>
</html>