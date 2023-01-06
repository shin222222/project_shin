package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberDeletePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		//MemberDAO 객체 생성
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.userCheck(id,pass);

		//MemberDTO dto = 디비작업주소.userCheck(id,pass) 메서드 호출

		//5단계 : 결과를 출력, 실행 결과를 저장하는 자바 내장객체(select)
		//if 다음행 이동 => 데이터 있으면 => true => "아이디 비밀번호 일치"
		// 데이터 없으면 => false => "아이디 비밀번호 불일치"
		if(dto != null)  {
			// 리턴할형없음 void deleteMember(String id) 메서드 정의
			// deleteMember(id) 메서드 호출
			dao.deleteMember(id);	
			// 세션초기화
			HttpSession session=request.getSession();
			session.invalidate();
			// main.jsp 이동
			ActionForward forward=new ActionForward();
			forward=new ActionForward();
			forward.setPath("./MemberMain.me");
			forward.setRedirect(true);
			return forward;
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.print("<script>");
			out.print("alert(\"입력하신 정보 틀림\");");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
	}

}

