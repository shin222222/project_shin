package com.itwillbs.carinfo.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.carinfo.db.CarInfoDAO;
import com.itwillbs.carinfo.db.CarInfoDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CarInsertPro implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		// 이미지 파일 저장
		// 이미지 업로드속도 때문에 car_images 파일이 아니라 톰캣배포경로에 이미지파일 업로드
		// car_images에 저장하면 1~2초 뒤에 업로드됨
		// 그래서 차량 이미지 업로드해도 이클립스 car_images 폴더는 비어있음
		String filepath = request.getSession().getServletContext().getRealPath("car_images");
		
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
	
//		System.out.println("car_num = " + car_num);
//		System.out.println("car_place = " + car_place);
//		System.out.println("per_hour = " + per_hour);
//		System.out.println("car_type = " + car_type);
//		System.out.println("car_year = " + car_year);
//		System.out.println("car_model = " + car_model);
//		System.out.println("car_brand = " + car_brand);
//		System.out.println("car_image = " + car_image);
//		System.out.println("car_fuel = " + car_fuel);
		
		CarInfoDAO dao = new CarInfoDAO();
		CarInfoDTO dto = new CarInfoDTO();
		
		if(dao.carNumCheck(car_num)>0) {	// 차 번호 중복 확인
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.print("<script>");
			out.print("alert(\"이미 등록된 차 번호입니다.\");");
			out.print("history.back();");
			out.print("</script>");
			out.close();
		}
		
		dto.setCar_num(car_num);
		dto.setCar_place(car_place);
		dto.setPer_hour(per_hour);
		dto.setCar_type(car_type);
		dto.setCar_year(car_year);
		dto.setCar_model(car_model);
		dto.setCar_brand(car_brand);
		dto.setCar_image(car_image);
		dto.setCar_fuel(car_fuel);
		
		dao.insertCar(dto);

		ActionForward forward = new ActionForward();
		forward.setPath("./CarList.ci");
		forward.setRedirect(true);
		return forward;
	}
}
