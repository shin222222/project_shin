package com.itwillbs.carinfo.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarInfoFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strpath=request.getServletPath();
		System.out.println("뽑은 주소 strpath : "+strpath);
		
		ActionForward forward=null;
		Action action=null;
		if(strpath.equals("/CarInsertForm.ci")) {
			forward=new ActionForward();
			forward.setPath("./carinfo/insertForm.jsp");
			forward.setRedirect(false);
		} else if(strpath.equals("/CarInsertPro.ci")) {
			action=new CarInsertPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(strpath.equals("/CarList.ci")) {
			action=new CarList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(strpath.equals("/CarDelete.ci")) {
			action=new CarDelete();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(strpath.equals("/CarUpdateForm.ci")){
			action=new CarUpdateForm();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(strpath.equals("/CarUpdatePro.ci")){
			action=new CarUpdatePro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dis
				= request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	

}
