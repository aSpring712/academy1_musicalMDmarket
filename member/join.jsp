<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<script src="../js/member.js"></script>
<script>
function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/MD_Market/juso/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr, zipNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.form.zipcode.value = zipNo;
		document.form.address.value = roadFullAddr;
}
</script>
	<div class="container">
		<h2>회원가입</h2>
		<form action="join" id="frm" method="post" name="form">
		<input type="hidden" name="admin" value="0">
			<div class="form-group">
				<label for="name">이름</label> <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
			</div>
			<div class="row"> <!-- 한 줄에 ID 입력받고, 중복확인 버튼도 같이 위치시키려고 -->
				<div class="col"> <!-- ID 입력받는 곳 -->
					<label for="userid">아이디</label> <input type="text" class="form-control" id="userid" placeholder="Enter userid" name="userid" readonly="readonly">
				</div>
				<div class="col align-self-end"> <!-- 같은 줄에 중복확인 버튼 -->
					<button type="button" id="idCheckBtn" class="btn btn-primary">중복확인</button>
				</div>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label> <input type="password" class="form-control" id="pwd" placeholder="Enter pwd" name="pwd">
			</div>
			<div class="form-group">
				<label for="pwd_check">비밀번호 재확인</label> <input type="password" class="form-control" id="pwd_check" placeholder="Enter pwd_check" name="pwd_check">
			</div>
			<div id="list"></div>
			<div id="callBackDiv">
			<div class="row"> 
				<div class="col"> 
					<label for="zipcode">우편번호</label> 
					<input type="text" class="form-control" id="zipcode" placeholder="Enter zipcode" name="zipcode" readonly="readonly">
				</div>
				<div class="col align-self-end"> <!-- 같은 줄에 중복확인 버튼 -->
					<input type="button" onClick="goPopup();" class="btn btn-primary" value="우편번호 찾기" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="address">상세주소</label> <input type="text" class="form-control" id="address" placeholder="Enter address" name="address">
			</div>
			</div>
			<div class="form-group">
				<label for="email">이메일</label> <input type="text" class="form-control" id="email" placeholder="Enter email" name="email">
			</div>
			<div class="form-group">
				<label for="phone">전화번호</label> <input type="text" class="form-control" id="phone" placeholder="Enter phone" name="phone">
			</div>
			<div class="form-group form-check"></div>
			<button type="button" class="btn btn-primary" id="sendBtn">Submit</button> <!-- <button type="submit"> 하면 클릭 시 바로 넘어가니까 button으로 바꾸어줌 -->

			<!-- 켜면 관리자로 회원가입
			<div class="gubun" name="admin">
				<div class="custom-control custom-switch">
					<input type="checkbox" class="custom-control-input" id="switch1">
					<label class="custom-control-label" for="switch1">Admin</label>
				</div>
			</div> -->

	</form>
	</div>
<%@ include file="../include/footer.jsp" %>