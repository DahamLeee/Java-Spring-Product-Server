<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:if test="${cookie.saveid.value != null}">
	<c:set var="svid" value="${cookie.saveid.value}"/>
	<c:set var="ckid" value=" checked"/>
</c:if>
<!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${root}/product">Product</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li id="navhome"><a href="${root}/">Home</a></li>
              <li id="navproduct"><a href="${root}/product">Product</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            <c:choose>
            	<c:when test="${userinfo == null}">
              <li><a style="cursor:pointer;" data-toggle="modal" data-target="#loginModal">로그인</a></li>
              <li><a href="${root}/user/mvinsert">회원가입</a></li>
            	</c:when>
            	<c:otherwise>
              <li><a>${userinfo.name}(${userinfo.user_id})</a></li>
              <input type="hidden" id="userCheck" value="${userinfo.user_id}">
              <li><a href="${root}/product/mvbuy">구매 목록</a></li>
              <li><a href="${root}/user/mvmodify">수정</a></li>
              <li><a style="cursor:pointer;" onclick="logout()">로그아웃</a></li>           	
            	</c:otherwise>
            </c:choose>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
      <!-- Login Modal -->
		<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
		  <div class="modal-dialog" align="center">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="modalLabel">로그인</h4>
		      </div>
		      <div class="modal-body">
		      	<form>
		          <div class="form-group">
		            <label for="" class="control-label">ID:</label>
		            <input type="text" class="form-control" id="user_id" name="user_id" value="${svid}">
		          </div>
		          <div class="form-group">
		            <label for="" class="control-label">비밀번호:</label>
		            <input type="password" class="form-control" id="password" name="passowrd">
		          </div>
		          <div class="form-group form-check" align="right">
				   	<label class="form-check-label">
				 	 	<input class="form-check-input" type="checkbox" id="idsave" name="idsave" value="saveok"${ckid}> 아이디저장
				  	 </label>
				  </div>
		          <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        <button type="button" onclick="login()" class="btn btn-primary">로그인</button>
			        <button type="button" class="btn btn-success" onclick="javascript:movefindpw();">비밀번호 찾기</button>
			      </div>
		        </form>
		      </div>
		    </div>
		  </div>
		</div>
      
      <script>
      		function login() {
	    	  	let saveid;
	    	  	if($("#idsave").is(":checked")) {
	    	  		saveid="saveok";
	    	  	}
				$.ajax({
				    url : "${root}/api/user/login", // 요기에
				    type : "POST",
				    data : {"user_id":$("#user_id").val(), "password":$("#password").val(), "idsave":saveid}, 
				    success : function(data) {
				    	if(data.status.user_id == "success"){
				    		alert("로그인 성공!"); 
				    		location.href="${root}";
				    	} else{
				    		alert("로그인 실패!");
				    	}
				    },
				    error : function(xhr, status) {
				    	console.log(xhr);
				        alert(xhr + " : " + status);
				    }
				});
	  	};
	  	
	  	function logout() {
	  		$.ajax({
			    cache : false,
			    url : "${root}/api/user/logout", // 요기에
			    type : "POST",
			    success : function(data) {
			    	if(data.res == 1) {
			    		alert("로그아웃 되었습니다.");
			    	} else {
			    		alert("로그아웃에 문제가 발생했습니다.");
			    	}
			    	location.href="${root}";
			    },
			    error : function(xhr, status) {
			        alert(xhr + " : " + status);
			    }
			});
	  	};
	  	
	  	function movefindpw() {
			document.location.href = "${root}/user/mvfindpw";
		};
      </script>
      