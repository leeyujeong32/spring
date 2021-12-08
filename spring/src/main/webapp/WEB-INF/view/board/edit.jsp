<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시판 수정</h2>

	<form action="update.do" method="post">
	<input type="hidden" name="boardno" value="${data.boardno }">
	<table border="1" >
		<tr>
			<td>제목</td>
			<td><input type="text" name=title value="${data.title}"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name=content>${data.content}</textarea></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name=writer value="${data.writer}"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="수정">
		</tr>
		</table>
	</form>

</body>
</html>