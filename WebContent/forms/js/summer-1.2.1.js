/**
 * 配合summer-android, summer-iphone项目使用
 */

function AndroidProxy() { // 安卓代理，对应android源码中的JavaScriptProxy
	this.active = false; // 标识为true时，表示当前为summer-android专用浏览器
	this.machine = null; // 当前的JavaScriptProxy对象
	this.req = {}; // 请求参数
	this.resp = {}; // 返回参数
	this.message = null; // 返回的消息

	if (window.JSobj) { // 兼容老的JSobj
		this.machine = window.JSobj;
		this.active = true;
	}
	if (window.jsAndroid) { // 原JSobj改名为jsAndroid
		this.machine = window.jsAndroid;
		this.active = true;
	}

	this.support = function(classCode) { // 判断是否支持指定的功能函数
		if (!this.active) {
			this.message = "您的版本太旧，不支持指令：" + classCode;
			return false;
		}
		try {
			var dataOut = this.machine.support(classCode);
			this.resp = JSON.parse(dataOut);
			if (!this.resp.result && this.resp.message)
				this.message = this.resp.message;
			return this.resp.result;
		} catch (e) {
			this.message = "您的版本太旧，不支持指令：support";
			return false;
		}
	}

	this.send = function(classCode) {// 调用 android 中的功能函数，并立即返回
		if (!this.active)
			return null;
		try {
			var dataOut = this.machine.send(classCode, JSON.stringify(this.req));
			this.resp = JSON.parse(dataOut);
			return this.resp.result;
		} catch (e) {
			this.resp = {
			    "result" : true,
			    "message" : e.getMessage()
			};
			return this.resp.result;
		}
	}

	this.getData = function() { // 取得执行后结果数据
		if (this.resp.result)
			return this.resp.data;
		else
			return null;
	}

	this.getMessage = function() { // 在执行出错时，取得出错原因
		return this.resp.message;
	}
}

// IPhone代理，对应IPhone源码中的JavaScriptProxy
function IPhoneProxy() {
	this.active = false;
}

// 自动判断并返回summer-android或summer-ihone专用浏览器
function getBrowser() {
	browser = new AndroidProxy();
	if (!browser.active) {
		browser = new IPhoneProxy();
		if (!browser.active)
			return null;
	}
	return browser;
}
