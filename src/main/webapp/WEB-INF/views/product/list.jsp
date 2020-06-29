<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../jstl_init.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../htmlHead_init.jsp" %>
</head>
<body>

	<div class="container">
		<%@ include file="../navbar.jsp" %>
		
		<h1>여기는 상품 조회입니다</h1>
		<div>
      		<form id="searchform" action="${root}/api/product/search">
      		<input type="hidden" name="page" value="">
      			<div class="form-group">			
      				<select id="key" id="key" name="key">
      					<option value="name">상품 이름
      					<option value="content">판매자
      				</select>
      				<input type="text" id="word" name="word" value="${word}">
      				<button type="submit" class="btn btn-default">검색</button>
      			</div>
      		</form>
      	</div>
		
		<table class="table table-condensed">
      		<tr>
      			<th>상품 번호</th>
      			<th>상품 이름</th>
      			<th>상품 가격</th>
      			<th>판매자</th>
      			<th colspan="4">등록 날짜</th>
   			</tr>
   			<tbody id="htable">
   			</tbody>
		</table>
		<div align="right">
			<c:if test="${userinfo != null}">
				<a href="${root}/product/mvwrite">상품 등록</a>
			</c:if>
		</div>
	</div>
</body>

<script type="text/javascript">
$(function(){
	$("#navproduct").addClass("active");
	
	$.ajax({
		type: "GET",
		url : "${root}/api/product/searchAll/",
		async : false,
		headers:{"Content-Type":"application/json"},
		success : function(data){
			let producthtml;
			if(data.length == 0){
				producthtml += "<tr><td colspan=\"5\">등록된 상품이 없습니다.</td></tr>"
				$("#htable").html(producthtml);
			}
				for(var i = 0; i < data.length; i++){
				producthtml += "<tr>"
						+"<td>"
						+data[i].product_id
						+"</td>"
						+"<td>"
						+"<a href=\"${root}/api/product/findProductById/"+ data[i].product_id +"\">"
						+data[i].name
						+"</a>"
						+"</td>"
						+"<td>"
						+ data[i].price + "원"
						+"</td>"
						+"<td>"
						+data[i].seller
						+"</td>"
						+"<td>"
						+data[i].regtime
						+"</td>"
				if("${userinfo.user_id}" == data[i].seller){
					producthtml += "<td></td>"
								+ "<td onclick=remove("+ data[i].product_id + ")> 삭제 </td>"
								+ "<td onclick=modify("+ data[i].product_id + ")>수정</td>"
				} else if("${userinfo.user_id}" != ""){
					producthtml += "<td onclick=buy("+ data[i].product_id + ")>구매</td>"
								+ "<td></td>"
								+ "<td></td>"
								
				} else{
					producthtml += "<td></td>"
								+ "<td></td>"
								+ "<td></td>"
				}
				$("#htable").html(producthtml);
			} // for end
		}, // success end
		error : function(xhr, status){
			console.log(xhr);
			alert(xhr + " : " + status);
		}
	})
})
function submit(idx) {
	let form = $('#searchform');
	$('input[name="page"]').val(idx);
	form.submit();
}
function remove(idx){
	$.ajax({
		type : "DELETE",
		url : "${root}/api/product/" + idx,
		headers:{"Content-Type":"application/json"},
		success : function(data){
			if(data.status == 1){
				alert("삭제가 성공적으로 이루어졌습니다");
				location.reload();
			} else{
				alert("삭제하는 도중 에러가 발생했습니다");
				location.reload();
			}
		},
		error : function(xhr, status){
			console.log(xhr);
			alert(xhr + " : " + status);
		}
	})
}
function modify(idx){
	location.href="${root}/product/mvmodify/" + idx;
}

function buy(idx){
	alert("${userinfo.user_id}인 사람이 " + idx + "번째 상품을 살 것입니다");
	// 그러면 여기서 구매목록에 담는거지
	
}

</script>
</html>