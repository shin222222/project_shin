package com.itwillbs.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con=null;
	PreparedStatement pstmt2=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public Connection getConnection() throws Exception {
//		// 예외처리를 함수 호출하는 곳으로 전달
//		
//		//1단계 JDBC 프로그램 가져오기
//		Class.forName("com.mysql.cj.jdbc.Driver");
////		//2단계 JDBC 프로그램 이용해서 데이터베이스 연결
//		String dbUrl="jdbc:mysql://localhost:3306/jspdb2?serverTimezone=Asia/Seoul";
//		String dbUser="root";
//		String dbPass="1234";
//		con= DriverManager.getConnection(dbUrl, dbUser, dbPass);
//		return con;
		
		// Connection Pool : 서버에서 드라이버로더, 디비연결을 미리하고
		// 그 디비연결 자원의 이름을 부여해서 DAO에서 자원의 이름을 호출해서 사용
		// 1. 속도 향상
		// 2. 디비연결정보 수정을 최소화(한 번 만 수정)
		
		// 설치 : 웹서버에 DBCP(DataBase Connection Pool) 프로그램 설치
		// META-INF 폴더에 context.xml 파일을 만들고
		// 디비연결정보(1,2단계)를 저장 => 자원 이름으로 정의
		// DAO에서 자원 이름을 호출해서 사용
		
		// Context 객체 생성
		// import javax.naming.Context;
		// import javax.naming.InitialContext;
		Context init=new InitialContext();
		// 자원이름호출("자원위치/자원이름") => DataSource 에 저장
		// import javax.sql.DataSource;
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		// DataSource => con 에 저장
		con=ds.getConnection();
		return con;
	}
	
	
	public void close() {
		if(con!=null) try{con.close();}catch(SQLException ex) {}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException ex) {}
		if(pstmt2!=null) try{pstmt2.close();}catch(SQLException ex) {}
		if(rs!=null) try{rs.close();}catch(SQLException ex) {}
	}

	public void insertBoard(BoardDTO dto) {
		System.out.println("BoardDTO name 값 : " + dto.getName());
		System.out.println("BoardDTO subject 값 : " + dto.getSubject());
		System.out.println("BoardDTO content 값 : " + dto.getContent());
		System.out.println("BoardDTO readcount 값 : " + dto.getReadcount());
		System.out.println("BoardDTO date 값 : " + dto.getDate());
		try {
			// 1,2 메서드 호출
			con = getConnection();
			// 게시판 글번호 구하기 가장 큰번호 +1 => 이번에 입력할 글번호 
			// 3. select max(num) from board;
			String sql2 = "select max(num) from board";
			pstmt2 = con.prepareStatement(sql2);
			// 4. 실행 => 결과 저장 
			rs=pstmt2.executeQuery();
			// 5. 결과접근 max(num)가져와서 +1
			int num=0;
			if(rs.next()) {
				num=rs.getInt("max(num)")+1; // max(num)은 컬럼명
			}
			// 3 SQL구문 만들기
			String sql="insert into board(num, name, subject, content, readcount, date) value(?,?,?,?,?,?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getReadcount());
			pstmt.setTimestamp(6, dto.getDate());
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 마무리 => 내장객체 => 기억장소 해제
			// con pstmt pstmt2, ResultSet => 기억장소 해제
			close();
		}
		
	}
	
	public List<BoardDTO> getBoardList(int startRow,int pageSize) {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		try {
			// 1,2단계 디비연결
			con = getConnection();
			// 3 sql
//			String sql="select * from board";
//			String sql="select * from board order by num desc limit 시작행,글개수";
			String sql="select * from board order by num desc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			// 4 실행 => 결과저장
			rs=pstmt.executeQuery();
			// 5 while 결과 접근 
			while (rs.next()) {
				BoardDTO dto=new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setReadcount(rs.getInt("readcount"));
				
				boardList.add(dto);
			}
			// => BoardDTO 객체생성 set호출 디비에서 가져온 값저장
			// => 글하나를 배열한칸에 저장
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return boardList;
	}// getBoardList
	
	public BoardDTO getBoard(int num) {
		// MemberDAO getMember 메서드 참고
		BoardDTO dto=null;
		try {
			con = getConnection();
			// 3단계 sql구문을 만들고 실행할 준비 update
			String sql="select * from board where num=? ";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//4단계 : sql구문 실행, 실행결과 저장 (select)
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 결과 있으면 => num에 대한 글 있음
				dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setReadcount(rs.getInt("readcount"));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}

	public void updateReadcount(int num) {
		try {
			con = getConnection();
			
			String sql="update board set readcount=readcount+1 where num=? ";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}
	
	public void updateBoard(BoardDTO dto) {
		try {
			// 예외가 발생할 가능성 높은 코드(명령)
			// 1, 2단계 메서드 호출
			con = getConnection();
			
			// 3단계 sql구문을 만들고 실행할 준비
			
			String sql="update Board set subject=?, content=? where num=?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
			
			//4단계 : sql구문 실행 (insert)
			pstmt.executeUpdate();
		} catch (Exception e) {
			// 예외가 발생하면 처리하는 곳
			e.printStackTrace();
		} finally {
			// 예외 상관없이 마무리 작업 
			// => Connection, PreparedStatement
			// => 기억장소 해제
			close();
		}	
		return;
	}	
	
	
	public void deleteBoard(int num) {
		try {
			// 예외가 발생할 가능성 높은 코드(명령)
			// 1, 2단계 메서드 호출
			con = getConnection();
			
			// 3단계 sql구문을 만들고 실행할 준비 update
			// String sql="update 테이블이름 set 수정할열=값 where 조건열=값";
			String sql="delete from board where num = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			// 4단계 : sql구문 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			// 예외가 발생하면 처리하는 곳
			e.printStackTrace();
		} finally {
			// 예외 상관없이 마무리 작업 
			// => Connection, PreparedStatement
			// => 기억장소 해제
			close();
		}	
		return;
		// deleteMember() 메서드
	}
	
	public int getBoardCount() {
		int count=0;
		try {
			con = getConnection();
			String sql="select count(*) from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt("count(*)"); // 열이름 count(*)
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return count;
	}
	
	
	
	
}
