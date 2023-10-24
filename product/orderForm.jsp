<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<script src="../js/product.js"></script>

<script>
	$(".back_btn").click(function(event) {
		event.preventDefault();
		location.assign("/product/myCart?userid="userid);
		return false;
	});
	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/MD_Market/juso/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
		//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}

	function jusoCallBack(roadFullAddr, zipNo) {
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.form.zipcode.value = zipNo;
		document.form.address.value = roadFullAddr;
	}
</script>
<div class="container" class="form-group" style="margin-top: 30px;">
	<hr/>
	<form action="payment" id="frm" method="post" name="form" class="was-validated">
		<h2 style="text-align: center;">주문서</h2>
		<div class="container">
			<table class="table table-hover" style="text-align: center;" id="orderTable">
				<thead>
					<tr>
						<th>상품명</th>
						<th>가격</th>
						<th>수량</th>
					</tr>
				</thead>
				<c:if test="${orders != null }">
					<tbody>
						<c:forEach items="${orders }" var="o">
							<tr>
								<td>${o.product_name }</td>
								<td>${o.price }원</td>
								<td>${o.amount }</td>
							</tr>
							<input type="hidden" id="amount" value="${o.amount }">
						</c:forEach>
					</tbody>
				</c:if>
				<c:if test="${orders == null }">
					<tbody>
							<tr>
								<td>${product_name }</td>
								<td>${unitPrice }원</td>
								<td>${qty }</td>
							</tr>
					</tbody>
					<input type="hidden" id="amount" value="${qty }">
				</c:if>
				<tfoot>
					<tr>
						<th>총 결제 금액</th>
						<th>${calPrice }원</th>
						<td>${shipping }</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<hr />
		
		<div class="container" style="margin: 80px 0;">
		<h2 style="text-align: center;">배송 정보 입력</h2>
			<div class="form-group">
				<label for="userid">아이디</label> <input type="text"
					class="form-control" id="userid" value="${member.userid }"
					name="userid" readonly="readonly" required>
			</div>
			<div class="form-group">
				<label for="name">이름</label> <input type="text" class="form-control"
					id="name" value="${member.name }" name="name" required>
				<div class="invalid-feedback">받으시는 분의 성함을 입력해주세요. </div>
			</div>
			<div id="list"></div>
			<div id="callBackDiv">
				<div class="row">
					<div class="col">
						<label for="zipcode">우편번호</label> <input type="text"
							class="form-control" id="zipcode" value="${member.zipcode }"
							name="zipcode" readonly="readonly" required>
					</div>
					<div class="col align-self-end">
						<!-- 같은 줄에 중복확인 버튼 -->
						<input type="button" onClick="goPopup();" class="btn btn-primary"
							value="우편번호 찾기">
					</div>
				</div>
				<div class="form-group" style="margin-top: 5px">
					<label for="address">상세주소</label> 
					<input type="text" class="form-control" id="address" value="${member.address }" name="address" required>
					<div class="invalid-feedback">상세주소를 정확하게 입력해주세요.</div>
				</div>
				<div class="form-group">
					<label for="msg">배송 요청사항</label>
					<input type="text" class="form-control" id="msg" name="msg" placeholder="생략 가능">
				</div>
			</div>
			<div class="form-group">
				<label for="phone">Phone</label> <input type="text"
					class="form-control" id="phone" value="${member.phone }"
					name="phone" placeholder="010-0000-0000" required pattern="^[0-9]{3}-[0-9]{4}-[0-9]{4}$">
				<div class="invalid-feedback">전화번호 양식이 아닙니다.</div>
			</div>
		</div>
		<hr/>
		<div class="form-group" style="text-align: center; margin: 80px 0;">
			<h2 class="page-header">결제수단 확인</h2>
			<div style="text-align: center; margin-top: 30px;">
				<input type="radio" name="cal_info" value="계좌이체" checked>
				<label style="margin-right: 50px;"> 계좌이체</label> 
				<input type="radio" name="cal_info" value="무통장입금">
				<label style="margin-right: 50px;"> 무통장 입금</label> 
				<input type="radio" name="cal_info" value="핸드폰결제">
				<label style="margin-right: 50px;"> 핸드폰 결제</label> 
				<input type="radio" name="cal_info" value="카드">
				<label> 카드 결제</label>
			</div>
			<br/>
			<input type="hidden" id="calPrice" value="${calPrice }">
			<div>
				<input type="hidden" id="status" value="1">
				<button type="button" class="btn btn-primary" id="payment">결제하기</button>
				<button type="button" onclick="history.back()" class="btn btn-secondary">취소하기</button>
			</div>
		</div>
	</form>
</div>

<%@ include file="../include/footer.jsp"%>