<%@page import="javax.script.ScriptContext"%>
<%@page import="java.util.Arrays"%>
<%@page import="review.ReviewDAO"%>
<%@page import="review.ReviewDTO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>review/writePro.jsp</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");

String user_id=request.getParameter("user_id");
String car_num=request.getParameter("car_num");
String review_star = request.getParameterValues("review_star").length + "";
// String review_star = request.getParameter("review_star");

System.out.println(request.getParameter("review_star"));
String review_cont=request.getParameter("review_cont");


ReviewDTO dto=new ReviewDTO();
//num값은 BoardDAO에서 작업할거임
dto.setUser_id(user_id);
dto.setCar_num(car_num);
dto.setReview_star(review_star);
dto.setReview_cont(review_cont);
dto.setReview_date(new Timestamp(System.currentTimeMillis())); 
dto.setReadcount(0); 


ReviewDAO dao=new ReviewDAO();
dao.insertReview(dto);


//글목록이동
response.sendRedirect("list.jsp");
%>
</body>
</html>