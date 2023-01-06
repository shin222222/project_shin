package com.itwillbs.member.db;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
//		//2단계 JDBC 프로그램 이용해서 데이터베이스 연결
		String dbUrl="jdbc:mysql://localhost:3306/jspdb2?serverTimezone=Asia/Seoul";
		String dbUser="root";
		String dbPass="1234";
		Connection con=
		 DriverManager.getConnection(dbUrl, dbUser, dbPass);
		return con;
	}
	
	// 디비작업 => 1~4단계 자바파일에 메서드 정의
//							(바구니 주소를 저장하는 변수)
	public void insertMember(MemberDTO dto) {
		// 1~4단계
		System.out.println("MemberDAO insertMember()");
		System.out.println("전달받은 바구니(dto)의 주소 : " + dto);
		System.out.println("전달받은 바구니 안에 있는 id 값 : " + dto.getId());
		System.out.println("전달받은 바구니 안에 있는 pass 값 : " + dto.getPass());
		System.out.println("전달받은 바구니 안에 있는 name 값 : " + dto.getName());
		System.out.println("전달받은 바구니 안에 있는 date 값 : " + dto.getDate());
		
		try {
			// 예외가 발생할 가능성 높은 코드(명령)
			// 1, 2단계 메서드 호출
			Connection con = getConnection();
			
			// 3단계 sql구문을 만들고 실행할 준비
			String sql="insert into members(id, pass, name, date) values(?,?,?,?)";
			PreparedStatement pstmt =con.prepareStatement(sql);
			// ? 값을 넣어서 sql구문 완성
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPass());
			pstmt.setString(3, dto.getName());
			pstmt.setTimestamp(4, dto.getDate());
			
			//4단계 : sql구문 실행 (insert)
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// 예외가 발생하면 처리하는 곳
			e.printStackTrace();
		} finally {
			// 예외 상관없이 마무리 작업 
			// => Connection, PreparedStatement
			// => 기억장소 해제
		}	
		return;
	}	// insertMember() 메서드
	
	// MemberDTO(리턴할형) userCheck(String id, String pass) 메서드 정의
