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
		<form id="productform">
			<table class="table table-bordered">	
				<tr>
					<th> 상품명 </th>
					<td colspan = "3"> <input type="text" id="name" name="name" size = "50" maxlength="20" value="${productDto.name}"></td>
				</tr>
				<tr>
					<th> 상품 가격 </th>
					<td colspan = "3"> <input type="number" id="price" name="price" size = "50" value="${productDto.price}"></input></td>
				</tr>
				<tr>
					<th> 상품 설명 </th>
					<td colspan = "3"> <textarea id="description" name="description" cols="50" rows="10">${productDto.description}</textarea></td>
				</tr>
				<tr>
					<td colspan="4"> <button class="btn btn-primary" type="button" id="registerBtn">상품 수정</button> <a href="${root}/product" class="btn btn-success">목록보기</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>

<script type="text/javascript">
$(document).ready(function(){
	$("#registerBtn").click(function(){
		if($("#name").val() == ""){
			alert("상품 명을 입력해주세요!");
			return;
		} else if($("#price").val() == ""){
			alert("상품 가격을 입력해주세요!");
			return;
		} else{
			$.ajax({
				type : "PUT",
				url : "${root}/product/api/modify/" + ${productDto.id},
				headers:{"Content-Type":"application/json"},
				data : JSON.stringify(
					{
						id : ${productDto.id},
						name : $("#name").val(),
						price : $("#price").val(),
						description : $("#description").val()
					}),
					
				success : function(data){
					if(data.status == 1){
						alert("수정 성공!");
						location.href = "${root}/product";
					} else{
						alert("수정 실패!");
						location.reload();
					}
				},
				error : function(xhr, status){
					console.log(xhr);
					alert(xhr + " : " + status);
				}
			});
		};
	});
});
</script>

</html>

