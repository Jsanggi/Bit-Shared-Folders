package com.adacho.module;

import java.util.List;
import java.util.Scanner;

import com.adacho.dao.MyDao;
import com.adacho.model.Employee;

public class EmployeesByName implements DbWork{
	private Scanner scanner;
	
	public EmployeesByName(Scanner scanner ) {
		this.scanner = scanner;
	}
	
	@Override
	public void getInfo() {
		// TODO Auto-generated method stub
		String name;
		List<Employee> employees;
		
		System.out.print("����� First name �Ǵ� Last name�� �Է��ϼ���: ");
		name = scanner.nextLine();
		
		MyDao dao = MyDao.getInstance();
		
		employees = dao.getEmployees(name);
		
		System.out.println("   �̸�           ��               	�̸���             ��ȭ  	     	     	�Ի���");
		System.out.println("=================================================================================================");
		if(employees.size() == 0) {
			System.out.println("��ȸ ����� �����ϴ�!");
		}else {

			for(Employee emp : employees) {
				System.out.printf("%10s    %10s     %20s       %15s        %15s\n", 
						emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getPhoneNumber(), emp.getHireDate());
			}			
		}
	}
	
}
