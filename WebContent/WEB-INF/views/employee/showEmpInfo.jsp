<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	사원번호 : ${ requestScope.selectedEmp.empId }<br>
	사원명 : ${ requestScope.selectedEmp.empName }<br>
	부서코드 : ${ requestScope.selectedEmp.deptCode }<br>
	직급코드 : ${ requestScope.selectedEmp.jobCode }<br>
	급여 : ${ requestScope.selectedEmp.salary }<br>

	
</body>
</html>