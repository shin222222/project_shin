<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@page import="com.itwillbs.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/deletePro.jsp</title>
</head>
<body>
<%
String id=request.getParameter("id");
String pass=request.getParameter("pass");

//MemberDAO 객체생성
MemberDAO dao = new MemberDAO();
//MemberDTO dto = 디비작업주소.userCheck(id,pass) 메서드 호출
MemberDTO dto = dao.userCheck(id, pass);//리턴값이 MemberDTO형으로 됨

if(dto!=null){
	out.print("아이디 비밀번호 일치");
	// 리턴할형없음 void deleteMember(String id) 메서드 정의
	// deleteMember(id) 메서드 호출
	
	dao.deleteMember(id);

	session.invalidate();
	response.sendRedirect("main.jsp");

} else {
	out.print("아이디 비밀번호 틀림");
	//"입력하신 정보 틀림", 뒤로이동
	%>
	<script type="text/javascript">
	alert("입력하신 정보 틀림");
	history.back();
	</script>
	<%
}
%>
</body>
</html>