package com.greedy.mvc.employee.model.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.greedy.mvc.common.config.ConfigLocation;
import com.greedy.mvc.employee.model.dao.EmployeeDAO;
import com.greedy.mvc.employee.model.dto.EmployeeDTO;
/* getConnection을 static으로 설정하여 바로 사용하기 */
import static com.greedy.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.greedy.mvc.common.jdbc.JDBCTemplate.close;

public class EmployeeService {

	/* Connection 객체 생성, 결과에 다른 트랜잭션 처리, [Connection 객체 소멸] */

	
	
	/* EmployeeDAO와 연결할 필드 변수 */
	private EmployeeDAO empDAO = new EmployeeDAO();
	
	
	
	// alt shift j
	/**
	 * 사원번호를 이용해서 사용자 정보 조회
	 * @param empId 사원번호
	 * @return 사원정보
	 */
	public EmployeeDTO selectOneEmpById(String empId) {

		/* Connection 생성 */
		Connection con = getConnection();

		
		/* Connection과 함께 정보를 전달하여 조회를 한다. */
		EmployeeDTO selectEmp = empDAO.selectEmpById(con, empId);
		
		/* 생각: 트랜잭션 처리가 필요한 상황인가? ==> select이니 필요 없다 */
		
		
		/* Connection 객체를 닫아주고 값 리턴해준다. */
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectEmp;
	}


	/* 모든 직원 정보 조회 메소드 */
	public List<EmployeeDTO> selectAllEmp() {
		
		Connection con = getConnection();
		
		// DAO DB로 가서 결과 값 가져오는 역할
		List<EmployeeDTO> empAll = empDAO.selectAllEmp(con);
		
		close(con);
		
		return empAll;
	}

	
	
}
