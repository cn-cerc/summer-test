<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>出错页面</title>
</head>
<body>
    <div style="text-align: center; padding-top: 2em;">
        <div>抱歉，运行出现错误：</div>
        <div>${message}</div>
        <div style="padding: 0.5em;">
            <a href="javascript:history.go(-1)">返回</a>
        </div>
    </div>
</body>
</html>