<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp" %>
<script src="../js/product.js"></script>
<div class="container">
  <h2>장바구니</h2> 
	 
  <form name="form">
	  <table class="table table-hover">
	  <c:if test="${totalPrice == 0}">
					<STYLE TYPE="text/css">
					table { font-size: 20pt; text-align: center; }
					</STYLE>
			<tr><td>장바구니에 담긴 상품이 없습니다<td><tr>
	  		<tr><td colspan="5" align="center"><button type="button" onclick="history.back()" class="btn btn-info">쇼핑 계속하기</button></td><tr>
	  </c:if>
	  <c:if test="${totalPrice != 0}">
	    <thead>
	      <tr>
	        <th colspan="2">상품명</th>
	        <th>가격</th>
	        <th>수량</th>
	        <th>합계</th>
	        <th>삭제</th>
	      </tr>
	    </thead>
	    <tbody>
		    <c:forEach items="${carts }" var="c">
			    	<tr>
				        <td colspan="2">${c.product_name }</td>
				        <td>${c.price }</td>
				        <td>${c.amount }</td>
				        <td>${c.money }</td>
				        <td onclick="delInCart(${c.product_id })">삭제</td> <!-- 구현 예정 -->
			    	</tr>
		    </c:forEach>
	    </tbody>

		<tfoot align="center">
			<tr>
				<th>금액 합계</th>
				<th>배송비</th>
				<th colspan="4">총 결제 금액</th>
			</tr>
			<tr>
				<td><input type="text" name="totalPrice" value="${totalPrice }" readonly="readonly" style="border:none; text-align: center;"></td>
				<td>${shipping }</td>
		        <td colspan="4" align="center">${calPrice }</td>
			</tr>
			<tr>
				<td colspan="5" align="right">
					<button type="button" onclick="history.back()" class="btn btn-info">쇼핑 계속하기</button>
					<input type = "submit" class="btn btn-secondary" formaction="clearCart" value="장바구니 비우기">
					<input type = "submit" class="btn btn-warning" formaction="orderform" value="주문하기">
				</td>
			</tr>
		</tfoot>
		</c:if>

	  </table>
	  </form>
	  
	</div>

<%@ include file="../include/footer.jsp" %>