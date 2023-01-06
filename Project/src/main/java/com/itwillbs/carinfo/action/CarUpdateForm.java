package com.itwillbs.carinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.carinfo.db.CarInfoDAO;
import com.itwillbs.carinfo.db.CarInfoDTO;


public class CarUpdateForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String car_num=request.getParameter("car_num");
		CarInfoDAO dao=new CarInfoDAO();
		CarInfoDTO dto=dao.getCar(car_num);
		
		request.setAttribute("dto", dto);
		ActionForward forward=new ActionForward();
		forward.setPath("./carinfo/updateForm.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
