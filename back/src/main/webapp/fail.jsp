<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fail.jsp</title>
</head>
<body>
<% String msg = (String)request.getAttribute("msg"); %>
<h1 style="color:red;"><%=msg %></h1>
</body>
</html>