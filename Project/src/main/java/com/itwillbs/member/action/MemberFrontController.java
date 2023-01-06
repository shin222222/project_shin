package com.itwillbs.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 처리담당자(서블릿을 상속)
public class MemberFrontController extends HttpServlet{

	//서블릿 파일이 동작할때 => 자동으로 메서드 호출 service()
		//                  doGet() doPost() 
		// 웹서버에서 서블릿이 동작할대 자동으로 메서드 호출되면
		// 메서드 오버라이딩해서 재정의
		protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("MemberFrontController doProcess()");
			// 주소매핑:(가상주소)-->실제페이지 연결
			
			// 가상주소 뽑아오기
	// URL => http://localhost:8080/Model2/insertForm.me
		String requestURL=request.getRequestURL().toString();
		System.out.println("requestURL : "+requestURL);
	// URI => 		               /Model2/insertForm.me
		String requestURI=request.getRequestURI();
		System.out.println("requestURI : "+requestURI);
	// 프로젝트 명(Context명) => 	   /Model2
		String contextPath=request.getContextPath();
		System.out.println("contextPath : " + contextPath);
		System.out.println("contextPath길이 : " + contextPath.length());
	// 뽑은 가상주소          =>            /insertForm.me
	// 시작위치(contextPath길이) 부터 끝까지 문자열을 잘라서 가져오기
		String strpath=requestURI.substring(contextPath.length());
		System.out.println("뽑은 주소 strpath : "+strpath);
		
		// 이동경로, 이동방식을 저장하는 ActionForward 파일 선언
		ActionForward forward=null;
		// 부모 인터페이스 틀 선언
		Action action=null;
		
			
			// 뽑은 가상주소 비교 -> 실제페이지 연결
		if(strpath.equals("/MemberInsertForm.me")) {
	//insertForm.me(가상주소)-->실제페이지 연결 insertForm.jsp(화면)
			//이동 //가상주소의 현재위치/member/insertForm.jsp
			
	// http://localhost:8080/Model2/member/insertForm.jsp
			// 주소가 변경되면서 이동
//			response.sendRedirect("./member/insertForm.jsp");
			
	// insertForm.me(가상주소)유지가 되고 실제 주소가 안보이면서 이동
//			http://localhost:8080/Model2/insertForm.me
//			RequestDispatcher dis
//			=request.getRequestDispatcher("./member/insertForm.jsp");
//			dis.forward(request, response);
			
			forward=new ActionForward();
			forward.setPath("./member/insertForm.jsp");
			forward.setRedirect(false);
			
		}else if(strpath.equals("/MemberInsertPro.me")) {
//			=> insertPro.me(가상주소)
			//-->실제페이지 연결 insertPro.java(처리)<=>DAO(디비)
			// 패키지 com.itwillbs.member.action
			// 파일   MemberInsertPro.java
			
			// 자바파일 객체생성
			// 부모 인터페이스 = new 자식클래스()
			action=new MemberInsertPro();
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 이동 "./loginForm.me"
			// 주소가 변경되면서 이동
//			response.sendRedirect(path);
//			forward=new ActionForward();
//			forward.setPath(path);
//			forward.setRedirect(true);
			
			
		} else if(strpath.equals("/MemberLoginForm.me")) {	// 비교할때는 ./안함 /만
			// => loginForm.me(가상주소)
			// --> 실제페이지 연결 loginForm.jsp(화면)
			//    ./member/loginForm.jsp
			// 주소가 안바뀌면서 이동
			
//			RequestDispatcher dis
//			=request.getRequestDispatcher("./member/loginForm.jsp");
//			dis.forward(request, response);
			
			forward=new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);
			
		
		} else if(strpath.equals("/MemberLoginPro.me")) {
			// loginPro.me(가상주소)
			// --> 실제페이지 연결 loginPro.java(처리) <=> DAO(디비)
			// 패키지 com.itwillbs.member.action
			// 파일 MemberLoginPro.java
			// 자바파일 객체 생성
			// 부모인터페이스 = new 자식클래스
			action=new MemberLoginPro();
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 이동 "./Main.me"
			// 주소가 변경되면서 이동
//			if(path!=null) {
////				response.sendRedirect(path);
//			}
//				forward=new ActionForward();
//				forward.setPath(path);
//				forward.setRedirect(true);
		} else if(strpath.equals("/MemberMain.me")) {
//			RequestDispatcher dis
//			=request.getRequestDispatcher("./member/main.jsp");
//			dis.forward(request, response);
			forward=new ActionForward();
			forward.setPath("./member/main.jsp");
			forward.setRedirect(false);
		} else if (strpath.equals("/MemberLogout.me")) {
			// MemberLogout 자바파일 만들기 상속 인터페이스 Action
			// 메서드 오버라이딩 - 세션값 초기화, 이동정보저장 리턴
			// 인터페이스 = 자식클래스 객체생성
			action=new MemberLogout();
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(strpath.equals("/MemberInfo.me")) {
			// MemberInfo 자바파일 만들기 상속 인터페이스 Action
			// 메서드 오버라이딩 - id에 해당하는 디비에 MemberDTO 정보가져오기
			// MemberDTO정보를 request 담아서 오기
			// 이동정보를 담아서 이동 ./member/info.jsp false
			
			// 인터페이스 = 자식클래스 객체생성
			action=new MemberInfo();
			// 메서드 호출
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(strpath.equals("/MemberUpdateForm.me")) {
				action=new MemberUpdate();
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (strpath.equals("/MemberUpdatePro.me")) {
			action=new MemberUpdatePro();
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(strpath.equals("/MemberDeleteForm.me")) {
			// ./member/deleteForm.jsp 이동
			forward=new ActionForward();
			forward.setPath("./member/deleteForm.jsp");
			forward.setRedirect(false);
		} else if (strpath.equals("/MemberDeletePro.me")) {
			action=new MemberDeletePro();
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (strpath.equals("/MemberList.me")) {
			action=new MemberList();
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 이동 (경로정보, 이동방식 담아서 오면 이동
		if(forward !=null) {
			if(forward.isRedirect()) {
				// true - sendRedirect()
				System.out.println("true:" + forward.getPath() + "sendRedirect() 이동");
				response.sendRedirect(forward.getPath());
			}else {
				// false - foward()
				System.out.println("false:" + forward.getPath() + "foward() 이동");
				RequestDispatcher dis
				=request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		} 
		
		} // doProcess()메서드
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("MemberFrontController doGet()");
//			doProcess()호출
			doProcess(request, response);
		}//

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("MemberFrontController doPost()");
//			doProcess()호출
			doProcess(request, response);
		}//
		
	}//