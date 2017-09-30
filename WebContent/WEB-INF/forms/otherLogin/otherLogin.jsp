<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript">
$(function() {
	var logmsg = '${requestScope.loginMsg }';
	if (logmsg && logmsg != '') {
		if (logmsg == '账户禁用') {
			$("#msg").text("您的账户已经被禁用,请拨打客户电话400-888-3198咨询")

		} else {
			winBox(logmsg);
		}
		if ('${ sessionScope.device }' == 'iphone') {
			window.webkit.messageHandlers.webViewApp.postMessage({
				"type" : "clearLogin"
			});
			//webViewApp("login", phnoeNub, password); 
		}
	}
	if (window.history && window.history.pushState) {
        $(window).on('popstate', function () {
            var hashLocation = location.hash;
            var hashSplit = hashLocation.split("#!/");
            var hashName = hashSplit[1];
            if (hashName !== '') {
                var hash = window.location.hash;
                if (hash === '') {
                	location.reload();
                }
            }
        });
        window.history.pushState('forward', null, '');
    }
	
	$("#header span").on("click", function() {
		window.location.href = "VerificationLogin";
	});

	$("#register").on("click", function() {
		window.location.href = "FrmPhoneRegistered";
	});

	if (window.localStorage['LoginName']) {
		$("#login_usr").val(window.localStorage['LoginName']);
	}
	//本地存储用户端
	/* var CorpNo_=$("#CorpNo_").val();
	localStorage.setItem("CorpNo_",CorpNo_);//设置b为"isaac"  */
	
	window.localStorage['CorpNo_'] = '727111';
	
	if(window.localStorage['local']){
		$("#form").attr("action",window.localStorage['local']);//设置页面form表单的action
		window.localStorage.removeItem("local"); //移除属性避免首次登陆界面跳转
	}
	
});
</script>
<title>登录页面</title>
</head>
<body>
	<div style="text-align: center; padding-top: 2em;">
		<div>请输入用户名：</div>
		<input name="userCode" id="userCode" />
	</div>
</body>
</html>