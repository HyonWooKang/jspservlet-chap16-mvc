package com.greedy.mvc.employee.model.dao;

import static com.greedy.mvc.common.jdbc.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.greedy.mvc.common.config.ConfigLocation;
import com.greedy.mvc.employee.model.dto.EmployeeDTO;


public class EmployeeDAO {

	/* Properties 객체를 이용해서 쿼리문을 조회해서 사용한다.
	 * 기본 생성자를 통해서 쿼리문을 조회해온다.
	 */
	private Properties prop = new Properties();
	
	public EmployeeDAO() {
		
		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION+"employee-mapper.xml"));
		} catch(IOException	e) {
			e.printStackTrace();
		}
	
	}
	
	public EmployeeDTO selectEmpById(Connection con, String empId) {
		
		/* Statement를 사용할까? 아니면 PreparedStatement를 사용할까?
		 * ==> Where절에 값이 들어가면 PreparedStatement를 사용한다. (보안up, 속도lower)
		 * */
		PreparedStatement pstmt = null;
		
		/* 결과 값을 뭘로 받을까?
		 * select를 요청했을 때 => ResultSet
		 * insert, update, delete를 요청했을 때 => int
		 */
		ResultSet rset = null;
		
		/* 반환시킬 변수를 지정하자 => 위의 결과를 잘 생각해보면 나오지 않을까 */
		/* 단일 : EmployeeDTO, 다수 : List<EmployeeDTO> */
		EmployeeDTO selectedEmp = null;
		
		String query = prop.getProperty("selectEmpById");
		
		// mapper가서 코드 작성 후 출력 테스트
		System.out.println(query);
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, empId); // <= 위치홀더의 인덱스는 1부터 시작한다.
			
			rset = pstmt.executeQuery();
			// 위에서 만든 쿼리문을 이용해서 데이터베이스에 질의하여 데이터 정보를 리턴 받음 (ctrl + enteR)
			
			// 단일 값 확인은 if, 여러 개의 값일 경우는 while문
			// 데이터 있는지는 next()로 확인
			if(rset.next()) {
				selectedEmp = new EmployeeDTO();
				// setEmpId => DTO 필드의 setter, rset.getString("데이터베이스입력명") 별칭이 있으면 별칭으로 불어야함
				selectedEmp.setEmpId(rset.getString("EMP_ID"));
				selectedEmp.setEmpName(rset.getString("EMP_NAME"));
				selectedEmp.setDeptCode(rset.getString("DEPT_CODE"));
				selectedEmp.setJobCode(rset.getString("JOB_CODE"));
				selectedEmp.setSalary(rset.getInt("SALARY"));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return selectedEmp;
	}

	public List<EmployeeDTO> selectAllEmp(Connection con) {
		
		// Statement, RSet 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 최종적으로 값을 답아서 리턴할 List
		List<EmployeeDTO> selectAllEmp = null;
		
		// 쿼리문 연결
		String query = prop.getProperty("selectAllEmp");
		
		// mapper 쿼리문 출력 테스트
		System.out.println(query);
		
		// 연결해서 쿼리문 실행
		try {
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			selectAllEmp =  new ArrayList<>(); // 
			
			while(rset.next()) {
				EmployeeDTO empAll = new EmployeeDTO();
				
				empAll.setEmpId(rset.getString("EMP_ID"));
				empAll.setEmpName(rset.getString("EMP_NAME"));
				empAll.setEmpNo(rset.getString("EMP_NO"));
				empAll.setEmail(rset.getString("EMAIL"));
				empAll.setPhone(rset.getString("PHONE"));
				empAll.setDeptCode(rset.getString("DEPT_CODE"));
				empAll.setJobCode(rset.getString("JOB_CODE"));
				empAll.setSalLevel(rset.getString("SAL_LEVEL"));
				empAll.setSalary(rset.getInt("SALARY"));
				empAll.setBonus(rset.getDouble("BONUS"));
				empAll.setManagerId(rset.getString("MANAGER_ID"));
				empAll.setHireDate(rset.getDate("HIRE_DATE"));
				empAll.setEntDate(rset.getDate("ENT_DATE"));
				empAll.setEntYn(rset.getString("ENT_YN"));
				
				selectAllEmp.add(empAll);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectAllEmp;
	}
	
	
	
}	
	
	
	
