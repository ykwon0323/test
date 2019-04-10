<%@page import="Dto.TestBoardDto" %>
<%@page import="java.util.List" %>
<%@page import="Dao.TestBoardDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>    
 <%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function allchk(bool){
		
		var chks=document.getElementsByName("chk");
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked=bool;
		}
	}
</script>

</head>
<body>

<% 
	List<TestBoardDto> list = (List<TestBoardDto>)request.getAttribute("list");
%>

<h1>글목록</h1>
<form action="Controller.do" method="post">
<input type="hidden" name="command" value="muldel"/>
<table border="1">
    <col width="50px"/>
	<col width="50px"/>
	<col width="100px"/>
	<col width="300px"/>
	<col width="100px"/>
	<tr>
		<th><input type="checkbox" name="all" onclick="allchk(this.checked)" /> </th>
		<th>번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>작성일</th>
	</tr>
	<%
		if(list.size()==0){
			%>
			<tr>
				<td colspan="4">---작성된 글이 없습니다.---</td>
			</tr>
			<%
		}else{
			for(int i=0;i<list.size();i++){
				TestBoardDto dto=list.get(i);//list[dto,dto,dto]
				%>
				<tr>
				  	<td><input type="checkbox" name="chk" value="<%=dto.getSeq()%>" /></td>
					<td><%=dto.getSeq() %></td>
					<td><%=dto.getWriter() %></td>
					<td><a href="Controller.do?command=detail&seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a></td>
					<td><%=dto.getRegDate()%></td>
				</tr>
				<%
			}
		}
	%>
	<tr>
		<td colspan="5">
			<a href="Controller.do?command=writeform">글쓰기</a>
			<input type="submit" value="글삭제"/>
		</td>
	</tr>
</table>


</body>
</html>