<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<div align="center" class="div_center">

	<h3>게시글 내용 보기</h3>
	<hr>
	<table border="1" width="500">
		<tr>
			
			<td width="20%">글번호</td>
			<td width="30%">${content.getBno() }</td>
			
			<td width="20%">조회수</td>
			<td width="30%">${content.getHit() }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${content.writer }</td>
			
			<td>작성일</td>
			<td >${content.regdate }</td>
		</tr>
		
		<tr>
			<td width="20%">글제목</td>
			<td colspan="3">${content.title }</td>
		</tr>
		<tr>
			<td width="20%">글내용</td>
			<td colspan="3" height="120px">${content.content}</td>
		</tr>
	
		<tr>
			<td colspan="4" align="center">
				
				<input type="button" value="목록" onclick="location.href='list.board';">&nbsp;&nbsp;
				<input type="button" value="수정" onclick="location.href='modify.board?bno=${content.getBno()}';">&nbsp;&nbsp;
				<input type="button" value="삭제">&nbsp;&nbsp;
			</td>
		</tr>
		
		
	</table>
	
	

	

</div>

<%@include file="../include/footer.jsp"%>