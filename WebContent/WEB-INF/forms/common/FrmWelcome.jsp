<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>欢迎页</title>
<script src="js/jquery.js"></script>
<script src="js/summer-1.2.1.js"></script>
<script>
    var browser = null; //android.webview

    function onTestClick() {
        if (browser == null)
            return;

        browser.req = {
                "homeUrl" : "FrmScanProduct",
                "viewUrl" : "FrmScanProduct.view",
                "postUrl" : "FrmScanProduct.save",
                "title" : "零售单扫描"
            };
        browser.req.title = "零售单扫描";

        if (browser.send("scanProduct")) {
            showMessage(browser.getData());
        } else {
            showMessage(browser.getMessage());
        }
    }

    window.onload = function() {
        browser = getBrowser();
        if (browser == null) {
            showMessage("this is not android or iphone.");
            return;
        }
    }
</script>
</head>
<body style="font-size: 2em;">
    <form action="FrmWelcome.onAppend" method="post">
        <input name="aaa" value=""></input>
        <input name="aaa2" value=""></input>
        <input name="aaa3" value=""></input>
        <button name="submit" value="append">append</button>
        <button name="submit" value="delete">delete</button>
    </form>
    <div style="text-align: center; padding-top: 2em;">
        <div>欢迎使用summer框架！</div>
        <div style="text-align: center; padding-top: 2em;">
            <a href="FrmDefault">进入首页,welcome ${name}</a>
        </div>
    </div>
    <div style="text-align: center; padding-top: 2em;">
        <div>
            <a href="FrmDataGrid">演示dataGrid表格</a>
            <long:grid>${grid1}</long:grid>
        </div>
    </div>
    <div style="text-align: center; padding-top: 2em;">
        <div>
            <a href="FrmClientTest">原生App功能测试</a>
        </div>
    </div>
    <div style="text-align: center; padding-top: 2em;">
        <div>
            <a href="FrmInvoicing">进销存测试Martin</a>
        </div>
    </div>

    <div style="text-align: center; padding-top: 2em;">
        <a href="javascript:onTestClick()">临时测试</a><br />
    </div>
    <div id="message" style="text-align: center; padding-top: 2em; font-color: red"></div>
</body>
</html>