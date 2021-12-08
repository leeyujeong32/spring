<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<c:if test="${empty loginInfo }"> <!-- 작은 곳부터 다 찾기 -->
	<input type="button" value="로그인" onclick="location.href='/spring/user/login.do'">
	<input type="button" value="회원가입" onclick="location.href='/spring/user/regist.do'">
	</c:if>
	<c:if test="${!empty loginInfo }">
	${loginInfo.name}님 안녕하세요
	<input type="button" value="로그아웃" onclick="location.href='/spring/user/logout.do'">
	<input type="button" value="마이페이지" onclick="location.href='/spring/user/mypage.do'">
	</c:if>
</div>
<form method="get" action="mypage.do">
<select name="searchType">
	<option value="">전체</option>
	<option value="title" <c:if test="${param.searchType == 'title'}">selected</c:if>>제목</option>
	<option value="content" <c:if test="${param.searchType == 'content'}">selected</c:if>>내용</option>
</select>
	<input type="text" name="searchWord" value="${param.searchWord }">
	<input type="submit" value="검색">
</form>
<table border="1">
	<tr>
		<th>번호</th>
		<th>
			<c:choose>
				<c:when test="${param.orderCond == 'title_asc'}">
					<a href="mypage.do?orderCond=title_desc">제목 ↑</a>
				</c:when>
				<c:when test="${param.orderCond == 'title_desc'}">
					<a href="mypage.do?orderCond=title_desc">제목 ↓</a>
				</c:when>
				<c:otherwise>
					<a href="mypage.do?orderCond=title_asc">제목</a>
				</c:otherwise>
			</c:choose>
		</th>
		<th>작성자</th>
		<th>
			<c:choose>
				<c:when test="${param.orderCond == 'regdate_asc'}">
					<a href="mypage.do?orderCond=regdate_desc">작성일 ↑</a>
				</c:when>
				<c:when test="${param.orderCond == 'regdate_desc'}">
					<a href="mypage.do?orderCond=regdate_desc">작성일 ↓</a>
				</c:when>
				<c:otherwise>
					<a href="mypage.do?orderCond=regdate_asc">작성일</a>
				</c:otherwise>
			</c:choose>
		</th>
	</tr>
	<c:forEach var="vo" items="${list }">
	<tr>
		<td>${vo.boardno }</td>
		<td><a href="/spring/board/detail.do?boardno=${vo.boardno }">${vo.title }</a></td>
		<td>${vo.writer }</td>
		<td>${vo.regdate }</td>
	</tr>
	</c:forEach>
</table>
<c:forEach var="rpage" begin="1" end="${totPage }">
	<a href="mypage.do?page=${rpage}">[${rpage }]</a>
</c:forEach><br>

</body>
</html>