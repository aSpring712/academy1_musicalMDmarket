<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script>
	$(function() { // jquery
		$("#delBtn").click(function() {
			if (confirm("정말 삭제할까요?")) {
				// location.href="delete?num="+${board.num} // 비동기함수 아니므로 처리 결과를 받으려고 기다리지 않음
				$.getJSON("delete", // $.ajax 또는 $.getJSON 비동기 함수 사용
				{
					"num" : $("#num").val()
				}, function(resp) {
					if (resp == 0) { // 수행된 행 수 : 0 -> 삭제 실패
						alert("삭제할 수 없습니다.");
					} else {
						alert("삭제 성공");
						location.href = "boardList";
					}
				} // function
				) // getJSON
			} // confirm
		}) // delBtn
	}) // function
</script>
<div class="container">
	<h2>공지사항</h2>
	<input type="hidden" name="num" id="num" value=${board.num }>
	<!-- num 값 이렇게 들고가거나 -->
	<table class="table table-hover mt-3">
		<tr>
			<th>글번호</th>
			<td id="no">${board.num }</td>
			<!-- 여기에 id값을 주고 num을 들고가도 됨 -->
			<th>조회수</th>
			<td>${board.readcount }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.userid}</td>
			<th>작성일</th>
			<td>${board.regdate }</td>
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="3">${board.subject}</td>
		</tr>
		<tr>
			<th>글내용</th>
			<td colspan="3">${board.content}</td>
		</tr>
	</table>
	<br />
	<c:if test="${sessionScope.user.userid==board.userid }">
		<%-- 로그인한 사람과 글 쓴 사람이 동일해야 수정/삭제 버튼 --%>
		<button type="button" class="btn btn-primary"
			onclick="location.href='boardupdate?num=${board.num}'">수정</button>
		<button type="button" class="btn btn-secondary" id="delBtn">삭제</button>
	</c:if>

	<div class="container mt-5">
		<textarea rows="5" cols="50" id="msg"></textarea>
		<button type="button" class="btn btn-success mb-3" id="commentBtn">Comment Write</button>
	</div>
	<div id="result"></div>
</div>
<script>
	var init = function() {
		//$.getJSON("commentlist?bnum=$("#num").val()")
		$.getJSON("commentlist", {
			"bnum" : $("#num").val()
		}, function(resp) {
			var str = "<table class='table table-hover'>"
			$.each(resp, function(key, val) {
				str += "<tr>";
				str += "<td>" + val.msg + "</td>";
				str += "<td>" + val.userid + "</td>";
				str += "<td>" + val.regdate + "</td>";
				str += "</tr>";
			})
			str += "</table>"
			$("#result").html(str);
		}) // getJSON
	} // init
	$("#commentBtn").on("click", function() {
		// alert($("#no").text())
		$.ajax({
			type : "get",
			url : "commentInsert",
			data : {
				"msg" : $("#msg").val(),
				"bnum" : $("#num").val()
			}
		}).done(function(resp) {
			if (resp.trim() == "1") { // 로그인 안된 상태
				alert("로그인하세요");
				location.href = "/MD_Market/member/login";
			} else {
				// alert("성공");
				init();
				$("#msg").val('');
			}
		}).fail(function(e) {
			alert("Comment 추가 실패");
		})
	}) // commentBtn
	init(); // 이 함수가 먼저 실행됨
</script>
<%@ include file="../include/footer.jsp"%>