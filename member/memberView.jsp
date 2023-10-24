<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script> /* 자바 스크립트 */
function check() {
	if($("#pwd").val()=="") {
		alert("비밀번호를 입력하세요");
		return false;
	}
	return true;
}
function delCon() {
	if(confirm("정말 탈퇴할까요?")) {
		location.href="delete"; // 컨트롤러의 Servlet name이 됨
	}
}
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
	<h2>회원변경</h2>
	<form action="update" id="frm" method="post" onsubmit="return check()" name="form"> <!-- return false이면 안넘어가고, true일 때 넘어감 -->
	<!-- sumbit -> action으로 가는데 onsubmit이 있으면 여기로 먼저 감! -->
		<div class="form-group">
			<label for="userid">아이디</label> <input type="text"
				class="form-control" id="userid" value="${member.userid }"
				name="userid" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="name">이름</label> <input type="text"
				class="form-control" id="name" value="${member.name }" name="name">
		</div>
		<div class="form-group">
			<label for="pwd">비밀번호</label> <input type="password"
				class="form-control" id="pwd" name="pwd">
		</div>
		<div class="form-group">
			<label for="email">Email</label> <input type="text"
				class="form-control" id="email" value="${member.email }"
				name="email">
		</div>
		<div id="list"></div>
			<div id="callBackDiv">
			<div class="row"> 
				<div class="col"> 
					<label for="zipcode">우편번호</label> 
					<input type="text" class="form-control" id="zipcode" value="${member.zipcode }" name="zipcode" readonly="readonly">
				</div>
				<div class="col align-self-end"> <!-- 같은 줄에 중복확인 버튼 -->
					<input type="button" onClick="goPopup();" class="btn btn-primary" value="우편번호 찾기" />
				</div>
			</div>
			<div class="form-group">
				<label for="address">상세주소</label> <input type="text" class="form-control" id="address" value="${member.address }" name="address">
			</div>
			</div>
		<div class="form-group">
			<label for="phone">Phone</label> <input type="text"
				class="form-control" id="phone" value="${member.phone }"
				name="phone">
		</div>
		<br/>
		<button type="submit" class="btn btn-primary">수정</button> <!-- submit은 바로 넘어감 ->  onsubmit 사용, 못 넘어가도록! -->
		<button type="button" class="btn btn-secondary" onclick="delCon()">회원탈퇴</button> <!-- id로 할거면 JQuery 사용 -->
	</form>
</div>
<%@ include file="../include/footer.jsp"%>