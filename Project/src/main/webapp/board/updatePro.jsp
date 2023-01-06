<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//<board2/updatePro2.jsp>

//request 한글처리
request.setCharacterEncoding("utf-8");
//request 정보 가져오기
//num name subject content 파라미터 가져와서 변수에 저장
int num=Integer.parseInt(request.getParameter("num"));
String name=request.getParameter("name");
String subject=request.getParameter("subject");
String content=request.getParameter("content");

//BoardDTO 객체생성
BoardDTO dto=new BoardDTO();

//set메서드 호출 num name subject content 저장
dto.setNum(num);
dto.setName(name);
dto.setSubject(subject);
dto.setContent(content);


//수정작업
//BoardDAO 객체생성
BoardDAO dao=new BoardDAO();
//리턴할 형 없음 updateBoard(BoardDTO변수) 메서드 정의
//DB작업 주소를 통해서 updateBoard(BoardDTO 주소값)호출
//dao.updateBoard(dto);//BoardDTO 주소값 = dao임//(18번)

if(dto!=null){
	dao.updateBoard(dto);
}
//list.jsp 이동
response.sendRedirect("list.jsp");
	

%>






