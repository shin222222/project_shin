<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/list.jsp</title>
</head>
<body>
<h1>회원목록</h1>
<%
//세션값 가져오기
String id=(String)session.getAttribute("id");
if(id==null){
	//세션값이 없으면 => loginForm.jsp
	response.sendRedirect("loginForm.jsp");
}else{
	//세션값이 있으면 => admin 아니면 => main.jsp
	if( ! (id.equals("admin")) ){
		response.sendRedirect("main.jsp");
	}
}

List<MemberDTO> memberList
=(List<MemberDTO>)request.getAttribute("memberList");

// System.out.println("전달 받은 배열 주소 : "+memberList);

// //5단계 : 배열저장 for (select)
%>
<table border="1">
<tr>
	<th>아이디</th>
	<th>비밀번호</th>
	<th>이름</td>
	<th>가입날짜</th>
</tr>
    <%
    for(int i=0;i<memberList.size();i++){
//     	MemberDTO dto=(MemberDTO)memberList.get(i);
//      참조형의 형변환 없이 가져옴
		MemberDTO dto=memberList.get(i);
    	%>
		<tr>
			<td><%=dto.getId() %></td>
			<td><%=dto.getPass() %></td>
		    <td><%=dto.getName() %></td>
		    <td><%=dto.getDate() %></td>
		</tr>    	
	<%
    }
    %>
</table>
</body>
</html>