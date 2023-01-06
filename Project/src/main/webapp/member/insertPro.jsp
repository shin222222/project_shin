<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@page import="com.itwillbs.member.db.MemberDAO"%>
<%@page import="java.sql.Timestamp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/insertPro.jsp</title>
</head>
<body>
<%

request.setCharacterEncoding("UTF-8");
//폼에 입력한 데이터 -> 서버 request저장
//id pass name 변수 파라미터값 가져와서 저장
String id=request.getParameter("id");
String pass=request.getParameter("pass");
String name=request.getParameter("name");

//웹서버의 날짜시간 가져오기
Timestamp date = new Timestamp(System.currentTimeMillis());

// 디비작업 => 1~4단계 자바파일에 메서드 정의
// package member, 파일이름 MemberDAO 
// => insertMember() 메서드 정의

// MemberDAO자바파일 => 객체생성(기억장소 할당)

MemberDAO dao = new MemberDAO();
System.out.println("MemberDAO 주소 : " + dao);
// 메서드 호출(id,pass,name,date 전달)
// id,pass,name,date를 저장할 바구니 만들기 => 자바파일
// package member, 파일이름 MemberDTO

// 바구니에 id,pass,name,date를 담기
// MemberDTO => 객체생성(기억장소 할당)
MemberDTO dto = new MemberDTO(); //바구니 준비
System.out.println("MemberDTO 주소 : " + dto);
// id,pass,name,date 변수 담기 => 변수 private 접근제한
//dto.id=id;
dto.setId(id);//바구니에 값담기
dto.setPass(pass);
dto.setName(name);
dto.setDate(date);

//dao.insertMember(id, pass, name, date);
//dao.insertMember(MemberDTO 주소);
dao.insertMember(dto); //dto라는 바구니에 id, pass, name, date한꺼번에 담고
						//dto 주소값가지고 감

//로그인 페이지로 이동
response.sendRedirect("loginForm.jsp");
%>
<%-- 회원가입 성공 <%=pstmt %> --%>
</body>
</html>