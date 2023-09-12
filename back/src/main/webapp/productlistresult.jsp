<%@page import="com.my.product.dto.Product"%>
<%@page import="com.my.product.dto.PageGroup"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <style>
    	div {
            box-sizing: border-box; /*padding, margin값 계산하기 편리하므로 이렇게 설정하는 것을 권장*/
            width:200px;
            display: inline-block;
        }

        div > ul {
            list-style-type: none;
            /* padding-left: 0px; */
        }
        
		div > ul > li > img {
			width: 100px;
			height: 100px;
		}
		
		div > ul > li > span {
            display: block;
            text-align: center;
            font-size: 16px;
            width:80%;
        }
    </style>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    
    <script>
    $(() => {
    	$('.pagegroup > span').click((e) => {
    		alert($(e.target).html() + "페이지가 클릭되었습니다")
    	})
    })
    
    </script>
<meta charset="UTF-8">
<title>productlistresult.jsp</title>
</head>
<body>
<% 
//List<Product> list = (List)request.getAttribute("list");
PageGroup<Product> pb = (PageGroup) request.getAttribute("pb");
List<Product> list = pb.getList();
for(Product p : list) {
%>
<div>
	<ul>
		<li><img src = "./images/<%=p.getProdNo()%>.jpg"></li>
		<li><%=p.getProdName()%></li>
	</ul>
</div>
<%
}
%>
<div class="pagegroup">
<%
int startPage = pb.getStartPage();
int endPage = pb.getEndPage();
int totalPage = pb.getTotalPage();

if (startPage == 0) {
	return;
}
if (startPage > 1) {
%>
[<span>PREV</span>]&nbsp;&nbsp;&nbsp; 
<%
}

for (int i = startPage; i <= endPage; i++) {
%>
[<span class="pg<%=i%>"><%=i %></span>]&nbsp;&nbsp;&nbsp;
<%
}

if (endPage != totalPage) { //endPage < totalPage 인 경우
%>[<span class="pg<%=endPage+1%>">NEXT</span>]
<%
}
%>
</div>
</body>
</html>