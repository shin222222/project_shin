package com.itwillbs.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.qna.db.QnaDAO;
import com.itwillbs.qna.db.QnaDTO;

public class QnaUpdatePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		String qna_sub = request.getParameter("qna_sub");
		String qna_content = request.getParameter("qna_content");
		
		QnaDAO dao = new QnaDAO();
		QnaDTO dto=new QnaDTO();
		
		dto.setQna_num(qna_num);
		dto.setQna_sub(qna_sub);
		dto.setQna_content(qna_content);
		
		dao.updateQna(dto);

		ActionForward forward=new ActionForward();
		forward.setPath("/QnaContent.qn?page_num="+String.valueOf(qna_num));
		forward.setRedirect(false);
		return forward;
	
	}

}
