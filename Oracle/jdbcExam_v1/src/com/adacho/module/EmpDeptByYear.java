package com.adacho.module;

import java.util.List;
import java.util.Scanner;

import com.adacho.dao.MyDao;
import com.adacho.model.GeneralDto;

public class EmpDeptByYear implements DbWork{
	private Scanner scanner;
	
	public EmpDeptByYear(Scanner scanner) {
		this.scanner = scanner;
	}


	@Override
	public void getInfo() {
		// TODO Auto-generated method stub
		long inputNum;
		List<GeneralDto> list;
		
		System.out.print("입사년도를 입력하세요: ");
		inputNum = scanner.nextLong();
		
		MyDao dao = MyDao.getInstance();
		list = dao.getEmpDept(inputNum);
		
		System.out.println("사원번호        이름         성                부서");
		System.out.println("===========================================================");
		
		if(list.size() == 0) {
			System.out.println("조회 결과가 없습니다!");
		}else {

			for(GeneralDto item : list) {
				System.out.printf("%5d    %10s     %10s       %15s\n", 
						item.getLongData1(), item.getStringData1(), item.getStringData2(), item.getStringData3());
			}			
		}
		scanner.nextLine();
	}
	
	
}
