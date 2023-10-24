<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 전체보기 페이지 p.132 -->
<%@ include file="../include/header.jsp"%>

<script type="text/javascript">
	function addToCart() {
		if(confirm("상품을 장바구니에 추가하시겠습니까?")) {
			document.addForm.submit();
		} else {
			document.addForm.reset();
		}
	}
</script>

<div class="jumbotron jumbotron-fluid mb-5">
	<div class="container">
				<h1>상품 목록</h1>
	</div>
</div>

<div class="container">
	<div class="row">
		<c:forEach items="${products }" var="product">
			<div class="scale col-sm-6 col-md-4 mb-4">
				<a class="thumnail" href="pdetail?productId=${product.productId }">
				<img src="/MD_Market/upload/${product.filename }" alt="image"></a>
				<div class="caption">
				<div class="pname"><h4>${product.pname }</h4></div>
				<h4>${product.unitPrice }<span>원</span></h4>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<style>
.scale {
	text-align : center;
}
.pname {
	 white-space: pre-line;
     overflow: hidden;
     text-overflow: ellipsis;
}
</style>

<%@ include file="../include/footer.jsp"%>