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
		
		System.out.print("�����ȣ�� �Է��ϼ���: ");
		inputNum = scanner.nextLong();
		
		MyDao dao = MyDao.getInstance();
		list = dao.getEmpHistory(inputNum);
		
		System.out.println("	�̸�        		������         	������                	������");
		System.out.println("=================================================================================================");
		
		if(list.size() == 0) {
			System.out.println("��ȸ ����� �����ϴ�!");
		}else {

			for(GeneralDto item : list) {
				System.out.printf("%20s    %20s     %15s       %15s\n", 
						item.getStringData1(), item.getStringData2(), item.getStringData3(), item.getStringData4());
			}			
		}
		scanner.nextLine();
	}
	
	
}
