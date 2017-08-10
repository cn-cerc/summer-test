<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>原生App功能测试</title>
<script src="js/jquery.js"></script>
<script src="js/summer-1.2.1.js"></script>
<script>
    var browser = null;

    function showMessage(data) {
        $("#error").text(data);
    }

    function onClick() {
        if (browser == null)
            return;

        if (browser.send("getClientVersion")) {
            alert(browser.getData());
        } else {
            showMessage(browser.getMessage());
        }
    }

    window.onload = function() {
        browser = getBrowser();
        if (browser == null) {
            showMessage("then is not android or iphone.");
        }
    }
</script>
<style>
.h4 {
    padding-bottom: 9px;
    margin: 40px 0 20px;
    border-bottom: 1px solid #eee;
}

h4 {
    font-size: 18px;
    margin-top: 10px;
    margin-bottom: 10px;
    font-family: inherit;
    font-weight: 500;
    line-height: 1.1;
    color: inherit;
}
</style>
</head>
<body>
    <div style="text-align: center; padding-top: 2em;">
        <a href="javascript:onClick()">测试</a>
    </div>
    <div id="error" style="font-color: red"></div>

    <div class="h4">
        <h4>App函数功能测试</h4>
    </div>

    <form method="post" action="FrmClientTest.debug">
        <div>
            <label>函数名称</label>
            <select id="classCode" name="classCode">
                <c:forEach items="${dataSet.records}" var="record" varStatus="it">
                    <option value="${record.items.Code_}" ${record.items.Selected_}>${record.items.Name_}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label>函数说明</label>
            <div>getClinetID用于获取设备的硬件di码</div>
            <div>参数传递：无</div>
        </div>

        <div>
            <label>调用参数</label>
            <textarea rows="15" cols="100" placeholder="函数接口需要的参数"></textarea>
        </div>

        <div>
            <button>执行</button>
        </div>
    </form>

    <div class="h4">
        <h4>执行结果</h4>
    </div>
    <div>n_868568025516789</div>
</body>
</html>