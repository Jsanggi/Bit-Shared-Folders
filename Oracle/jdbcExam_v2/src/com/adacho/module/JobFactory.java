package com.adacho.module;

import java.util.Scanner;

public class JobFactory {
	public DbWork createJob(String job, Scanner scanner) {
		DbWork work = null;
		
		if(job.equals("a")) {
			work = new EmployeesByName(scanner);
		}else if(job.equals("b")) {
			work = new EmpDeptByYear(scanner);
		}else if(job.equals("c")) {
			work = new EmpHistoryByID(scanner);
		}else if(job.equals("q")) {
			System.out.println();
			System.out.println("���α׷� ����!!");
			scanner.close();
			System.exit(0);
		}else {
			System.out.println("�߸� �����ϼ̽��ϴ�. �ٽ� �����ϼ���!!");
		}
		return work;
	}
}
