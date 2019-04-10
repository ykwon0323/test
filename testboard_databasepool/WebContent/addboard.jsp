<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
    <%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	$(function(){
		$("form").submit(function(){
			var bool=true;
			$("tr>td").each(function(){
				if($(this).children().val()==""){
					alert($(this).children().attr("name")+"을 입력하세요");
					$(this).children().focus();
					bool=false;//submit이벤트 취소를 위한 false
					return false;//현재 실행중인 함수를 종료하기 위한 false
				}
			});
			return bool;
		});//submit()종료
	});
</script>
</head>
<body>
<h1>게시판글쓰기</h1>
<form action="Controller.do" method="post">
<input type="hidden" name="command" value="write"/>
<table border="1">
	<tr>
		<th>작성자</th>
		<td><input type="text" name="writer"/></td>
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
		<td colspan="2"><input type="submit" value="글추가"/></td>
	</tr>
</table>
</form>







</body>
</html>