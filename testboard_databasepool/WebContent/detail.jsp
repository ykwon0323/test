<%@page import = "Dto.TestBoardDto" %>
<%@page import = "Dao.TestBoardDao" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<% 
	TestBoardDto dto = (TestBoardDto)request.getAttribute("dto");
%>
<title>상세보기</title>
</head>
<body>

<h1>글상세보기</h1>
<table border="1">
	<tr>
		<th>번호</th>
		<td><%=dto.getSeq() %></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%=dto.getWriter() %></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><%=dto.getTitle() %></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getConts() %></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="Controller.do?command=updateform&seq=<%=dto.getSeq()%>&writer=<%=dto.getWriter() %>">수정하기</a>
			<button onclick="delBoard()">삭제</button>
			<a href="Controller.do?command=list">목록보기</a>
		</td>
	</tr>
</table>
<script type="text/javascript">
	function updateForm(){
		//updateform.jsp ---> update_after.jsp(수정처리)--> detailboard.jsp
		location.href="update.jsp";
	}
	function delBoard(){
		//delBoard.jsp: dao호출 delboard(seq)실행 
		//              --->성공 list.jsp--->실패 dedatil.jsp
		location.href="Controller.do?command=delete&seq=${dto.seq}";
	}
</script>

</body>
</html>