<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<%

String id=(String)session.getAttribute("id");
// id가 null(세션값이 없으면) loginForm.jsp 이동
if(id==null) {
	response.sendRedirect("./MemberLoginForm.me"); // jsp에서 이동
}
%>
<h1>메인 화면</h1>
<%=id %>님 로그인 하셨습니다.<br>
<a href="./MemberLogout.me">로그아웃</a><br>
<a href="./MemberInfo.me">정보조회</a><br>
<a href="./MemberUpdateForm.me">정보수정</a><br>
<a href="./MemberDeleteForm.me">정보삭제</a><br>
<a href="./QnaList.qn">QnA</a><br>
<a href="./CarList.ci">차정보</a><br>
<a href="./CarInsertForm.ci">차정보 등록</a><br>
<!-- admin 사용자만 보이게 설정
	 문자열 비교 => 문자열.equals(문자열)
	 id null이면 equals 사용못함 -->
<%
if(id!=null) {
	if(id.equals("admin")) {
		%>
		<a href="./MemberList.me">회원목록</a><br>
		<%
	}
}
//가상주소
//http://localhost:8080/Model2/BoardWriteForm.bo

if(id!=null){
	%>
	<a href="./BoardWriteForm.bo">글쓰기</a><br>
	<%
}
%>
<a href="./BoardList.bo">글목록</a>
</body>
</html>