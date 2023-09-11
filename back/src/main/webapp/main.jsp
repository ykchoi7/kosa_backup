<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
<style>
	section {
		clear:both;
		float:left;
		width: 650px;
		height: 450px;
		font-size: 16px;
		/* border: 3px solid black; */
		background-color: #1e3932;
		color: white
		/* margin: 10px; */
	}
</style>
</head>
<body>
<% //java 주석문 (_jsp문서에 표시됨) %>
<%-- jsp 주석문 (아예 표시가 안되므로 메모리 효율이 가장 좋음) --%>
<!-- html 주석문 (_jsp문서에 표시됨) -->
<jsp:include page="header.jsp"/>
<section>
</section>
<%@include file="footer.jsp" %>
</body>
</html>