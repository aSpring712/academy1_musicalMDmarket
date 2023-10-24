var exp=/^[0-9]{3}-[0-9]{4}-[0-9]{4}$/ /* 정규식 p.270 000-0000-0000 같은 형태로 입력 받기 위해*/
/* javascript만 있는 파일 */
$(document).ready(function() {
	$("#sendBtn").click(function() {
		if($("#name").val()=="") {
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}
		if($("#userid").val()=="") {
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}
		if($("#pwd").val()=="") {
			alert("비밀번호를 입력하세요");
			$("#pwd").focus();
			return false;
		}
		if($("#pwd").val()!=$("#pwd_check").val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#pwd_check").focus(); // 다시 입력할 수 있도록 포커스 이동
			return false;
		}
		if($("#zipcode").val()=="") {
			alert("우편번호를 입력하세요");
			$("#zipCodeCheckBtn").focus();
			return false;
		}
		if($("#address").val()=="") {
			alert("상세 주소를 입력하세요");
			$("#address").focus();
			return false;
		}
		if($("#email").val()=="") {
            alert("이메일을 입력하세요.");
            $("#email").focus();
            return false;
        }
		if(!exp.test($("#phone").val())) { // match 또는 test 함수 사용 가능
			alert("전화번호 양식이 아닙니다.");
			$("#phone").focus();
			return false;
		}
		$("#frm").submit();
	})
	// 아이디 입력창 -> [중복확인] 클릭 시 뜨는 화면
	$("#idCheckBtn").click(function() {
		window.open("idCheck", "", "width=800 height=500");
	})
	
	// 아이디 중복확인 -> userid를 받아와서 db에 있는지 없는지 확인(있으면 사용 불가, 업으면 사용 가능이라고 알려줘야 함)
	$("#useBtn").on("click", function() {
		if($("#userid").val()=="") {
			alert("아이디를 입력하세요");
			return;
		}
		$.ajax({
			type : "post",
			url : "idCheck",
			data : {"userid" : $("#userid").val()},
			success : function(resp) {
				//alert((resp.trim()).length); // trim() 앞 뒤 공백 제거 -> 우리가 yes나 no만 출력하려고 했는데 앞뒤 공백까지 같이 들어가지니까 제거
				if(resp.trim()=="yes") { //공백 제거한 글자가 yes이면
					alert("사용 가능한 아이디입니다.");
					$(opener.document).find("#userid").val($("#userid").val());
					self.close(); //팝업창 닫아줌
				} else {
					alert("사용 불가능한 아이디입니다.");
					$("#userid").val("");
					$("#userid").focus();
				}
			},
			erro : function(e) {
				alert("error : " + e)
			}
		}); //ajax
	})//idBtn
}) // document
/* function del(userid) 자바스크립트 del 함수, userid를 받아온다 
 * -> memberlist.jsp 파일에서 ${mem.userid}로 값 전달해줌*/
function del(userid) { // 자바스크립트 함수 function del()을 부르는데 userid 값을 받아와야 함
	if(confirm("정말 삭제할까요?")) { // 비동기함수 사용
		$.getJSON("userDelete", 
			{"userid" : userid}, 
			function(resp) { // 삭제 후 전체 회원 목록
					// alert(resp);
					var str = ""; //변수 선언
					$.each(resp.jarr, function(key, val) {
						str += "<tr>"
						str += "<td>" + val.name + "</td>";
						str += "<td>" + val.userid + "</td>";
						str += "<td>" + val.phone + "</td>";
						str += "<td>" + val.email + "</td>";
						str += "<td>" + val.zipcode + "</td>";
						str += "<td>" + val.address + "</td>";
						str += "<td>" + val.mode + "</td>";
						if(val.mode == '일반회원') { // 일반회원 -> 삭제 가능
							str += "<td onclick=del('" + val.userid + "')>삭제</td>"; // 삭제 클릭 후 또 삭제 클릭할 수 있게
						} else { // 관리자 -> 삭제 불가능
							str += "<td>Admin</td>"
						}						
						str += "</tr>"
					}) //each
				$("table tbody").html(str); // tbody 영역의 데이터(삭제 전 회원목록 -> 삭제 후 회원목록)만 바꿔주겠다는 의미
				$("#cntSpan").text(resp.countObj.count); // ctnSpan 영역 : count 나타나는 부분 바꿔줄 것
			}	
		) // getJSON
	} //if
} // function del()