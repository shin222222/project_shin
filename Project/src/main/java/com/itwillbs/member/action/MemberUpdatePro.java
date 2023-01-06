package com.itwillbs.member.action;

import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberUpdatePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request 한글처리
		request.setCharacterEncoding("utf-8");
		//String id = "id" 세션값 가져오기
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		String name=request.getParameter("name");

		// MemberDAO 객체 생성
		MemberDAO dao = new MemberDAO();

		// MemberDTO dto = 디비작업주소.userCheck(id,pass) 메서드 호출
		MemberDTO dto = dao.userCheck(id,pass);
		// dto null이 아니면 => "아이디 비밀번호 일치" => 수정, 메인이동
		// dto null이면 	   => "아이디 비밀번호 틀림" 뒤로가기

		if(dto!=null)  {
			// 수정할 정보를 MemberDTO에 객체생성 set 호출 저장
			MemberDTO dtoUpdate=new MemberDTO();
			dtoUpdate.setId(id);
			dtoUpdate.setPass(pass);
			dtoUpdate.setName(name);
			// 메서드 호출
			dao.updateMember(dtoUpdate);
			// main.jsp 이동
			
			ActionForward forward=new ActionForward();
			forward.setPath("./MemberMain.me");
			forward.setRedirect(true);
			return forward;
					
		} else {
			// "입력하신 정보 틀림", 뒤로이동
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.print("<script>");
			out.print("alert(\"입력하신 정보 틀림\");");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}// else
		
	}// 메서드

}// 클래스
