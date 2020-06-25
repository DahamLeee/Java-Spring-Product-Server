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
		
		<table class="table table-condensed">
      		<tr>
      			<th>상품 번호</th>
      			<th>상품 이름</th>
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
						+data[i].id
						+"</td>"
						+"<td>"
						+"<a href=\"#\">"
						+data[i].name
						+"</a>"
						+"</td>"
						+"<td>"
						+data[i].seller
						+"</td>"
						+"<td>"
						+data[i].regtime
						+"</td>"
						;
				$("#htable").html(producthtml);
			} // for end
			
		}
	})
})


</script>
</html>