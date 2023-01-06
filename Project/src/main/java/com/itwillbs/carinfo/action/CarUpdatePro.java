package com.itwillbs.carinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.carinfo.db.CarInfoDAO;
import com.itwillbs.carinfo.db.CarInfoDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CarUpdatePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String filepath = request.getSession().getServletContext().getRealPath("car_images");
		// 차 정보 수정 시 고정값이 필요해서 차량번호는 수정 못함 
		MultipartRequest multi = new MultipartRequest
				(request, filepath, 
						1024*1024*1024, "utf-8", new DefaultFileRenamePolicy());
		
		String car_num=multi.getParameter("car_num");
		String car_place=multi.getParameter("car_place");
		int per_hour=Integer.parseInt(multi.getParameter("per_hour"));
		String car_type=multi.getParameter("car_type");
		int car_year=Integer.parseInt(multi.getParameter("car_year"));
		String car_model=multi.getParameter("car_model");
		String car_brand=multi.getParameter("car_brand");
		String car_image=multi.getFilesystemName("car_image");
		String car_fuel=multi.getParameter("car_fuel");
		boolean imageChange = false;
		
		if(car_image != null) {
			imageChange = true;
		}
		
		CarInfoDAO dao = new CarInfoDAO();
		CarInfoDTO dto=new CarInfoDTO();
		
		dto.setCar_num(car_num);
		dto.setCar_place(car_place);
		dto.setPer_hour(per_hour);
		dto.setCar_type(car_type);
		dto.setCar_year(car_year);
		dto.setCar_model(car_model);
		dto.setCar_brand(car_brand);
		dto.setCar_image(car_image);
		dto.setCar_fuel(car_fuel);
		
		dao.updateCar(dto, imageChange, filepath);

		ActionForward forward=new ActionForward();
		forward.setPath("/CarList.ci");
		forward.setRedirect(false);
		return forward;
	}

}
