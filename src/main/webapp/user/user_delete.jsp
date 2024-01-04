<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<body>
	<jsp:include page="../include/header.jsp"/>


	<div align="center" class="div_center">
		<h3>현재 비밀번호를 입력하세요.</h3>
		<hr>

		<form action="" method="post"> 
			비밀번호: <input type="password" name="pw">&nbsp;
			<input type="submit" value="확인" class="btn btn-default"> 
		</form>

	</div>
	
	<jsp:include page="../include/footer.jsp"/>
</body>
<%@include file="../include/footer.jsp"%>