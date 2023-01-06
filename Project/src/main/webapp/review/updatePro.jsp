<%@page import="review.ReviewDAO"%>
<%@page import="review.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//reivew/updatePro.jsp

request.setCharacterEncoding("utf-8");

int review_num=Integer.parseInt(request.getParameter("review_num"));
String user_id=request.getParameter("user_id");
String car_num=request.getParameter("car_num");
String review_star = request.getParameterValues("review_star").length + "";
String review_sub=request.getParameter("review_sub");
String review_cont=request.getParameter("review_cont");

ReviewDTO dto=new ReviewDTO();

dto.setReview_num(review_num);
dto.setUser_id(user_id);
dto.setCar_num(car_num);
dto.setReview_star(review_star);
dto.setReview_sub(review_sub);
dto.setReview_cont(review_cont);

ReviewDAO dao=new ReviewDAO();


if(dto!=null){
	dao.updateReivew(dto);
}

response.sendRedirect("list.jsp");
%>