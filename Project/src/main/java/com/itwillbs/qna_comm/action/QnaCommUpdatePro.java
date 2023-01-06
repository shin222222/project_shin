package com.itwillbs.qna_comm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.qna_comm.db.QnaCommDAO;
import com.itwillbs.qna_comm.db.QnaCommDTO;

public class QnaCommUpdatePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		int comm_num=Integer.parseInt(request.getParameter("comm_num"));
		String comm_content = request.getParameter("comm_content");
		System.out.println("qna_num = " +qna_num);
		System.out.println("comm_num = " +comm_num);
		System.out.println("comm_content = " + comm_content);
		
		
		QnaCommDAO dao = new QnaCommDAO();

		QnaCommDTO dto=new QnaCommDTO();
//		dto.setQna_id(qna_id);
		dto.setQna_num(qna_num);
		dto.setComm_num(comm_num);
		dto.setComm_content(comm_content);
		// 메서드 호출
		dao.updateComm(dto);
		// main.jsp 이동

		ActionForward forward=new ActionForward();
		forward.setPath("/QnaContent.qn?qna_num="+String.valueOf(qna_num));
		forward.setRedirect(false);
		return forward;
	}

}
