<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include.jsp</title>
</head>
<body>
<%@include file="score.html" %> <!-- 정적포함 -->
<hr>
<jsp:include page="score.html"/> <!-- 동적포함 -->
</body>
</html>