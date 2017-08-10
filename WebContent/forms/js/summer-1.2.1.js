/**
 * 配合summer-android, summer-iphone项目使用 
 */

function AndroidProxy(){
	this.active = false;
	this.machine = null;
	this.req = {};
	this.resp = {};
	this.message = null;

	if(window.JSobj){ //兼容老的JSobj
		this.machine = window.JSobj;
		this.active = true;
	}
	if(window.jsAndroid){
		this.machine = window.jsAndroid;
		this.active = true;
	}
	
	this.support = function(kind){
		if(!this.active){
			this.message = "您的版本太旧，不支持指令：" + kind;
			return false;
		}
		try{
			var dataOut = this.machine.support(kind);
			this.resp = JSON.parse(dataOut);
			if(!this.resp.result && this.resp.message)
				this.message = this.resp.message;
			return this.resp.result;
		}catch(e){
			this.message = "您的版本太旧，不支持指令：support";
			return false;
		}
	}
	
	this.send = function(kind){
		if(!this.active)
			return null;
		try{
			var dataOut = this.machine.send(kind, JSON.stringify(this.req));
			this.resp = JSON.parse(dataOut);
			return this.resp.result;
		}catch(e){
			resp = {"result":true, "message":e.getMessage()};
			return this.resp.result;
		}
	}
	
	this.getData = function(){
		if(this.resp.result)
			return this.resp.data;
		else
			return null;
	}
	
	this.getMessage = function(){
		return this.resp.message;
	}
	
	this.hello = function(){ //test
		if(!this.active)
			return "I not is android.";
		return this.machine.hello2Html();
	}
}

function IPhoneProxy(){
	this.active = false;
}

function getBrowser(){
	browser = new AndroidProxy();
	if (!browser.active) {
		browser = new IPhoneProxy();
		if (!browser.active) 
			return null;
	}
	return browser;
}

/* use sample
function onLoginClick() {
	browser = getBrowser();
	if(browser == null){
		alert("then is not android or iphone.");
		return;
	}

	if (browser.send("getClientId")) {
		alert(browser.getData());
	}else{
		alert(browser.getMessage());
	}

	if (browser.support("getClientId")) {
		alert(browser.getData()); //取该函数描述
	}
}
*/
