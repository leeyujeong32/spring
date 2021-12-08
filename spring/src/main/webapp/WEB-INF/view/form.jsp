<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="send.do" method="post">
		이름 : <input type="text" name="name"><br>
		이메일 : <input type="text" name="email"><br>
		번호 : <input type="text" name="no"><br>
		취미 : <input type="checkbox" name="hobby" value="book">독서
				<input type="checkbox" name="hobby" value="movie">영화
				<input type="checkbox" name="hobby" value="game">게임<br>
		<input type="submit" value="전송">
	</form>
</body>
</html>