<%@page import="Dto.TestBoardDto" %>
<%@page import="Dao.TestBoardDao" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%request.setCharacterEncoding("utf-8"); %>
 <%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수정하기</title>
</head>
<body>
<% 
	TestBoardDto dto=(TestBoardDto)request.getAttribute("dto");// Object타입에 저장되도록 설계
%>
<h1>글수정하기</h1>
<form action="Controller.do" method="post">
<input type="hidden" name="command" value="update"/>
<table border="1">
	<tr>
		<th>번호</th>
		<td><input type="text" name="seq" value="<%=dto.getSeq() %>" readonly></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><input type="text" name="writer" value="<%=dto.getWriter() %>" readonly></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><input type="text" name="title"/></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" name="conts"></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="수정완료">
			
		</td>
	</tr>
</table>
</form>

</body>
</html>