
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
	$("#registerBtn").click(function() {
		
		if($("#j_name").val() == "") {
			alert("이름 입력!!!");
			return;
		} else if($("#j_id").val() == "") {
			alert("아이디 입력!!!");
			return;
		} else 
			if($("#j_passowrd").val() == "") {
			alert("비밀번호 입력!!!");
			return;
		} else if($("#j_password").val() != $("#pwdcheck").val()) {
			alert("비밀번호 확인!!!");
			return;
		} else {
			$.ajax({
				type : "POST",
				url : "${root}/api/user/join/",
				headers:{"Content-Type":"application/json"},
				data : JSON.stringify(
					{
						id: $("#j_id").val(),
						password: $("#j_password").val(),
						name: $("#j_name").val(),
						phone: $("#j_phone").val()
					}),
				success : function(data){
					console.log(data);
					if(data.status == 1){
						alert("회원 가입 성공!");
						location.href="${root}";
					} else{
						alert("회원 가입 실패!");
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
</head>
<body>
	<div class="container">
      <%@ include file="../navbar.jsp" %>
<h1 align="center">회원가입</h1><br>
<div class="container" align="center">
	<div class="col-lg" align="center">
		<form id="memberform">
			<div class="form-group" align="left">
				<label for="">아이디</label>
				<input type="text" class="form-control" id="j_id" name="j_id" placeholder="">
				<div id="msg"></div>
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="j_password" name="j_password" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호재입력</label>
				<input type="password" class="form-control" id="pwdcheck" name="pwdcheck" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="j_name" name="j_name" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="tel">전화번호</label>
				<div id="tel" class="custom-control-inline">
				<input type="text" class="form-control" id="j_phone" name="j_phone" placeholder="ex) 010-0000-0000">
				</div>
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="registerBtn">회원가입</button>
				<button type="reset" class="btn btn-warning">초기화</button>
			</div>
		</form>
	</div>
</div>
</div>
</body>
</html>