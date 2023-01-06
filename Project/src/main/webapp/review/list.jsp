<%@page import="java.text.SimpleDateFormat"%>
<%@page import="review.ReviewDTO"%>
<%@page import="review.ReviewDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>review/list.jsp</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>



</head>
<body>

<%
String id=(String)session.getAttribute("id");
%>


<%
ReviewDAO dao=new ReviewDAO();

int pageSize=10;
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

<form action="writePro.jsp" method="post">  
<div class="container">
  <h2>review</h2>
      
  <table class="table table-striped">
    <thead>
      <tr>
      	<th>번호<th>
<!--         <th align=center>상품</th> -->
        <th>별점</th>
        <th width=500>내용</th>
        <th>등록일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
    
	<% 
// 	ReviewDTO dto = new ReviewDTO();
	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");
	for(int i=0; i<reviewList.size(); i++) {
		ReviewDTO dto = reviewList.get(i);
					
		%>
		<tr> 
			<td><%=dto.getReview_num() %></td>
			<td><%=dto.getCar_num() %></td>
			<td><%=dto.getReview_star()%></td>
			<td height="100"><a href="content.jsp?review_num=<%=dto.getReview_num()%>"><%=dto.getReview_cont() %></a></td>
<%-- 		    <td><%=dto.getReview_date() %></td> --%>
		    <td><%=dateFormat.format(dto.getReview_date()) %></td>
		    <td><%=dto.getReadcount() %></td>
		    <td><input type="button" value="수정" onclick="location.href='updateForm.jsp?review_num=<%=dto.getReview_num() %>'">
	<input type="button" value="삭제" onclick="location.href='delete.jsp?review_num=<%=dto.getReview_num() %>'"></td>
		    </tr>
		   
		<%
	}
	%>
		
    </tbody>
  </table>

  
  
  <%
//한 화면에 보여줄 페이지 개수 설정
int pageBlock=10;

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

</div>

 <input type="hidden" name="user_id" value="<%=id %>" readonly>
</form> 
</body>


</html>