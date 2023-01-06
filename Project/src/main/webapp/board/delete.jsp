<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//board/delete.jsp

//delete.jsp?num=1  =>num값을 가져옴
int num=Integer.parseInt(request.getParameter("num")); //request로 꺼냄

//BoardDAO객체생성
BoardDAO dao=new BoardDAO();

//리턴할형 없음 deleteBoard(int num) 메서드 정의
//DB작업 주소를 통해서 deleteBoard(num) 호춯
dao.deleteBoard(num);

//list.jsp이동
response.sendRedirect("list.jsp");



%>