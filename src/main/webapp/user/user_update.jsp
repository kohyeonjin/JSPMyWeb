<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<body>

	<div align="center" class="div_center">
	
	<form action="updateForm.user" method="post" >
		<h3>회원정보 수정 페이지</h3>
		<hr>
		<table border="1">
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="id" value="${requestScope.vo.getId() }" readonly	>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
					<td>
						<input type="password" name="pw" value="${requestScope.vo.getPw() }" placeholder="비밀번호는 5자 이상 입력" required>
					</td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td>
						<input type="password" name="pw_check" placeholder="비밀번호는 5자 이상 입력" required>
					</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name" value="${requestScope.vo.getName() }">
				</td>
				
			</tr>

			<tr>
				<td>이메일</td>
				<td>
					<input type="text" name="email" value="${requestScope.vo.getEmail() }" placeholder="ex) abc@def.com">
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" name="address" value="${requestScope.vo.getAddress() }" placeholder="ex) OO시 OO구">
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<input type="radio" name="gender" ${vo.getGender() == 'M' ? 'checked' : '' } value = "M">남자
					<input type="radio" name="gender" ${vo.getGender() == 'F' ? 'checked' : '' } value = "F">여자
					
				</td>
			</tr>
		</table>
		<br>
		
		<input type="submit" value="수정" class="btn btn-default" onclick="">&nbsp;&nbsp;
		<input type="button" value="취소" class="btn btn-default" onclick="">    
		
	</form>	
	</div>



	<jsp:include page="../include/footer.jsp"/>
	
</body>
<%@include file="../include/footer.jsp"%>