<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Android/IPhone 功能测试</title>
<script src="js/jquery.js"></script>
<script src="js/summer-1.2.1.js"></script>
<script>
	var browser = null; //android.webview

    function onClick() {
	    if (browser == null)
		    return;

	    browser.req = {"url":"http://pic.4j4j.cn/upload/pic/20130617/55695c3c95.jpg"};
	    if (browser.send("PlayImage")) {
	    	$("#funcResult").text(browser.getData());
	    } else {
		    showMessage(browser.getMessage());
	    }
    }

    function onSelectItem(classCode) {
	    if (browser) {
	        if(browser.support(classCode)){
	        	$("#funcName").text(browser.getData());
	        }else{
	        	showMessage(browser.getMessage());
	        }
	    }
    }
    
    function onClickTest(){
    	var classCode = $("#funcList").val();
    	if (browser) {
    		var params = $("#params").val();
    		if(params){
    			console.log(params);
    			try{
    			  browser.req = JSON.parse(params);
    			}catch(e){
                    $("#funcResult").text(e.message);
    				return;
    			}
    		}
            if(browser.send(classCode)){
                $("#funcResult").text(browser.getData());
            }else{
            	$("#funcResult").text(browser.getMessage());
            }
        }
    }

    window.onload = function() {
	    browser = getBrowser();
	    if (browser == null) {
		    showMessage("this is not android or iphone.");
		    return;
	    }
	    var items = browser.list();
	    if (items) {
	    	var i = 0;
		    for ( var key in items) {
			    $("#funcList").append("<option value='" + key + "'>" + items[key] + "</option>");
			    i = i+1;
			    if(i == 1){
			    	$("#funcList").val(key);
			    	 if (browser && browser.support(key)){
		                 $("#funcName").text(browser.getData());
			    	 }
			    }
		    }
	    }
    }
</script>
<style>
</style>
</head>
<body style="font-size: 2em">
    <div>
        <label>请选择要测试功能：</label>
        <br /> &nbsp;&nbsp;&nbsp;&nbsp;<select id="funcList" style="font-size: 1em"
            onchange="onSelectItem(this.options[this.options.selectedIndex].value)">
        </select>
        <button style="font-size: 1em" onclick="onClickTest()">执行</button>
    </div>

    <div>
        <label>函数说明：</label>
        <br /> &nbsp;&nbsp;&nbsp;&nbsp;
        <label id="funcName"></label>
    </div>

    <div style="font-size: 1em">
        <label>调用参数：</label>
        <br /> &nbsp;&nbsp;&nbsp;&nbsp;
        <textarea id="params" style="font-size: 1em" rows="3" cols="40" placeholder="（函数接口需要的参数）"></textarea>
    </div>

    <div>
        <label>执行结果：</label>
        <br /> &nbsp;&nbsp;&nbsp;&nbsp;
        <label id="funcResult"></label>
    </div>
    <div id="message" style="font-color: red"></div>
</body>
</html>