package com.itwillbs.qna_comm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.qna_comm.db.QnaCommDAO;

public class QnaCommDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		int comm_num=Integer.parseInt(request.getParameter("comm_num"));
		QnaCommDAO dao = new QnaCommDAO();
		
		System.out.println("qna_num = " + qna_num);
		System.out.println("comm_num = " + comm_num);
		dao.deleteComm(qna_num, comm_num);
		
		ActionForward forward=new ActionForward();
		forward.setPath("/QnaContent.qn?qna_num="+String.valueOf(qna_num));
		forward.setRedirect(false);
		return forward;
	}

}
