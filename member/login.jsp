<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<div class="container">
  <h2>Login</h2>
	<form action="/action_page.php" class="was-validated">
		<div class="form-group">
			<label for="userid">아이디</label> 
			<input type="text" class="form-control" id="userid" placeholder="Enter userid" name="userid" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>
		<div class="form-group">
			<label for="pwd">비밀번호</label> 
			<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pswd" required>
    		<div class="valid-feedback">Valid.</div>
    		<div class="invalid-feedback">Please fill out this field.</div>
		</div>
		<button type="button" class="btn btn-primary" id="loginBtn">로그인</button>
	</form>
</div>

<script>
$("#loginBtn").click(function() { /* 프로그램 -> 위에서부터 아래로 내려오기 때문에 여기에 써주면 위에 loginBtn이 있기 때문에 인식 가능. 만약 이걸 위에 써주면 인식을 하지 못하기 때문에 $(document).ready(function({ 해주어야 함 */
	if($("#userid").val()=="") {
		alert("아이디를 입력하세요.");
		$("#userid").focus();
		return false;
	}
	if($("#pwd").val()=="") {
		alert("비밀번호를 입력하세요.");
		$("#pwd").focus();
		return false;
	}
	$.ajax({
		type : "post",
		url : "login", // sendRedirect에 login 적었으므로
		data : {"userid" : $("#userid").val(), "pwd" : $("#pwd").val()},
		success : function(resp) { // 0 일반 1 관리자 -1 비회원 2 비번오류
			if(resp.trim()==0) {
				$(location).attr("href", "../index.jsp"); // jqeury, 상대경로
			} else if(resp.trim() == 1) {
				alert("관리자 로그인");
				location.href="../member/memberlist" // 자바 스크립트, action으로 보냄
			} else if(resp.trim() == -1) {
				alert("회원이 아닙니다. 회원가입하세요");
			} else if(resp.trim() == 2) {
				alert("비밀번호를 확인하세요");
			}	
		},
		error : function(e) {
			alert("error : " + e);
		}
	});
})
</script>

<%@ include file="../include/footer.jsp" %>