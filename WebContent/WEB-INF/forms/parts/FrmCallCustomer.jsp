<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>拨打客户电话</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,user-scalable=no">
<script src="js/jquery.js"></script>
<script src="js/summer-1.2.1.js"></script>
<style>
* {
	margin: 0;
	padding: 0;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

li {
	list-style: none
}

;
body {
	font-size: 18px;
}

.ulLIst {
	width: 100%;
}

.ulLIst>li {
	padding: 10px;
	border-bottom: 1px solid #eee;
}

.tips:after {
	content: '';
	display: block;
	clear: both;
}

.tips {
	margin-top: 30px
}

.tips>li {
	float: left;
	/* width:50%; */
	padding: 10px;
}

.tips>li:first-child {
	width: 30%;
}

.tips>li:last-child {
	border-left: 1px solid #eee;
	width: 70%;
}

.huanhan {
	white-space: normal;
	word-wrap: break-word;
	word-break: break-all;
}

.padding10 {
	padding: 10px;
}

.btn {
	width: 85%;
	margin: 0 auto;
	text-align: center;
	padding: 10px;
	border: 1px solid #eee;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	background-color: grey;
	margin-top: 10px;
}

.btn.active {
	background-color: #efe;
}

.beizhu textarea {
	margin-top: 10px;
	border: 1px solid #eee;
	width: 100%;
	height: 100px;
	outline: none;
	resize: none;
}
</style>
<script>
	$(function() {
		$(".btn").on("click", function() {
			var btn = $(this);
			btn.addClass('active');
			var timer = setInterval(function() {
				btn.removeClass('active');
				clearInterval(timer);
			}, 100);
		})

		/* $("#post").on("click", function(){
			var items;
			$('input[type="checkbox"]').each(function(){
				if($(this).is(":checked")){
					items + $(this).val();
				}
			})
			var phoneNumber = "${phoneNumber}";
			var customerName = "${customerName}";
			var userName = "${userName}";
			var remark = $("#remark").val();
			var timeLength = $("#timeLengthShow").html();
		}) */
	})
</script>
<script>
	var browser = null; //android.webview

	window.onload = function() {
		browser = getBrowser();
		if (browser == null) {
			alert("this is not android or iphone.");
			return;
		}
	}

	function callPhoneNumber() {
		if (browser == null)
			return;
		//拔打指定的电话号码
		//browser.req = {"phoneNumber": "${phoneNumber}"};
		browser.req = {
			"phoneNumber" : "15191048958"
		};
		if (browser.send("CallPhoneNumber")) {
			var json = JSON.parse(browser.resp.data);
			if (json.result) {
				$("#timeLengthShow").text(json.message);
			} else {
				$("#timeLengthShow").text(json.message);
			}
		} else {
			alert(browser.getMessage());
		}
	}

	function getTimeLength() {
		if (browser == null)
			return;
		if (browser.send("GetPhoneTime")) {
			var json = JSON.parse(browser.resp.data);
			if (json.result) {
				if (json.phoneNumber != "${phoneNumber}") {
					alert("手机不相同");
					return;
				}
				$("#timeLengthShow").text(parseTimeLength(json.timeLength));
			} else {
				$("#timeLengthShow").text(json.message);
			}
		} else {
			alert(browser.getMessage());
		}
	}

	function parseTimeLength(timeLength) {
		var second = 0;
		var min = 0;
		var hour = 0;
		var time;
		if (timeLength >= 0) {
			second = timeLength % 60;
			time = doubleMum(second) + "秒";
		}
		if (timeLength >= 60) {
			min = timeLength / 60 % 60;
			time = doubleMum(parseInt(min)) + "分" + doubleMum(second) + "秒";
		}
		if (timeLength >= 3600) {
			hour = timeLength / 3600 % 60;
			time = doubleMum(parseInt(hour)) + "时" + doubleMum(parseInt(min))
					+ "分" + doubleMum(second) + "秒";
		}
		$(function() {
			$("#timeLengthShow").html(time);
		})

	}

	function doubleMum(num) {
		if (num < 10) {
			return "0" + num;
		}
		return num;
	}
</script>
</head>
<body>
	<ul class="ulLIst">
		<li><span class="item">拨打电话：</span>${phoneNumber}</li>
		<li><span class="item">客户简称：</span>${customerName}</li>
		<li><span class="item">联系人：</span>${userName}</li>
	</ul>
	<ul class="tips">
		<li>
			<div>
				<p class="huanhan">提示：</p>
			</div>
		</li>
		<li><c:forEach var="item" items="${list}">
				<p>
					<label><input name="items" type="checkbox" value="${item}" />&nbsp;&nbsp;${item}</label>
				</p>
			</c:forEach></li>
	</ul>
	<div class="beizhu padding10">
		<p>备注：</p>
		<textarea class="padding10" id="remark"></textarea>
	</div>
	<div class="padding10">
		<span>通话时长：</span><span id="timeLengthShow"></span>
	</div>
	<div class="btn" onClick="callPhoneNumber();">拨打</div>
	<div class="btn" id="post" onClick="getTimeLength();">提交</div>
</body>
</html>