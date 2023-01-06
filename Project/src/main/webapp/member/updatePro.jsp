
<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@page import="com.itwillbs.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/updatePro.jsp</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
//폼에서 입력한 파라미터값(id,pass,name)을 서버에 request에 저장
//변수 = request에 저장된 값 가져오기
String id=request.getParameter("id");
String pass=request.getParameter("pass");
String name=request.getParameter("name");

//MemberDAO 객체생성
MemberDAO dao = new MemberDAO();
//MemberDTO dto = 디비작업주소.userCheck(id,pass) 메서드 호출
MemberDTO dto = dao.userCheck(id, pass);

//dto가 null이 아니면 => 아이디 비밀번호 일치 => 수정, 메인이동
//null이면	   => 아이디 비밀번호 틀림 => 뒤로이동
if(dto!=null){
	out.print("아이디 비밀번호 일치");
	//다음행 이동=> 데이터 있으면 => true => "아이디 비밀번호 일치"
	//수정할 정보를 MemberDTO에 객체생성 set 호출 저장

	// 리턴할형없음 void updateMember(수정할 정보 : MemberDTO 주소) 메서드 정의
	// updateMember(MemberDTO 주소) 메서드 호출	
	MemberDTO dtoUpdate=new MemberDTO();
	dtoUpdate.setId(id);
	dtoUpdate.setPass(pass);
	dtoUpdate.setName(name);
	

	dao.updateMember(dtoUpdate);
	
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