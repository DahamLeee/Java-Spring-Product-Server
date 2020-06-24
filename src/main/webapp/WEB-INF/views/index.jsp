<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./jstl_init.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./htmlHead_init.jsp" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<%@ include file="./navbar.jsp" %>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$("#navhome").addClass("active");
})
</script>
</html>