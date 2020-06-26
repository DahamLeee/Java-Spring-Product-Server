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
		<h4>상품 아이디 : ${product.id}</h4>
		<h4>상품명 : ${product.name}</h4>
		<h4>상품 가격 : ${product.price}</h4>
		<h4>판매자 : ${product.seller}</h4>
		<h4>설명서 : ${product.description}</h4>
	</div>
</body>
</html>