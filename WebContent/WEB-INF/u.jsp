<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>内测版μ Family</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <script type ="text/javascript" src="/jquery-1.9.1.min.js"></script>
    <meta name="description" content="">
    <meta name="keywords" content="">

    <!--后台全部样式开始-->
    <!--gongyong-->
    <link rel="stylesheet" href="https://www.appbsl.com/app/Tpl/main/fanwe/css/appconf_v2/public.css">

    <!--手机扫描样式开始-->
    <link rel="stylesheet" href="https://www.appbsl.com/app/Tpl/main/fanwe/css/appconf_v2/phoneScan.css">
    <!--手机扫描样式结束-->
<style>
    .other_a{
        display: table;
        width: 95%;
        height: 132px;
        line-height: 132px;
        text-align: center;
    }
    .updateBtugparem{
        text-align:center;
        margin-bottom:15px;
          
    }
    .updataBut{
        display:inline-block;
        padding:6px 15px;
        background: rgb(26, 188, 156);
        color:#fff;
        border:0;
        
    }
    .updataBut .updata_icon{
        max-width:20%;
          vertical-align: middle;
    }
    .downloadBoxMode{
        position:fixed;
        width:100%;
        height:100%;
        background-color:rgba(0,0,0,.5);
        top:0;
        left:0;
        z-index:20;
        display:none;
    }
    .iosdownloadBoxMode{
        position:fixed;
        width:100%;
        height:100%;
        background-color:rgba(0,0,0,.5);
        top:0;
        left:0;
        z-index:20;
        display:none;
    }
    .downloadBoxMode .downloadBox,.iosdownloadBoxMode .downloadBox{
        width:80%;
        background-color:#fff;
        padding:20px;
        position:absolute;
        top:50%;
        left:50%;
        
        font-size:15px;

    }
    .downloadBoxMode .downloadBox .tips,.iosdownloadBoxMode .downloadBox .tips{
        line-height:20px;   
    }
    .downloadBoxMode .downloadBox .btn{
        line-height:20px;
        margin-top:20px;
    }
    .downloadBoxMode .downloadBox .btn:after{
        content:'';
        display:block;
        clear:both;
    }
    .downloadBoxMode .downloadBox .submitBtn{
        float:right;
        color:#6BC439;
    }
    .butList{
    margin-top:20px;
        width:100%;
        -webkit-box-sizing:border-box;
        box-sizing:border-box;
        line-height:30px;
        text-align:center;
    }
    
    .butList>li:first-child{
        color:#6BC439;
    }
    @media screen and (min-width:640px){
    .downloadBoxMode .downloadBox,.iosdownloadBoxMode .downloadBox{
        width:300px;
    }
    
}
</style>
</head>
<body>

<div class="phoneScanMin">

    <!--开始-->
    <div class="phoneScan">
        <img src="https://lucland.com/miuzu/beta/user/u.png" alt="" />
        <div class="phoneScanTitle">μ Family</div>
        <div class="phoneScanTitle">（内测版）</div>
        <!--<p>未受信任的企业级开发者? <a class="phoneScanSolve" href="/acate.html">解决>></a></p>-->
    </div>
    <div class="updateBtugparem"><a href="javascript:void(0);" onclick="jump()" class="updataBut">
        <img class="updata_icon" src="upda-icon.png">点击下载</a></div>
    <div class="phoneScanSize">
        <p>IOS:8.0.0（build 7）</p>
        <p class="phoneScanVers"><span>/</span>大小：124.4 MB<span>/</span></p>
    </div>
    <div class="phoneScanSize">
        <p>Android:8.0.1（build 27）</p>
        <p class="phoneScanVers"><span>/</span>大小：64.7 MB<span>/</span></p>
    </div>
             <div class="phoneQr">
        <p>用手机<span class="phoneQrcolor">在浏览器扫描</span>下方的二维码安装</p>
        <p><img class="phoneQrCode" src="https://lucland.com/miuzu/alpha/user/um.png" alt="https://lucland.com/miuzu/alpha/user/um.png" /></p>
        <p><img class="phoneQrImg" src="https://lucland.com/miuzu/alpha/user/phoneQrImg.png" alt="" /></p>
    </div>
    <!-- 微信 -->
 <div class="downloadBoxMode" id="downloadBoxMode">
    <div class="downloadBox" id="downloadBox">
        <div class="tips">点击右上角按钮，然后在弹出的菜单中，点击在浏览器中打开，即可安装</div>
        <div class="btn"><span class="submitBtn" id="submitBtn">确定</span></div>
    </div>
</div>
<!-- ios -->
<div class="iosdownloadBoxMode" id="iosdownloadBoxMode">
    <div class="downloadBox" id="iosdownloadBox">
        <div class="tips">文件过大，请使用WIFI下载</div>
        <ul class="butList"><li id="kashixiazai">我是wifi网络，开始下载</li><li id="noxiazai">我是3G|4G网络，暂不下载</li></ul>
    </div>
</div>
  <script type="text/javascript">
            /*
             * 智能机浏览器版本信息:
             *
             */
             function jump(){
            var browser = {
                versions: function() {
                    var u = navigator.userAgent, app = navigator.appVersion;
                    return {//移动终端浏览器版本信息
                        trident: u.indexOf('Trident') > -1, //IE内核
                        presto: u.indexOf('Presto') > -1, //opera内核
                        webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                        gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                        mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), //是否为移动终端
                        ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                        android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
                        iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
                        weixin : u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
                        iPad: u.indexOf('iPad') > -1, //是否iPad
                        webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
                        
                    };
                }(),
                language: (navigator.browserLanguage || navigator.language).toLowerCase()
            }
 
            if (browser.versions.ios || browser.versions.iPhone || browser.versions.iPad) {
                if(browser.versions.weixin){
                    document.getElementById("downloadBoxMode").style.display="block";
                     alertBox();
                }else{
                    document.getElementById("iosdownloadBoxMode").style.display="block";
                     alertBox();
                }
               
            }
            else if (browser.versions.android) {
                if(browser.versions.weixin){
                    //alert(123);
                    document.getElementById("downloadBoxMode").style.display="block";
                     alertBox();                        
                }else{
                    window.location="${apkFile}";
                }   
            }   
        }   
        
        function alertBox(){
            //weixin
                var het=parseFloat(document.getElementById("downloadBox").offsetHeight);
                var wd=parseFloat(document.getElementById("downloadBox").offsetWidth);
                document.getElementById("downloadBox").style.marginTop="-"+het/2+"px";
                document.getElementById("downloadBox").style.marginLeft="-"+wd/2+"px";
                console.log('het='+het);
                console.log('wd='+wd);
                 document.getElementById("submitBtn").onclick=function(){
                    document.getElementById("downloadBoxMode").style.display="none";
                }
                //ios
                var het=parseFloat(document.getElementById("iosdownloadBox").offsetHeight);
                var wd=parseFloat(document.getElementById("iosdownloadBox").offsetWidth);
                document.getElementById("iosdownloadBox").style.marginTop="-"+het/2+"px";
                document.getElementById("iosdownloadBox").style.marginLeft="-"+wd/2+"px";
                console.log('het='+het);
                console.log('wd='+wd);
        }
         document.getElementById("kashixiazai").onclick=function(){
                    document.getElementById("iosdownloadBoxMode").style.display="none";
                    window.location="itms-services://?action=download-manifest&url=https://lucland.com/miuzu/beta/user/APP.PLIST"; 
                };
                
                document.getElementById("noxiazai").onclick=function(){
                        document.getElementById("iosdownloadBoxMode").style.display="none";
                    return ;
                };
   </script>
</html>
