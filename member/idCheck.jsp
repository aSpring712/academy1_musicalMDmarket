<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>ID 중복 검사</title>
<script src="../js/member.js"></script>
</head>
<body>
<div class="container">
	<div class="row"> <!-- 한 줄에 ID 입력받고, 중복확인 버튼도 같이 위치시키려고 -->
		<div class="col" style="margin: 100px;"> <!-- ID 입력받는 곳 -->
			<label for="userid">UserID:</label> <input type="text" 
			class="form-control" id="userid" placeholder="Enter userid" 
			name="userid">
		</div>
		<div class="col align-self-end" style="margin: 100px; 30px;'"> <!-- 같은 줄에 중복확인 버튼 -->
			<button type="button" id="useBtn" class="btn btn-secondary">사용여부</button> <!-- useBtn 누르면 member.js에 가서 useBtn -->
		</div>
	</div>

</div>
</body>
</html>