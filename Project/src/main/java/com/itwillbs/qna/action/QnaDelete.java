package com.itwillbs.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.qna.db.QnaDAO;

public class QnaDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		QnaDAO dao = new QnaDAO();

		dao.deleteQna(qna_num);
		
		ActionForward forward=new ActionForward();
		forward.setPath("QnaList.qn");
		forward.setRedirect(false);
		return forward;
	}
	

}
