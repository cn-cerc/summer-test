<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>首页</title>
<script src="js/jquery.js"></script>
<script src="js/summer-1.2.1.js"></script>
<script>
var browser = null;

function showMessage(data){
    $("#error").text(data);
}

function onClick(){ 
    if(browser == null) return;
    
    if (browser.send("getClientId")) {
        alert(browser.getData());
    }else{
    	showMessage(browser.getMessage());
    }

    if (browser.support("getClientId")) {
        alert(browser.getData()); //取该函数描述
    }
}

window.onload = function(){
	browser = getBrowser();
	if(browser == null){
	    showMessage("then is not android or iphone.");
	}
}
</script>
</head>
<body>
	<div style="text-align: center; padding-top: 2em;">
		<a href="javascript:onClick()">测试</a>
	</div>
	<div id="error" style="font-color:red"></div>
</body>
</html>