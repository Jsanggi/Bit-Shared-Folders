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
			System.out.println("메뉴 선택!");
			System.out.println("===================================");
			System.out.println("a. 사원정보 검색(이름)");
			System.out.println("b. 사원정보 검색(입사년)");
			System.out.println("c. 과거 업무이력 검색(사원ID)");
			System.out.println("q. 종료");
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
				System.out.println("프로그램 종료!!");
				scanner.close();
				System.exit(0);
			}else {
				System.out.println("잘못 선택하셨습니다. 다시 선택하세요!!");
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
