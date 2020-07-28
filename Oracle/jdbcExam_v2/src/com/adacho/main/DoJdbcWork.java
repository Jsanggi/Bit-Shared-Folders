package com.adacho.main;

import java.util.Scanner;

import com.adacho.module.DbWork;
import com.adacho.module.JobFactory;

public class DoJdbcWork {
	private Scanner scanner;
	private JobFactory factory;
	
	public DoJdbcWork(JobFactory factory) {
		this.factory = factory;
	}
	
	public void doWork() {
		DbWork work = null;
		String inputString;
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
			
			work = factory.createJob(inputString, scanner);
			
			if(work != null) {
				work.getInfo();
				work = null;
			}
				
			System.out.println();
		}
	}
}
