<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	/* css 기술하는 순서 정하기!!
	1. box-model
	width, height, padding, border, margin
	2. 위치 선정
	position, top/left, float, clear, overflow, z-index
	3. decoration 속성
	background, color, font, text
	*/
	
	* { 
	    box-sizing: border-box;
	}
	
	header {
	    float:left;
	    /* border: 3px black solid; */
	    /* margin: 10px; */
	    top: 10px;
	    height:80px;
	    align-items: center;
	}
	
	nav {
	    clear:both;
	    float:left;
	    position: absolute;
	    text-decoration: none;
	    top: 10px;
	    left: 100px;
	    height:75px;
	    /* border: 3px solid black; */
	    list-style-type: none;
	    background-color: lightyellow;
	    padding: 0px 10px 10px 0px;
	    /* margin: 10px; */
	}
	
	nav > ul > li {
	    float:left;
	    left: 0px;
	    list-style-type: none;
	    padding: 10px;
	    margin:0px;
	}
	
	nav > ul > li > a {
	    text-decoration-line: none;
	}
	
	nav > ul > li > a:hover {
	    background-color: darkolivegreen;
	    text-decoration: underline;
	}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
	function loadDoc(method, u, target) {
	    console.log(u)
	
	    //GET방식의 요청만 더 단순하게 표현 가능
	    if (method == 'GET') {
	        target.load(u, function( response, status, xhr ){ //jQuery용 메소드 .load( url [, data ] [, complete ] )
	            if (status == 'error') {                      //url~success까지 .load()에 포함되어있는 것
	                alert(xhr.status + xhr.statusText)
	            }
	        })
	    }
	}
	
	// ----------------------jQuery 활용 버전----------------------
	// 아래 세가지 모두 같은 의미
	// window.addEventListener('load', () => {
	// $(document).ready()
	$(() => {
	    //DOM트리에서 section객체 찾기
	    const sectionObj = document.querySelector('section')
	    const $sectionObj = $('section')
	    console.log("--자바스크립트객체--")
	    console.log(sectionObj) // MAIN입니다
	
	    console.log("--jQuery객체--")
	    console.log($sectionObj) // jQuery용 객체 반환
	    console.log(sectionObj === $sectionObj) //false
	    console.log(sectionObj === $sectionObj.get(0)) //true
	
	    //DOM트리에서 nav>ul>li>a 객체들 찾기
	    const menus = document.querySelectorAll('nav>ul>li>a')
	    const $menus = $('nav>ul>li>a')
	    
	    //----메뉴 객체에서 클릭 이벤트가 발생했을 때 할 일 END----
	    $menus.click((e) => {
	        console.log(e.target.className)
	        switch(e.target.className) {
	                case 'login':
	                    loadDoc('GET', './login.html', $sectionObj);
	                    break
	                case 'signup' : 
	                    loadDoc('GET', './signup.html', $sectionObj);
	                    break;
	                case 'logout' : break
	                case 'productlist' : 
	                    //loadDoc('GET', './productlist.html', $sectionObj);
	                    loadDoc('GET', './productlist', $sectionObj);
	                    break;
	                case 'cartlist' : break
	                case 'orderlist' : break
	        }
	    })
	    //----메뉴 객체에서 클릭 이벤트가 발생했을 때 할 일 END----
	})
</script>

<header>
	<img src="./images/logo.png" alt="로고">
</header>
<nav>
    <!--메뉴들-->
    <ul>
	    <li><a href="#" class="login">로그인</a></li>
	    <li><a href="#" class="signup">가입</a></li>
	    <li><a href="#" class="logout">로그아웃</a></li>
	    <li><a href="#" class="productlist">상품 목록</a></li>
	    <li><a href="#" class="cartlist">장바구니 목록</a></li>
	    <li><a href="#" class="orderlist">주문 목록</a></li>
	</ul>
</nav>