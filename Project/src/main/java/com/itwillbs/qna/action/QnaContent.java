package com.itwillbs.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.qna.db.QnaDAO;
import com.itwillbs.qna.db.QnaDTO;

public class QnaContent implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		System.out.println("qna_num = " + qna_num);
		QnaDAO dao=new QnaDAO();
		dao.updateQnaReadcount(qna_num);
		QnaDTO dto=dao.getQna(qna_num);
		request.setAttribute("dto", dto);
		ActionForward forward=new ActionForward();
		forward.setPath("./qna/content.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}