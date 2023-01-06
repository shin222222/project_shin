package com.itwillbs.qna.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.qna.db.QnaDAO;
import com.itwillbs.qna.db.QnaDTO;

public class QnaWritePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		String qna_sub = request.getParameter("qna_sub");
		String qna_content = request.getParameter("qna_content");
		String qna_secret = request.getParameter("qna_secret");

		QnaDTO dto = new QnaDTO();
		dto.setUser_id(user_id);
		dto.setQna_sub(qna_sub);
		dto.setQna_content(qna_content);
		dto.setQna_readcount(0);
		if(qna_secret==null) {
			dto.setQna_secret("N");
		} else{
			dto.setQna_secret(qna_secret);
		}

		QnaDAO dao = new QnaDAO();

		dao.insertQna(dto);

		ActionForward forward=new ActionForward();
		forward.setPath("./QnaList.qn");
		forward.setRedirect(true);
		return forward;
	}
	
}
