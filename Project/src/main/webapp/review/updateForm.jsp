<%@page import="review.ReviewDTO"%>
<%@page import="review.ReviewDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>review/updateForm.jsp</title>
    <link rel="stylesheet" href="./review1.css"/>
    <script type="text/javascript" src="./review1.js"></script>

</head>
<body>
<%
String id=(String)session.getAttribute("id");
int review_num=Integer.parseInt(request.getParameter("review_num"));
ReviewDAO dao=new ReviewDAO();

ReviewDTO dto=dao.getReivew(review_num);

System.out.println("review_num" + review_num);
%>


    <div class="wrap">
        <h1>REVIEW</h1>
        <form name="reviewform" class="reviewform" method="post" action="writePro.jsp" >
            <input type="hidden" name="rate" id="rate" value="0">
            
            <p align="center" class="title_star">별점과 이용후기를 남겨주세요</p>
     
            <div class="review_rating rating_point">
                <div class="warning_msg">별점을 선택해 주세요.</div>
                <div class="rating">
                    <div class="ratefill"></div>
                    <!-- [D] 해당 별점이 선택될 때 그 점수 이하의 input엘리먼트에 checked 클래스 추가 -->
                    <input type="checkbox" name="review_star" id="rating11" value="1" class="rate_radio" title="1점">
                    <label for="rating11"></label>
                    <input type="checkbox" name="review_star" id="rating12" value="2" class="rate_radio" title="2점">
                    <label for="rating12"></label>
                    <input type="checkbox" name="review_star" id="rating13" value="3" class="rate_radio" title="3점" >
                    <label for="rating13"></label>
                    <input type="checkbox" name="review_star" id="rating14" value="4" class="rate_radio" title="4점">
                    <label for="rating14"></label>
                    <input type="checkbox" name="review_star" id="rating15" value="5" class="rate_radio" title="5점">
                    <label for="rating15"></label>
                </div>
            </div>
			<br>
			<br>
			<br>
			
            <div class="review_contents">
                <div class="warning_msg"></div>
                <textarea name="review_cont" rows="10" class="review_textarea" 
                		  minlength="5" maxlength="1500" placeholder="내용을 입력하세요" ><%=dto.getReview_cont() %></textarea>
            </div>
      
            <div class="cmd">
<!--                <input type="button" name="save" id="save" value="등록하기" > -->

				
               <button type="button" name="save" id="save">수정완료</button>
               <input type="submit" id="btnSubmit" style="display: none;">
			   
           	   <input type="hidden" name="car_num"><br>
           	   <input type="hidden" name="user_id" value="<%=id %>" readonly>
               <input type="hidden" name="review_num"><br>
            </div>
            
 </form>


            
        
    </div>
</body>
</html>