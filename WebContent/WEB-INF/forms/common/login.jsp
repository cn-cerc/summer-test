<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript">
//手机端处理
var browser = {
		versions : function() {
			var u = navigator.userAgent, app = navigator.appVersion;
			return {
				trident : u.indexOf('Trident') > -1, //IE内核
				presto : u.indexOf('Presto') > -1, //opera内核
				webKit : u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
				gecko : u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
				mobile : !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
				ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
				android : u.indexOf('Android') > -1 || u.indexOf('Adr') > -1, //android终端
				iPhone : u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
				iPad : u.indexOf('iPad') > -1, //是否iPad
				webApp : u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
				weixin : u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
				qq : u.match(/\sQQ/i) == " qq" //是否QQ
			};
		}(),
		language : (navigator.browserLanguage || navigator.language)
				.toLowerCase()
	}

	if (!(browser.versions.mobile || browser.versions.android
			|| browser.versions.ios || browser.versions.iPad)) {
		//不是移动端,跳转后台登录Form
		var loginMsg = "${loginMsg}";
		if (loginMsg != '') {
			window.location.href = "FrmAdminLogin?loginMsg=" + loginMsg;
		} else {
			window.location.href = "FrmAdminLogin";
		}
	}else if((browser.versions.mobile || browser.versions.android
			|| browser.versions.ios || browser.versions.iPad)){
		//多端登录设置(用户端和商家端为例) 缓存失效时会转发到本页面  在本页面获取上个页面的信息 并存储本页面的 地址
		var local=window.location.pathname;//获取缓存失效时的forms
		var str=local.split("forms/");
		 if(window.localStorage['CorpNo_']=='727111'){
			 window.localStorage.removeItem("local");  
			window.localStorage['local']=str[1];
			window.location.href = "LoginTech";
		} 
		
	}
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