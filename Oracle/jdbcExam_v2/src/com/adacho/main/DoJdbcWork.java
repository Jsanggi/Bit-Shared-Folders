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
			System.out.println("�޴� ����!");
			System.out.println("===================================");
			System.out.println("a. ������� �˻�(�̸�)");
			System.out.println("b. ������� �˻�(�Ի��)");
			System.out.println("c. ���� �����̷� �˻�(���ID)");
			System.out.println("q. ����");
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
