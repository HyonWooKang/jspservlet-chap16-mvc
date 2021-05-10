package com.greedy.mvc.employee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.mvc.employee.model.dto.EmployeeDTO;
import com.greedy.mvc.employee.model.service.EmployeeService;

/**
 * Servlet implementation class SelectedAllEmpServlet
 */
@WebServlet("/employee/list")
public class SelectedAllEmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 전달된 값 꺼내기 */

		/* 서비스로 보내기 */
		EmployeeService empService = new EmployeeService();
		
		/* 값을 반환 받자 (시작부터 리턴 타입 생각하여 작성) */
		List<EmployeeDTO> employeeList = empService.selectAllEmp();
		
		//System.out.println(employeeDTO.get(1));
		
		// 리턴 후 바로 이 부분 작성
		/* 뷰 연결 */
		String path ="";
		if(employeeList != null) {
			path = "/WEB-INF/views/employee/employeeList.jsp";
			request.setAttribute("employeeList", employeeList);
		} else {
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("message", "회원정보를 조외할 수 없습니다.");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
