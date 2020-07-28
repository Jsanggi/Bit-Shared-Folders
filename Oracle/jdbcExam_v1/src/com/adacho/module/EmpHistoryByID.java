package com.adacho.module;

import java.util.List;
import java.util.Scanner;

import com.adacho.dao.MyDao;
import com.adacho.model.GeneralDto;

public class EmpHistoryByID implements DbWork{
	private Scanner scanner;

	public EmpHistoryByID(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public void getInfo() {
		// TODO Auto-generated method stub
		long inputNum;
		List<GeneralDto> list;
		
		System.out.print("사원번호를 입력하세요: ");
		inputNum = scanner.nextLong();
		
		MyDao dao = MyDao.getInstance();
		list = dao.getEmpHistory(inputNum);
		
		System.out.println("	이름        		담당업무         	시작일                	종료일");
		System.out.println("=================================================================================================");
		
		if(list.size() == 0) {
			System.out.println("조회 결과가 없습니다!");
		}else {

			for(GeneralDto item : list) {
				System.out.printf("%20s    %20s     %15s       %15s\n", 
						item.getStringData1(), item.getStringData2(), item.getStringData3(), item.getStringData4());
			}			
		}
		scanner.nextLine();
	}
	
	
}
