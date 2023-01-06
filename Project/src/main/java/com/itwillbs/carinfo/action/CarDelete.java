package com.itwillbs.carinfo.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.carinfo.db.CarInfoDAO;

public class CarDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String car_num=request.getParameter("car_num");
		CarInfoDAO dao = new CarInfoDAO();
		String filepath = request.getSession().getServletContext().getRealPath("car_images");
		// 차량정보 삭제 시 이미지 파일도 같이 삭제
		File deleteImageName = new File (filepath + "\\" + request.getParameter("car_image"));
		deleteImageName.delete();
		
		dao.deleteCar(car_num);
		
		ActionForward forward=new ActionForward();
		forward.setPath("CarList.ci");
		forward.setRedirect(false);
		return forward;
	}

} 
