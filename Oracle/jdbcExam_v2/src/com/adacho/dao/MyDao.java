package com.adacho.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.adacho.model.Employee;
import com.adacho.model.GeneralDto;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class MyDao {
	private static MyDao dao = new MyDao();
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement pStmt;
	private CallableStatement cStmt;
	private OracleCallableStatement ocStmt;
	
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String username = "c##hr";
	private final String password = "1234";
	
	private MyDao() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static MyDao getInstance() {
		return dao;
	}
	
	private void getConnection() {
		try {
			conn = DriverManager.getConnection(url,username, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void closeConnecton() {
		try {
			if(rSet != null)
				rSet.close();
			if(pStmt != null)
				pStmt.close();
			if(cStmt != null)
				cStmt.close();
			if(ocStmt != null)
				ocStmt.close();
			if(conn != null)
				conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Employee> getEmployees(String name){
		List<Employee> employees = new ArrayList<Employee>();
		
		getConnection();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE ");
		sb.append("FROM EMPLOYEES ");
		sb.append("WHERE FIRST_NAME LIKE  ? OR LAST_NAME = ?");
		
		String sql = sb.toString();
		
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%" + name + "%");
			pStmt.setString(2, "%" + name + "%");
			
			rSet = pStmt.executeQuery();
			Employee employee;
			
			while(rSet.next()) {
				employee = new Employee();
				employee.setFirstName(rSet.getString(1));
				employee.setLastName(rSet.getString(2));
				employee.setEmail(rSet.getString(3));
				employee.setPhoneNumber(rSet.getString(4));
				employee.setHireDate(rSet.getDate(5).toString());
				employees.add(employee);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnecton();
		}
		
		return employees;
	}
	
	public List<GeneralDto> getEmpDept(long year) {
		List<GeneralDto> list = new ArrayList<GeneralDto>(); 
		
		getConnection();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT E.EMPLOYEE_ID , E.FIRST_NAME, E.LAST_NAME, NVL(D.DEPARTMENT_NAME, '<Not Assigned>') AS DEPT_NAME ");
		sb.append("FROM EMPLOYEES E LEFT OUTER JOIN DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID ");
		sb.append("WHERE TO_CHAR(E.HIRE_DATE, 'YYYY') = ? ORDER BY E.EMPLOYEE_ID");
		
		String sql = sb.toString();
		
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setLong(1, year);
			
			rSet = pStmt.executeQuery();
			GeneralDto result;
			
			while(rSet.next()) {
				result = new GeneralDto();
				result.setLongData1(rSet.getLong(1));
				result.setStringData1(rSet.getString(2));
				result.setStringData2(rSet.getString(3));
				result.setStringData3(rSet.getString(4));
				list.add(result);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnecton();
		}
		
		
		return list;
	}
	
	public List<GeneralDto> getEmpHistory(long empNo) {
		List<GeneralDto> list = new ArrayList<GeneralDto>(); 
		
		getConnection();
		
		try {
			cStmt = conn.prepareCall("BEGIN CURSOR_PKG.SP_JOB_HISTORY(?, ?); END;");
			cStmt.setLong(1, empNo);
			cStmt.registerOutParameter(2, OracleTypes.CURSOR);
			
			cStmt.execute();
			
			ocStmt = (OracleCallableStatement)cStmt;
			
			rSet = ocStmt.getCursor(2);
			
			GeneralDto result;
			while(rSet.next()) {
				result = new GeneralDto();
				result.setStringData1(rSet.getString(1));
				result.setStringData2(rSet.getString(2));
				result.setStringData3(rSet.getString(3).toString());
				result.setStringData4(rSet.getString(4).toString());
				list.add(result);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnecton();
		}
		
		return list;
	}
	
}
