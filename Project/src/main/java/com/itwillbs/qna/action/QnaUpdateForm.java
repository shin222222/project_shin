package com.itwillbs.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.qna.db.QnaDAO;
import com.itwillbs.qna.db.QnaDTO;

public class QnaUpdateForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		QnaDAO dao=new QnaDAO();
		QnaDTO dto=dao.getQna(qna_num);
		
		request.setAttribute("dto", dto);
		ActionForward forward=new ActionForward();
		forward.setPath("./qna/updateForm.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
