<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>演示dataGrid表格</title>
<style type="text/css">
.dbgrid {
    margin: 0 auto;
}
</style>
</head>
<body>
    <div style="text-align: center;">
        <h1>演示dataGrid表格</h1>
        ${dataGrid} ${page}
    </div>
</body>
</html>