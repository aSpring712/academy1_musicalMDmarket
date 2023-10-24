<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- c:choose 사용을 위해 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<header>
		<a href="/MD_Market/product/plist"> <img src="../upload/Logo.jpg" /> </a>
	</header>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
		<a class="navbar-brand" href="/MD_Market/product/plist">전체상품</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar"> 
		<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="/MD_Market/product/plist?category=더데빌">더데빌</a> <!-- 매핑이름 --></li>
				<li class="nav-item"><a class="nav-link" href="/MD_Market/product/plist?category=드라큘라">드라큘라</a></li>
				<li class="nav-item"><a class="nav-link" href="/MD_Market/product/plist?category=록키호러쇼">록키호러쇼</a></li>
				<li class="nav-item"><a class="nav-link" href="/MD_Market/product/plist?category=마마, 돈크라이">마마, 돈크라이</a></li>
				<li class="nav-item"><a class="nav-link" href="/MD_Market/product/plist?category=셜록홈즈">셜록홈즈</a></li>
				<li class="nav-item"><a class="nav-link" href="/MD_Market/product/plist?category=호프">호프</a></li>
				 <!-- Dropdown -->
			    <li class="nav-item dropdown">
			      <a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown"> More </a>
			      <div class="dropdown-menu">
			        <a class="dropdown-item" href="/MD_Market/board/boardlist">공지사항</a>
			        <a class="dropdown-item" href="/MD_Market/board/qnaList">고객문의</a>
			      </div>
			    </li>
			    <li class="form-inline align-center">
					<input class="form-control" type="text" placeholder="Search" id="word" name="word">
					<button class="btn btn-secondary" type="submit">검색</button>
				</li>
			</ul>
			<ul class="navbar-nav">
				<c:choose>
					<c:when test="${empty sessionScope.user }">
						<%-- 세션이 없을 때 --%>
						<li class="nav-item"><a class="nav-link" href="/MD_Market/member/login">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/MD_Market/member/join">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<%-- 세션이 있을 때 --%>
						<li class="nav-item"><a class="nav-link" href="/MD_Market/member/logout">로그아웃</a> <%-- get 방식으로 넘어감 --%></li>
						<li class="nav-item"><a class="nav-link" href="/MD_Market/member/view">정보수정</a></li>
					</c:otherwise>
				</c:choose>
				<c:if test="${sessionScope.user.admin==1 }">
					<%-- 관리자 --%>
					<li class="nav-item"><a class="nav-link" href="/MD_Market/product/pinsert">상품등록</a></li>
					<li class="nav-item"><a class="nav-link" href="/MD_Market/product/pAdminlist">상품목록</a></li>
					<li class="nav-item"><a class="nav-link" href="/MD_Market/member/memberlist">회원관리</a></li>
					<li class="nav-item"><a class="nav-link" href="/MD_Market/product/orderlist">주문현황</a></li>
				</c:if>
				<c:if test="${sessionScope.user.admin==0 }">
					<%-- 일반회원 --%>
					<li class="nav-item"><a class="nav-link" href="/MD_Market/product/myCart">장바구니</a></li>
					<span class="navbar-text">(${sessionScope.user.name } 님 반갑습니다.)</span>
				</c:if>
			</ul>
		</div>
	</nav>
	<div class="container pt-4 text-center">
		<h1>MUSICAL MD STORE</h1>
	</div>
</head>
<body>

</body>
</html>