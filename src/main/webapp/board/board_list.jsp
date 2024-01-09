<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../include/header.jsp" %>
	<div class="container">
		
		<h3>My Web게시판</h3>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>글 번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			
			

			<tbody>
			
			<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.bno }</td>
					<td>${vo.writer }</td>
					<td><a href="content.board?bno=${vo.bno }"><font color="black">${vo.title }</font></a></td>
					<td><fmt:formatDate value="${vo.regdate }" pattern="yyyy년 MM월dd일 HH시mm분ss초" /></td>
					<td>${vo.hit }</td>
				</tr>
					</c:forEach>
			</tbody>
			
			<tbody>
				<tr>
					<td colspan="5" align="right">
						<form action="" class="form-inline" >
						  <div class="form-group">
						    <input type="text" name="search" placeholder="제목검색" class="form-control" >
						  	<input type="submit" value="검색" class="btn btn-default">
							<input type="button" value="글 작성" class="btn btn-default" onclick = "location.href='write.board'; ">
						  </div>
						</form> 
					</td>
				</tr>
			</tbody>
		
		</table>
	</div>


</body>
<%@include file="../include/footer.jsp"%>




