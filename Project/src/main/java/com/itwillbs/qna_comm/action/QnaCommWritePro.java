package com.itwillbs.qna_comm.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.qna_comm.db.QnaCommDAO;
import com.itwillbs.qna_comm.db.QnaCommDTO;

public class QnaCommWritePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// board/writePro.jsp
		// 글쓰기 폼에서 입력한 내용을 서버에 전달하면 
		// 내장객체 request에 저장
		// request 한글처리 
		request.setCharacterEncoding("utf-8");
		// request name, subject, content 파라미터 가져와서 변수에 저장
//		String id=(String)session.getAttribute("id");
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		String comm_content = request.getParameter("comm_content");
		String user_id = request.getParameter("user_id");
		System.out.println(comm_content);
		System.out.println(qna_num);

		QnaCommDTO dto = new QnaCommDTO();

		dto.setComm_content(comm_content);
		dto.setQna_num(qna_num);
		dto.setUser_id(user_id);
		
		QnaCommDAO dao = new QnaCommDAO();

		dao.insertQnaComm(dto);

		ActionForward forward=new ActionForward();
		forward.setPath("/QnaContent.qn?qna_num="+String.valueOf(qna_num));
		forward.setRedirect(false);
		return forward;
	}

}