//	접근지정자 리턴할형 메서드이름(전달받은 값을 저장하는 변수)
	
	public MemberDTO userCheck(String id, String pass) {
		// MemberDTO 변수 선언 초기값 null
		MemberDTO dto=null;
		try {
			//1,2 디비연결
			Connection con = getConnection();
			// 3단계 sql구문을 만들고 실행할 준비 update
			// String sql="select * from student where num == ?";
			String sql="select * from members where id= ? and pass=?";
			PreparedStatement pstmt =con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			//4단계 : sql구문 실행, 실행결과 저장 (select)
			ResultSet rs = pstmt.executeQuery();
			//5단계 : 결과를 출력, 배열저장(select)
			// if 다음행 이동 => 데이터 달러있으면 => true => "아이디 비밀번호 일치"
//			 				  데이터 없으면 => false => "아이디 비밀번호 불일치"

			if(rs.next())  {
				// 데이터 있으면 => true => "아이디 비밀번호 일치
	//				out.println("아이디 비밀번호 일치");
				// id pass name date => 바구니 MemberDTO 저장
				// MemberDTO 객체생성
				dto = new MemberDTO();
				// set메서드 호출 값(디비 가져온값)을 저장
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setDate(rs.getTimestamp("date"));
				
			} else {
	//				out.println("아이디 비밀번호 불일치");
				// "입력하신 정보 틀림", 뒤로이동
	//					초기값 null
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 마무리
		}
		System.out.println("회원정보가 저장된 리턴할 주소: " + dto);
		return dto;
	}
	
	public MemberDTO getMember(String id) {
		// MemberDTO 변수 선언 초기값 null
		MemberDTO dto=null;
		try {
			//1,2 디비연결
			Connection con = getConnection();
			// 3단계 sql구문을 만들고 실행할 준비 update
			// String sql="String sql="select * from 테이블이름 where id= ?";
			String sql="select * from members where id= ?";
			PreparedStatement pstmt =con.prepareStatement(sql);
			pstmt.setString(1, id);

			//4단계 : sql구문 실행, 실행결과 저장 (select)
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				// 데이터 있으면 => true => "아이디 비밀번호 일치
				// out.println("아이디 비밀번호 일치");
				// id pass name date => 바구니 MemberDTO 저장
				// MemberDTO 객체생성
				dto = new MemberDTO();
				// set메서드 호출 값(디비 가져온값)을 저장
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setDate(rs.getTimestamp("date"));
			} 
			
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
			// 마무리	
			}
			return dto;
		}
	
	// 리턴할형 없음 void updateMember(수정할 정보MemberDTO 주소) 메서드 정의
	public void updateMember(MemberDTO dto) {
		// 1~4단계
		System.out.println("MemberDAO updateMember()");
		System.out.println("전달받은 바구니(dto)의 주소 : " + dto);
		System.out.println("전달받은 바구니 안에 있는 id 값 : " + dto.getId());
		System.out.println("전달받은 바구니 안에 있는 pass 값 : " + dto.getPass());
		System.out.println("전달받은 바구니 안에 있는 name 값 : " + dto.getName());
//		System.out.println("전달받은 바구니 안에 있는 date 값 : " + dto.getDate());
		
		try {
			// 예외가 발생할 가능성 높은 코드(명령)
			// 1, 2단계 메서드 호출
			Connection con = getConnection();
			
			// 3단계 sql구문을 만들고 실행할 준비
			
			String sql="update members set name=? where id=?";
			PreparedStatement pstmt =con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			
			//4단계 : sql구문 실행 (insert)
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// 예외가 발생하면 처리하는 곳
			e.printStackTrace();
		} finally {
			// 예외 상관없이 마무리 작업 
			// => Connection, PreparedStatement
			// => 기억장소 해제
		}	
		return;
	}	// insertMember() 메서드
	
	// 리턴할형 없음 void updateMember(수정할 정보MemberDTO 주소) 메서드 정의
	public void deleteMember(String id) {
		try {
			// 예외가 발생할 가능성 높은 코드(명령)
			// 1, 2단계 메서드 호출
			Connection con = getConnection();
			
			// 3단계 sql구문을 만들고 실행할 준비 update
			// String sql="update 테이블이름 set 수정할열=값 where 조건열=값";
			String sql="delete from members where id = ?";
			PreparedStatement pstmt =con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 4단계 : sql구문 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// 예외가 발생하면 처리하는 곳
			e.printStackTrace();
		} finally {
			// 예외 상관없이 마무리 작업 
			// => Connection, PreparedStatement
			// => 기억장소 해제
		}	
		return;
	}	// deleteMember() 메서드
	
	public List<MemberDTO> getMemberList() {
		// 배열 객체생성할 때 같은형이 저장되도록 정의=> 제네릭타입 추가
//		List memberList=new ArrayList();
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		System.out.println("배열의 주소: " + memberList);
		try {
			//1,2단계
			Connection con = getConnection();
			//3단계 sql구문을 만들고 실행할 준비 update
			// String sql="select * from 테이블이름";
			String sql="select * from members";
			PreparedStatement pstmt =con.prepareStatement(sql);
			//4단계 : sql구문 실행, 결과 저장 (update)
			ResultSet rs = pstmt.executeQuery();
			//5단계 : while 결과를 출력, 배열저장(select)
			while(rs.next()) {
				// 한사람의 데이터 id, pass, name, date => MemberDTO
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setDate(rs.getTimestamp("date"));
				// 한사람의 데이터를 배열 한칸에 저장 => memberList
				memberList.add(dto);
				// MemberDTO형에서 Object형으로 업캐스팅됨
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return memberList;
	}
		
	
}	// 클래스
