<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시판 등록</h2>

	<form action="insert.do" method="post" enctype="multipart/form-data">
	<table border="1" >
		<tr>
			<td>제목</td>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content"></textarea></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<td>이미지</td>
			<td>
				<input type="file" name="file">
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="등록">
		</tr>
		</table>
	</form>

</body>
</html>