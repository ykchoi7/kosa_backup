<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>score.jsp</title>
</head>
<body>
<% String star = request.getParameter("star"); %>
선택한 별점은 <%=star%>점입니다.
<%!
int totalScore = 0;
int totalCnt = 0;
%> <!-- 요청시마다 호출되는게 아니라 축적이 되어야하기 때문에 declaration으로 선언 -->
<% 
totalScore += Integer.parseInt(star); 
totalCnt++;
%>
<hr>
총점은 <%= totalScore %>점입니다.
<hr>
참여인원은 <%= totalCnt %>명입니다.
<hr>
평점은 <%= (float)totalScore/totalCnt %>점입니다.
</body>
</html>