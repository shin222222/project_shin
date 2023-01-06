package com.itwillbs.member.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberList implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//디비작업 기능 => MemberDAO
		// 사용 => MemberDAO 기억장소 할당(객체생성)
		MemberDAO dao=new MemberDAO();
		// dao주소를 통해서 메서드 호출
		// 여러명을 저장하는 List배열변수=dao.getMemberList();
		// List memberList =dao.getMemberList();
		List<MemberDTO> memberList =dao.getMemberList();
		// request memberList 저장
		request.setAttribute("memberList", memberList);
		// 이동
		ActionForward forward=new ActionForward();
		forward.setPath("./member/list.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
