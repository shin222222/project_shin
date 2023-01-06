<%@page import="review.ReviewDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//review/delete.jsp

int review_num=Integer.parseInt(request.getParameter("review_num")); 

ReviewDAO dao=new ReviewDAO();

dao.deleteReview(review_num);

response.sendRedirect("list.jsp");

%>
