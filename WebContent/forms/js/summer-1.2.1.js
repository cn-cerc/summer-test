/**
 * 配合summer-android, summer-iphone项目使用
 */

function ClientProxy() { // 安卓代理，对应android源码中的JavaScriptProxy
	this.active = false; // 标识为true时，表示当前为summer-android专用浏览器
	this.machine = null; // 当前的JavaScriptProxy对象
	this.req = {}; // 请求参数
	this.resp = {}; // 返回参数
	this.message = null; // 返回的消息
	this.device = "browser";

	//android 浏览器
	if (window.JSobj) { // 兼容老的JSobj
		this.machine = window.JSobj;
		this.device="android";
		this.active = true;
	}else if (window.jsAndroid) { // 原JSobj改名为jsAndroid
		this.machine = window.jsAndroid;
        this.device="android";
		this.active = true;
	}
	// iphone 浏览器
	if(!this.active){
	    var ua = navigator.userAgent.toLowerCase();
	    if (/iphone|ipad|ipod/.test(ua)){
	        this.device="iphone";
	        this.active = true;
	    }
	}
	//c#浏览器
	if(!this.active){
    	if(window.external){ 
            this.machine = window.external;
            try{
                window.external.send("GetVersionName", "{}");
                this.device="window";
                this.active = true;
            } catch (e) {
                this.active = false;
            }
    	}
	}
	
	// 判断是否支持指定的功能函数
	this.support = function(classCode) { 
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

	//列出所有可用的服务
	this.list = function(){
		if (!this.active)
			return null;
		var result = this.machine.list();
		if(result)
			return JSON.parse(result);
		else
			return null;
	}
	
	//请求具体的服务，并立即返回
	this.send = function(classCode) {
		if (!this.active)
			return null;
		try {
			var dataOut = {};
			if(this.device == "iphone"){
		        this.req.classCode = classCode;
		        window.webkit.messageHandlers.nativeMethod.postMessage(this.req);
	            this.resp = {
	                    "result" : true,
	                    "message" : "finish"
	                };
			}else{
			    dataOut = this.machine.send(classCode, JSON.stringify(this.req));
			    this.resp = JSON.parse(dataOut);
			}
			
			return this.resp.result;
		} catch (e) {
			this.resp = {
			    "result" : false,
			    "message" : e.message
			};
			return this.resp.result;
		}
	}
	
	this.sync = function(classCode, resultFunction, resultParams){
		this.req.resultFunction = resultFunction;
		this.req.resultParams = resultParams;
		return send();
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

// 自动判断并返回summer-android或summer-ihone专用浏览器
function getBrowser() {
	browser = new ClientProxy();
	if (!browser.active) {
	    return null;
	}
	return browser;
}

function showMessage(data) {
    $("#message").text(data);
}
