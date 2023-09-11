<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %> <!-- 일반페이지가 아니라 예외처리 전용 페이지임을 알리는 설정 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>err.jsp</title>
</head>
<body>
<h1 style="color:red;">예외발생 : <%=exception.getMessage() %></h1>
</body>
</html>