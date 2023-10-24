<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp" %>
<script src="../js/product.js"></script>
<div class="container">
  <h2>나의 주문내역</h2>            
  <table class="table table-hover">
    <thead>
      <tr>
        <th>주문번호</th>
        <th>주문일자</th>
        <th>상품명</th>
        <th>결제 금액</th>
        <th>주문 상세</th>
      </tr>
    </thead>
    <tbody>
	    <c:forEach items="${order }" var="o">
		    	<tr>
			        <td>${o.orderId }</td>
			        <td>${o.orderDate }</td>
			        <td>${o.orderName }</td>
			        <td>${o.orderPrice }</td>
			        <td onclick="orderDetail(${o.orderId })">조회</td>
		    	</tr>
	    </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="../include/footer.jsp" %>