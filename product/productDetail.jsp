<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="jumbotron jumbotron-fluid mb-5">
	<div class="container">
		<h1>상품 상세보기</h1>
	</div>
</div>

<form method="post" onsubmit="return loginCheck();">
<div class="d-flex container">
	<!-- 전체 레이아웃 : 두 부분으로 나눌 것-->
	<!-- 1. 이미지 -->
	<div class="card" style="width: 400px">
		<img class="card-img-top" src="/MD_Market/upload/${product.filename }" alt="Card image">
	</div>

	<!-- 2. 상품 내용 부분  -->
	<div class="container ml-2">
			<table class="table">
				<tr>
					<td width="200px">상품명(분류)</td>
					<th>${product.pname }(${product.category })</th>
				</tr>
				<tr>
					<td>가격</td>
					<td>${product.unitPrice }원</td>
				</tr>
				<tr>
					<td>제조사</td>
					<td>${product.manufacturer }</td>
				</tr>
				<tr>
					<td>상세설명</td>
					<td>${product.description }</td>
				</tr>
				<tr>
					<td>택배배송</td>
					<td>3,000원(주문시 결제)</td>
				</tr>
				<tr>
					<td>수량선택</td>
					<td rowspan="2">1회 주문 시 최대 5개 구매 가능		
						
							<select class="form-control" name="qty">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
							<input type="hidden" name="productId" value="${product.productId }">
							<input type="hidden" name="userid" value="${sessionScope.user.userid }">
							<c:if test="${empty sessionScope.user || sessionScope.user.admin==0 }">
								<%-- 일반회원 --%>
								<input type = "submit" class="btn btn-info" name="addCart" formaction="addCart" value="장바구니">
								<input type = "submit" class="btn btn-warning"  formaction="orderform" value="구매하기"> <!-- productId, qty 받아서 넘어가면 됨  -->
								<a href="javascript:history.back();" class="btn btn-dark">뒤로가기</a>
								<a href="plist" class="btn btn-secondary">전체보기</a>
							</c:if> 
							<c:if test="${sessionScope.user.admin==1}">
								<%-- 관리자 --%>
								<input type="submit" class="btn btn-primary" formaction="productView" value="수정하기">
								<input type="submit" class="btn btn-info" formaction="productDelete" value="삭제하기">
								<a href="javascript:history.back();" class="btn btn-dark">뒤로가기</a>
								<a href="plist" class="btn btn-secondary">전체보기</a>
							</c:if>
						</td>
				</tr>
			</table>
		
	</div>
</div>
</form>
<script type="text/javascript">
function loginCheck() {
	if(${empty sessionScope.user}) {
		window.alert("로그인 하세요");
		return false;
	}
}

</script>

<hr />

<div class="container">
	<div class="row about_product" style="text-align: center;">
		<h1 class="page-header">상품 상세</h1>
	</div>
	<div class="detail">${product.description }</div>
	<div class="row reviews" style="text-align: center; margin: 80px 0;">
		<h1 class="page-header" style="margin-bottom: 50px;">Review</h1>
		<c:forEach begin="1" end="5">
			<div class="container">
				<div class="media border p-3">
					<img src="../upload/img_avatar3.png" alt="John Doe"
						class="mr-3 mt-3 rounded-circle" style="width: 60px;">
					<div class="media-body">
						<h4>
							John Doe <small><i>Posted on February 19, 2016</i></small>
						</h4>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua.</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="QnA">
		<h1 class="page-header">상품 Q&A</h1>
		<c:forEach begin="1" end="5">
			<div class="media border p-3">
				<img src="/MD_Market/upload/${product.filename }" alt="John Doe"
					class="mr-3 mt-3 rounded-circle" style="width: 60px;">
				<div class="media-body">
					<h4>
						대답좀 <small><i>2021/04/06</i></small>
					</h4>
					<p>이거 언제 배송 되나요?</p>
					<div class="media p-3">
						<img src="/MD_Market/upload/Logo.jpg" alt="Admin"
							class="mr-3 mt-3 rounded-circle" style="width: 45px;">
						<div class="media-body">
							<h4>
								판매자 <small><i>2021/04/07</i></small>
							</h4>
							<p>오늘 오후 출고 예정입니다. 감사합니다.</p>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<%@ include file="../include/footer.jsp"%>