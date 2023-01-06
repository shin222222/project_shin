package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberInfo implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 메서드 오버라이딩 - id에 해당하는 디비에 MemberDTO정보가져오기
		//String id = "id" 세션값 가져오기
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		//MemberDAO 객체생성
		MemberDAO dao=new MemberDAO();
		// 리턴할형MemberDTO getMember(String id)
		// 메서드 정의
		// MemberDTO dto =디비작업주소.getMember(id) 메서드 호출
		MemberDTO dto=dao.getMember(id);
		// MemberDTO정보를 request담아서 오기
		request.setAttribute("dto", dto);
		// 이동정보를 담아서 이동  ./member/info.jsp  false
		ActionForward forward=new ActionForward();
		forward.setPath("./member/info.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
