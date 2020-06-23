<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../jstl_init.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../htmlHead_init.jsp" %>
<style type="text/css">
		.container
		{
		width: 100%;
		}
		.col-lg
		{
		width:50%;
		border : solid black;
		}
		#memberform
		{
		width:80%;
		}
	</style>
<script type="text/javascript">
$(document).ready(function() {
	$("#modify").click(function() {
		if($("#m_name").val() == "") {
			alert("이름 입력!!!");
			return;
		} else if($("#m_password").val() == "") {
			alert("비밀번호 입력!!!");
			return;
		} else {
			// 여기를 ajax로 바꾸면 되지 않을까
			$.ajax({
				type : "PUT", // http method는 PUT으로
				url : "${root}/api/user/", // 요기에
				headers:{"Content-Type":"application/json"},
				data : JSON.stringify(
					{
						id: $("#m_id").val(),
						password: $("#m_password").val(),
						name: $("#m_name").val(),
						phone: $("#m_phone").val()
					}),
				success : function(data){
					if(data.status == 1){
						alert("수정 성공");
						location.href="${root}";
					} else{
						alert("수정 실패");
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
	$("#delete").click(function() {
		$.ajax({
			type: "DELETE", 
			url : "${root}/api/user/", 
			headers:{"Content-Type":"application/json"},
			data : JSON.stringify({
				id: $("#m_id").val(),
				password: $("#m_password").val(),
				name: $("#m_name").val(),
				phone: $("#m_phone").val()
			}),
			success : function(data){
				if(data.status == 1){
					alert("회원 탈퇴 성공");
					location.href = "${root}";
				} else{
					alert("회원 탈퇴 실패");
					location.reload();
				}
			},
			error : function(xhr, status){
				console.log(xhr);
				alert(xhr + " : " + status);
			}
		});
	});
});
</script>
</head>
<body>
	<div class="container">
      <%@ include file="../navbar.jsp" %>
<h1 align="center">정보 수정</h1><br>
<div class="container" align="center">
	<div class="col-lg" align="center">
		<form id="memberform" method="post" action="${root}/user/modify">
			<div class="form-group" align="left">
				<label for="">아이디</label>
				<input type="text" class="form-control" id="" name="" placeholder="" value="${userinfo.id}" disabled>
				<input type="hidden" class="form-control" id="m_id" name="m_id" placeholder="" value="${userinfo.id}">
			</div>
			<div class="form-group" align="left">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="m_name" name="m_name" placeholder="" value="${userinfo.name}">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="m_password" name="m_password" placeholder="" value="${userinfo.password}">
			</div>
			<div class="form-group" align="left">
				<label for="tel">전화번호</label>
				<div id="tel" class="custom-control-inline">
				<input type="text" class="form-control" id="m_phone" name="m_phone" placeholder="ex) 010-0000-0000" value="${userinfo.phone}">
				</div>
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="modify">수정</button>
			</div>
		</form>
		<form id="deleteform" method="post" action="">
		<button type="button" class="btn btn-warning" id="delete">회원탈퇴</button>
		</form>
	</div>
</div>
</div>
</body>
</html>