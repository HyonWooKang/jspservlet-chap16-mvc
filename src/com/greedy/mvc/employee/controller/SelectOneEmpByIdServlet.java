package com.greedy.mvc.employee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.mvc.employee.model.dto.EmployeeDTO;
import com.greedy.mvc.employee.model.service.EmployeeService;

/**
 * Servlet implementation class SelectOneEmpByIdServlet
 */
@WebServlet("/employee/select")
public class SelectOneEmpByIdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* 전달한 파라미터를 꺼내기 */
		String empId = request.getParameter("empId");
		// 넘겨 받은 값이 제대로 왔는지 확인하고 넘어가기
		System.out.println("empId : " + empId);
	
		// 서비스로 보내기 (단계별로 글로 써보고 흐름을 연습하세요)
		/* 사번을 이용해서 사원 정보를 조회하는 비즈니스 로직 호출 */
		EmployeeService empService = new EmployeeService();
		
		/* 결과 값을 반환 받자 */
		/* 반환 값은 뭘로 받지? 객체, 리스트, 정수? 내가 뭘 조회하고 있는지, 무슨 작업을 하는지 확인 */
		EmployeeDTO selectedEmp = empService.selectOneEmpById(empId); 
		// selectedEmp에 가져온 정보가 들어있다.
		
		/* 비즈니스 로직 실행 결과에 따른 뷰 연결 */
		String path = "";
		if(selectedEmp != null) {
			// 성공 시 성공 페이지로 연결
			path = "/WEB-INF/views/employee/showEmpInfo.jsp";
			request.setAttribute("selectedEmp", selectedEmp);
		
		} else {
			// 실패 시 에러 페이지로 연결
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("message", "회원 정보를 조회할 수 없습니다.");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

}
