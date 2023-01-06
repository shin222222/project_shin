package com.itwillbs.member.action;

import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogout implements Action{
	

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 처리 - 세션값 초기화
		HttpSession session=request.getSession();
		session.invalidate();
		
		// ./MemberMain.me 이동
		ActionForward forward=new ActionForward();
		forward.setPath("./MemberMain.me");
		forward.setRedirect(true); // 바뀌면서 이동
		// 이동
		return forward;
	}
	
}



