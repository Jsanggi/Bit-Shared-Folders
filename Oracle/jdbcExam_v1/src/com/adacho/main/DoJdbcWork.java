package com.adacho.main;

import java.util.Scanner;

import com.adacho.module.DbWork;
import com.adacho.module.EmpDeptByYear;
import com.adacho.module.EmpHistoryByID;
import com.adacho.module.EmployeesByName;

public class DoJdbcWork {
	private Scanner scanner;
	
	public void doWork() {
		DbWork work = null;
		String inputString;
		boolean isSelected = false;
		scanner = new Scanner(System.in);
		
		while (true) {
			System.out.println("�޴� ����!");
			System.out.println("===================================");
			System.out.println("a. ������� �˻�(�̸�)");
			System.out.println("b. ������� �˻�(�Ի��)");
			System.out.println("c. ���� �����̷� �˻�(���ID)");
			System.out.println("q. ����");
			System.out.print("> ");
			
			inputString = scanner.nextLine();
			
			if(inputString.equals("a")) {
				work = new EmployeesByName(scanner);
				isSelected = true;
			}else if(inputString.equals("b")) {
				work = new EmpDeptByYear(scanner);
				isSelected = true;
			}else if(inputString.equals("c")) {
				work = new EmpHistoryByID(scanner);
				isSelected = true;
			}else if(inputString.equals("q")) {
				System.out.println();
				System.out.println("���α׷� ����!!");
				scanner.close();
				System.exit(0);
			}else {
				System.out.println("�߸� �����ϼ̽��ϴ�. �ٽ� �����ϼ���!!");
				isSelected = false;
			}
			
			if(isSelected) {
				work.getInfo();
				isSelected = false;
			}
				
			System.out.println();
		}
	}
}
