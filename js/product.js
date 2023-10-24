function del(productId) { 
	if(confirm("정말 삭제할까요?")) { // 비동기함수 사용
		$.getJSON("pDelete", // ProductDeleteController
			{"productId" : productId}, 
			function(resp) { // 삭제 후 전체 상품 목록
					var str = ""; //변수 선언
					$.each(resp, function(key, val) {
						str += "<tr>"
						str += "<td>" + val.category + "</td>";
						str += "<td>" + val.pname + "</td>";
						str += "<td>" + val.productId + "</td>";
						str += "<td>" + val.unitPrice + "</td>";
						str += "<td>" + val.manufacturer + "</td>";
						str += "<td>" + val.unitsInStock + "</td>";
						str += "<td onclick=del('" + val.productId + "')>삭제</td>"; // 삭제 클릭 후 또 삭제 클릭할 수 있게
						str += "</tr>"
					}) //each
				$("table tbody").html(str); 
			}	
		) // getJSON
	} //if
} // function del()

$(document).ready(function() {
	$("#payment").click(function() { // 버튼 클릭 시 row 값 가져오기		
			/*var orderTable = $("#orderTable tbody");
			var orderTableRow = $("#orderTable tbody tr");
			var i = orderTableRow.length; // 구매할 상품의 개수*/
			alert("구매하시겠습니까?");
			/* orderTable.each(function(i) { 
				
		})*/
	})
}) // document*/

function delInCart(productId) { 
	if(confirm("선택한 상품을 삭제할까요?")) { // 비동기함수 사용
		alert("업데이트 예정입니다.");
		/*$.getJSON("cartpdel",
			{"productId" : productId}, 
			function(resp) { // 삭제 후 전체 상품 목록
					var str = ""; //변수 선언
					$.each(resp, function(key, val) {
						str += "<tr>"
						str += "<td>" + val.category + "</td>";
						str += "<td>" + val.pname + "</td>";
						str += "<td>" + val.productId + "</td>";
						str += "<td>" + val.unitPrice + "</td>";
						str += "<td>" + val.manufacturer + "</td>";
						str += "<td>" + val.unitsInStock + "</td>";
						str += "<td onclick=del('" + val.productId + "')>삭제</td>"; // 삭제 클릭 후 또 삭제 클릭할 수 있게
						str += "</tr>"
					}) //each
				$("table tbody").html(str); 
			}	
		) // getJSON */
	} //if
} // function del()
