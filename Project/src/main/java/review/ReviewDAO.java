package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ReviewDAO {

	Connection con=null;
	PreparedStatement pstmt2=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	public Connection getConnection() throws Exception {

		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		con=ds.getConnection();
		
		return con;
	}
	
	
	public void close() {
		if(rs!=null) try{rs.close();} catch(SQLException ex) {}
		if(pstmt2!=null) try{pstmt2.close();} catch(SQLException ex) {}
		if(pstmt!=null) try{pstmt.close();} catch(SQLException ex) {}
		if(con!=null) try{con.close();} catch(SQLException ex) {}
	}
	
	
	public void insertReview(ReviewDTO dto) {
		
		
		System.out.println("ReviewDAO insertReview()");
		System.out.println("ReviewDAO 주소 : " + dto);
		System.out.println("ReviewDAO User_Id : " + dto.getUser_id());
		System.out.println("ReviewDAO Car_num : " + dto.getCar_num());		
		System.out.println("ReviewDAO Review_star : " + dto.getReview_star());
		System.out.println("ReviewDAO Review_title : " + dto.getReview_sub());
		System.out.println("ReviewDAO Review_cont : " + dto.getReview_cont());
		System.out.println("ReviewDAO readcount : " + dto.getReadcount());
		System.out.println("ReviewDAO Review_date : " + dto.getReview_date());
		System.out.println("======================================================");
		
		try {
			con=getConnection();

			String sql2="select max(review_num) from review";
			pstmt2=con.prepareStatement(sql2);
			rs=pstmt2.executeQuery();
			
			int review_num=0;
			if(rs.next()) {
				review_num=rs.getInt("max(review_num)")+1;
				
			}
			
			String sql="insert into review(review_num, user_id, car_num, review_star, review_sub, review_cont, readcount, review_date) values(?,?,?,?,?,?,?,?)";

			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, review_num);
			pstmt.setString(2, dto.getUser_id());
			pstmt.setString(3, dto.getCar_num());
			pstmt.setString(4, dto.getReview_star());
			pstmt.setString(5, dto.getReview_sub());
			pstmt.setString(6, dto.getReview_cont());
			pstmt.setInt(7,dto.getReadcount());
			pstmt.setTimestamp(8, dto.getReview_date());;
			
			pstmt.executeUpdate();
			
			System.out.println(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close();
		}
	}//insertReview() 메서드
	
	
	
	
	public List<ReviewDTO> getReviewList(int startRow, int pageSize) {
		
		List<ReviewDTO> reviewList=new ArrayList<ReviewDTO>();
		
		try {
			con=getConnection();
			
			String sql="select * from review order by review_num desc limit ?,?";
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReview_num(rs.getInt("review_num"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setCar_num(rs.getString("car_num"));
				dto.setReview_star(rs.getString("review_star"));
				dto.setReview_sub(rs.getString("review_sub"));
				dto.setReview_cont(rs.getString("review_cont"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setReview_date(rs.getTimestamp("review_date"));
				
				reviewList.add(dto);
			}
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return reviewList;
	
	}//reivewList()	
	
	
	
	public ReviewDTO getReivew(int review_num) {
		ReviewDTO dto = null;
		
		try {
			con = getConnection();
			String sql="select * from review where review_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,review_num);

			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto = new ReviewDTO(); 
				//set메서드 호출 값을 저장 
				dto.setReview_num(review_num);
				dto.setUser_id(rs.getString("user_id"));
				dto.setCar_num(rs.getString("car_num"));
				dto.setReview_star(rs.getString("review_star"));
				dto.setReview_sub(rs.getString("review_sub"));
				dto.setReview_cont(rs.getString("review_cont"));
				dto.setReview_date(rs.getTimestamp("review_date"));
				dto.setReadcount(rs.getInt("Readcount"));

			} else {
					//"입력하신 정보 틀림", 뒤로이동
			}		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return dto; 
	} //getReview()
	

	public void updatetReadcount(int review_num) {
			
		try {
			con = getConnection();
			
			String sql="update review set readcount=readcount+1 where review_num=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1,review_num);

			pstmt.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();	
		}

	}//updatetReadcount() 메서드
	
	public void updateReivew(ReviewDTO dto) {
		
		
		try {
			con = getConnection();
			
			String sql="update review set review_sub=?,review_cont=? where review_num=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1,dto.getReview_sub());
			pstmt.setString(2,dto.getReview_cont());
			pstmt.setInt(3,dto.getReview_num());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	return;
	}//updateReview() 메서드
	
	
	
	public void deleteReview(int review_num) {

		
		try {
			con=getConnection();
			
			String sql="delete from review where review_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,review_num);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return;
		
	}//deleteReview()
	
	
	
	
	public int getReviewCount() {
		int count=0;
		
		try {
			con=getConnection();
			
			String sql="select count(*) from review";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt("count(*)");  
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return count;
		
		
	}
	
	
}