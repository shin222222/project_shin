package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// delete.jsp?num=1
		int num=Integer.parseInt(request.getParameter("num"));
		// BoardDAO 객체생성
		BoardDAO dao = new BoardDAO();

		// 리턴할형없음 deleteBoard(int num) 메서드 정의
		dao.deleteBoard(num);

		ActionForward forward=new ActionForward();
		forward.setPath("BoardList.bo");
		forward.setRedirect(false);
		return forward;
		
	}

}
