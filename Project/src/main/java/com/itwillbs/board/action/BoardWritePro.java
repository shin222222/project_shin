package com.itwillbs.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardWritePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 글쓰기 폼에서 입력한 내용을 서버에 전달하면 
		// 내장객체 request에 저장
		// request 한글처리 
		request.setCharacterEncoding("utf-8");
		// request name, subject, content 파라미터 가져와서 변수에 저장
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		// 패키지board 파일이름 BoardDTO 자바 클래스 만들기
		// 멤버변수 num, name, subject, content, readcount, date 
		// set() get() 메서드 정의

		// BoardDTO 객체생성(기억장소 할당)
		BoardDTO dto = new BoardDTO();
		//set메서드 호출 파라미터값 저장
		dto.setName(name);
		dto.setSubject(subject);
		dto.setContent(content);
		// 현시스템 날짜시간
		dto.setDate(new Timestamp(System.currentTimeMillis()));
		// 조회수 0설정
		dto.setReadcount(0);
		// num => BoardDAO에서 작업
		System.out.println("MemberDTO 주소: " +dto);


		// 패키지 board 파일이름 BoardDAO 자바클래스 만들기

		// BoardDAO 객체생성(기억장소 할당)
		BoardDAO dao = new BoardDAO();



		// 리턴할형 없음 insertBoard(BoardDTO 주소값 저장하는 변수)메서드 정의

		// BoardDAO주소.insertBoard(BoardDTO 주소)메서드 호출
		dao.insertBoard(dto);
		// 글목록 이동
//		response.sendRedirect("list.jsp");
		ActionForward forward=new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		return forward;
	}
	
}
