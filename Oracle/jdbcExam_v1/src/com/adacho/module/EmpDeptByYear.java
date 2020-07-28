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
		
		System.out.print("�Ի�⵵�� �Է��ϼ���: ");
		inputNum = scanner.nextLong();
		
		MyDao dao = MyDao.getInstance();
		list = dao.getEmpDept(inputNum);
		
		System.out.println("�����ȣ        �̸�         ��                �μ�");
		System.out.println("===========================================================");
		
		if(list.size() == 0) {
			System.out.println("��ȸ ����� �����ϴ�!");
		}else {

			for(GeneralDto item : list) {
				System.out.printf("%5d    %10s     %10s       %15s\n", 
						item.getLongData1(), item.getStringData1(), item.getStringData2(), item.getStringData3());
			}			
		}
		scanner.nextLine();
	}
	
	
}
