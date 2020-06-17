package com.javaex.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PhoneApp {

	public static void main(String[] args) {
		PhoneDao pDao = new PhoneDao();
		List<PhoneVo> pList = new ArrayList<PhoneVo>();
		PhoneVo pVo = null;
		
		int num;
		boolean run = true;
		String name, hp, company;
		int person_id;
		Scanner sc = new Scanner(System.in);
		
		while(run) {
		System.out.println("****************************************");
		System.out.println("**       전화번호 관리 프로그램       **");
		System.out.println("****************************************");
		System.out.println("1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료");
		System.out.print(">메뉴번호:");
		num = sc.nextInt();
		sc.nextLine();
		switch (num) {
		case 1 : 
			pList.clear();
			pList = pDao.getPhoneList();
			
			for(PhoneVo vo : pList) 
			{
				System.out.print(vo.getPerson_id()+".");
				System.out.print(vo.getName()+"\t");
				System.out.print(vo.getHp()+"\t");
				System.out.print(vo.getCompany()+"\n");
			}
			break;
		case 2 :
			System.out.print("이름 > ");
			name = sc.nextLine();
			System.out.print("\n휴대전화 > ");
			hp = sc.nextLine();
			System.out.print("\n회사번호 > ");
			company = sc.nextLine();
			
			pVo = new PhoneVo(name, hp, company);
			pDao.phoneInsert(pVo);
			
			break;
		case 3 : 
			System.out.print("번호 >");
			person_id=sc.nextInt();
			sc.nextLine();
			System.out.print("이름 >");
			name = sc.nextLine();
			System.out.print("휴대전화 >");
			hp = sc.nextLine();
			System.out.print("회사번호 >");
			company = sc.nextLine();
			
			pVo = new PhoneVo(person_id, name, hp, company);
			pDao.updateBook(pVo);
			break;
		case 4 : 
			System.out.print(">번호 : ");
			pDao.deleteBook(sc.nextInt());
			break;
		case 5 : 
			System.out.print("검색어 >  ");
			String str = sc.nextLine();
			pList.clear();
			pList = pDao.search(str);
			
			for(PhoneVo vo : pList) 
			{
				System.out.print(vo.getPerson_id()+".");
				System.out.print(vo.getName()+"\t");
				System.out.print(vo.getHp()+"\t");
				System.out.print(vo.getCompany()+"\n");
			}
			break;
		case 6 : run = false; break;
		
		default : System.out.println("다시입력해주세요");
		}
		}
		sc.close();
		System.out.println("프로그램 종료");
	}

}
