<%@page import="review.ReviewDTO"%>
<%@page import="java.util.List"%>
<%@page import="review.ReviewDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>review/list.jsp</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>


<%
String id=(String)session.getAttribute("id");
%>


<div class="container">
  <h2>Review</h2>
  <form action="writePro.jsp" method="post">
  
 <%
ReviewDAO dao=new ReviewDAO();

int pageSize=5;
System.out.println("pageSize=" + pageSize);

//현 페이지 번호 파라미터 값 가져오기
String pageNum=request.getParameter("pageNum");
//페이지 번호가 없으면 => "1"설정
if(pageNum==null){
	pageNum="1";
}
System.out.println("pageNum=" + pageNum);

int currentPage=Integer.parseInt(pageNum);

int startRow=(currentPage-1)*pageSize+1;
System.out.println("starRow="+startRow);

int endRow=(startRow-1)+pageSize;
System.out.println("endRow="+endRow);



List<ReviewDTO> reviewList=dao.getReviewList(startRow, pageSize);

int count=dao.getReviewCount();
System.out.println("Count="+count);
										 
%>
<input type="hidden" name="user_id" value="<%=id %>" readonly> 
  
  <table class="table table-striped">
    <thead>
      <tr>
        <th>상품</th>
        <th>별점</th>
        <th>내용</th>
        <th>등록일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
 	<% 
	for(int i=0; i<reviewList.size(); i++) {
		ReviewDTO dto = reviewList.get(i);
		%>
		<tr>
<%-- 		<td><%=dto.getReview_num() %></td> --%>
<%-- 			<td><%=dto.getUser_id() %></td> --%>
			<td><%=dto.getCar_num() %></td>
<%-- 			<td><a href="content.jsp?review_num=<%=dto.getReview_num()%>"><%=dto.getReview_cont() %></a></td> --%>
			<td><%=dto.getReview_star() %></td>
			<td><%=dto.getReview_cont() %></td>
			
		    <td><%=dto.getReview_date() %></td>
		    <td><%=dto.getReadcount() %></td></tr>
		<%
	}
		%>
    </tbody>
  </table>
  <%
//한 화면에 보여줄 페이지 개수 설정
int pageBlock=5;

int startPage=(currentPage-1)/pageBlock*pageBlock+1;
System.out.println("startPage="+startPage);

int endPage=startPage+pageBlock-1;

int pageCount=count/pageSize+(count%pageSize==0 ? 0 : 1);
System.out.println("pageCount="+pageCount);
if(endPage>pageCount){
	endPage=pageCount;
}

//10페이지 이전
if(startPage > pageBlock){
	%>
	<a href="list.jsp?pageNum=<%=startPage-pageBlock %>">[10페이지 이전]</a>
	<%
}

//이전페이지 currentPage-1
if(currentPage > 1){
	%>
<%-- 	<a href="list.jsp?pageNum=<%=currentPage-1 %>">[1페이지 이전]</a> --%>
	<%
}

for(int i=startPage;i<=endPage;i++){//변수는 <%=% >여기 안에 넣음
	%>
	<a href="list.jsp?pageNum=<%=i %>"><%=i %></a>
	<%
}

// 다음페이지 currentPage+1
if(currentPage < pageCount){
	%>
<%-- 	<a href="list.jsp?pageNum=<%=currentPage+1 %>">[1페이지 다음]</a> --%>
	<%
}

//10페이지 다음
if(endPage < pageCount) {
	%>
	<a href="list.jsp?pageNum=<%=startPage+pageBlock %>">[10페이지 다음]</a>
	<%
}
%>

</form>
</div>

</body>
</html>
