<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp" %>

<script src="../js/product.js"></script> <!-- product.js에 있는 함수 del()을 인식할 수 있도록 -->

<div class="container">
  <h2>제품목록</h2>            
  <table class="table table-hover">
    <thead>
      <tr>
        <th>카테고리</th>
        <th>상품명</th>
        <th>상품번호</th>
        <th>가격</th>
        <th>제조사</th>
        <th>재고수</th>
        <th>삭제</th>
      </tr>
    </thead>
    <tbody>
	    <c:forEach items="${products }" var="p">
	    	<tr>
		        <td>${p.category }</td>
		        <td><a href="/MD_Market/product/productView?productId=${p.productId }">${p.pname }</a></td>
		        <td>${p.productId }</td>
		        <td>${p.unitPrice }</td>
		        <td>${p.manufacturer }</td>
		        <td>${p.unitsInStock }</td>
		        <td onclick="del(${p.productId })">삭제</td>
	    	</tr>
	    </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="../include/footer.jsp" %>