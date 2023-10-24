<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<script>
$(function() {
	$("#writeBtn").click(function() {
		if(${empty sessionScope.user}) {
			alert("로그인하세요.");
			location.href="../member/login";
		} else {
			location.href="qnaInsert";
		}
	})
})
</script>
<div class="container">
	<div class="d-flex justify-content-between mb-3">
		<h3>
			고객문의(<span id="cntSpan">${count }</span>)
		</h3>
		<button type="button" class="btn btn-secondary" id="writeBtn">문의하기</button>
	</div>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>문의사항</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boards }" var="board" varStatus="st"> <!-- st가 index를 가지고 있음 -->
				<tr>
					<td>${rowNo-st.index }</td>
					<td><a href="qnaDetail?num=${board.num }">${board.subject }</a></td>
					<td>${board.userid }</td>
					<td>${board.regdate }</td>
					<td>${board.readcount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- main -->

	<div class="d-flex justify-content-between mt-3">
		<!-- 페이징 -->
		<ul class="pagination">
			<!-- 이전 출력 조건 -->
			<c:if test="${p.startPage > p.blockPage }">
				<li class="page-item"><a class="page-link" href="qnaList?pageNum=${p.startPage-p.blockPage }&field=${p.field}&word=${p.word}">Previous</a></li>
			</c:if>
			<!-- 페이지 번호 -->
			<c:forEach begin="${p.startPage }" end="${p.endPage }" var="i">
			<!-- 현재 페이지가 아니면 -->
			<c:if test="${p.currentPage != i }">
				<li class="page-item"><a class="page-link" href="qnaList?pageNum=${i }&field=${p.field}&word=${p.word}">${i }</a></li> <!-- controller에서 pageNum 받아옴 -->
			</c:if>
			<!-- 현재 페이지 -->
			<c:if test="${p.currentPage == i }">
				<li class="page-item active"><a class="page-link" href="">${i }</a></li>
			</c:if>
				
			</c:forEach>
			<!-- 다음 출력 조건 -->
			<c:if test="${p.endPage < p.totPage }">
				<li class="page-item"><a class="page-link" href="qnaList?pageNum=${p.endPage+1 }&field=${p.field}&word=${p.word}">Next</a></li>
			</c:if>
		</ul>
	
		<!-- 검색 -->
		<form class="form-inline" action="qnaList"> <!-- 비동기 함수로 callback 하지않고 바로 처리하겠다, field값 word값 들고감 -->
			<select class="form-control mr-sm-1" id="field" name="field">
				<option value="subject">제목</option>
				<option value="content">내용</option>
				<option value="userid">작성자</option>				
			</select>
			<input class="form-control" type="text" placeholder="search" id="word" name="word">
			<button class="btn btn-success" type="submit">Search</button>
		</form>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>