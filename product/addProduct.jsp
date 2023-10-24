<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<!-- 204p -->
<div class="jumbotron jumbotron-fluid mb-5">
	<div class="container">
				<h1>상품 등록</h1>
	</div>
</div>
	<div class="container">
	
		<form action="pinsert" class="form-horizontal" method="post" 
		           enctype="multipart/form-data"> <!-- 파일 추가를 위한 부분 -->
	 		<!-- <div class="form-group row">
				<label class="col-sm-2">productId</label>
				<div class="col-sm-3">
					<input type="text" id="productId"  name="productId" 
					 class="form-control" >
				</div>
			</div>  id는 시퀀스로 부여 -->
			<div class="form-group row">
				<label class="col-sm-2">카테고리</label>
				<select name="category">
					<option value="더데빌">더데빌</option>
					<option value="드라큘라">드라큘라</option>
					<option value="록키호러쇼">록키호러쇼</option>
					<option value="마마, 돈크라이">마마, 돈크라이</option>
					<option value="셜록홈즈">셜록홈즈</option>
					<option value="호프">호프</option>
				</select>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">상품명</label>
				<div class="col-sm-3">
					<input type="text" id="name"  name="name" class="form-control" >
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">가격</label>
				<div class="col-sm-3">
					<input type="text" id="unitPrice"  name="unitPrice" class="form-control" >
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">상세 설명</label>
				<div class="col-sm-5">
					<textarea name="description" cols="50" rows="2" class="form-control"></textarea>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">제조사</label>
				<div class="col-sm-3">
					<input type="text" name="manufacturer" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">재고수량</label>
				<div class="col-sm-3">
					<input type="text" id="unitsInStock" name="unitsInStock" class="form-control" >
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">상품 이미지</label>
				<div class="col-sm-5">
					<input type="file" name="productImage" class="form-control" id="productImage">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<input type="submit" class="btn btn-primary"  value="상품추가">
				</div>
			</div>
		</form>
	</div>
<%@ include file="../include/footer.jsp" %>
