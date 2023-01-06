package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;


public class BoardUpdatePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// board/updatePro.jsp
		// request 한글처리
		request.setCharacterEncoding("utf-8");
		// request 정보 가져오기
		// num, name, subject content 파라미터 가져와서 변수에 저장
		int num=Integer.parseInt(request.getParameter("num"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		// 수정작업
		// BoardDAO 객체생성
		BoardDAO dao = new BoardDAO();
		// 리턴할형 X updateBoard(BoardDTO 변수) 메서드 정의

		// 디비작업 주소를 통해서 updateBoard(BoardDTO 주소값) 호출
		// list.jsp 이동
			BoardDTO dto=new BoardDTO();
			dto.setNum(num);
			dto.setSubject(subject);
			dto.setContent(content);
			// 메서드 호출
			dao.updateBoard(dto);
		
		ActionForward forward=new ActionForward();
		forward.setPath("BoardList.bo");
		forward.setRedirect(false);
		return forward;
	}
	
	
	
}
