package com.javaex.phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwitchCaseVersion {

	public static void main(String[] args) throws IOException {

//------시작화면
		
		System.out.println("*************************************");
		System.out.println("* 	   전화번호 관리 프로그램 	    *");
		System.out.println("*************************************");
		
//------시작화면 끝	
				
		Scanner sc= new Scanner(System.in);
		
//======리스트생성
		List<Person> personList=new ArrayList<Person>();		//리스트 선언해두기

		////////파일읽기
		//파일 읽기 스트림
		InputStream in=new FileInputStream("C:\\javaStudy\\PhoneDB.txt");
		InputStreamReader isr=new InputStreamReader(in);
		BufferedReader br=new BufferedReader(isr);		
		
		//파일 쓰기 스트림
		OutputStream out=new FileOutputStream("C:\\javaStudy\\PhoneDB.txt");
		OutputStreamWriter osw=new OutputStreamWriter(out);
		BufferedWriter bw=new BufferedWriter(osw);
		
		
		
////////메뉴번호 반복하기			
		boolean processOn = true;
		
		while (processOn) {
			
			System.out.println("1. 리스트 2. 등록 3. 삭제 4. 검색 5.종료");
			System.out.println("---------------------------------------");
			System.out.print("> 메뉴번호 : ");
			int processNum = sc.nextInt();
			
//######메뉴번호 switch case 시작
			switch (processNum) {
			
	////메뉴번호 1일떄			
				case 1:
					
					System.out.println("<1. 리스트>");
					
					while (true) {
						
						String str = br.readLine(); 
					
						if (str == null) {
		
							break;
						}
					
						String[] re = str.split(",");
					
						personList.add(new Person(re[0], re[1], re[2]));
					
					}
					
					for (int i = 0; i <personList.size(); i++ ) {
						System.out.print( (i+1) );
						personList.get(i).showInfo();
					}
					
					
					/*
					for (Person info : pList) {
						info.showInfo();
					}
					*/
					break;
				
    ////메뉴번호 2일떄			
				case 2: 
					
					sc.nextLine();
					System.out.println("<2. 등록>");
					System.out.print(">이름: ");
					String name=sc.nextLine();		
					System.out.print(">휴대전화: ");
					String hp=sc.nextLine();	
					System.out.print(">회사전화: ");
					String company=sc.nextLine();	
					
					personList.add(new Person(name, hp, company));
					System.out.println("[등록되었습니다.]");	
										
					break;
				
	////메뉴번호 3일떄		
				case 3: 
					
					System.out.println("<3. 삭제>");
					
					System.out.println("정보를 입력하세요");
					System.out.print(">> 번호 : ");
					int del = sc.nextInt();
					
					personList.remove(del-1);
					System.out.println("[" + del +"번 삭제되었습니다]");
					
					break;
					
	////메뉴번호 4일떄	
				case 4: 
					
					sc.nextLine();
					System.out.println("<4. 검색>");
					System.out.print(">이름: ");
					String search = sc.nextLine();
					
					for (int i = 0; i < personList.size(); i++) {
						
						if (personList.get(i).getName().contains(search)  ) {
							personList.get(i).showInfo();
						}
					}
					
					break;
					
	////메뉴번호 5일떄	
				case 5: 
					
					System.out.println("주소록을 업데이트합니다");
					for(int i=0; i<personList.size(); i++) {
						bw.write(personList.get(i).getName() + "," + personList.get(i).getHp() + "," + personList.get(i).getCompany());
						bw.newLine();
					}
				
					System.out.println("5. 프로그램을 종료합니다");
					processOn = false;
					break;
				
					
	/////메뉴번호 없는 번호일떄			
				default:
					System.out.println("[다시 입력해 주세요.]");
					break;
		
			}
			
			
		}
		
  //----메뉴번호 5번 고르고 switch 나오고 while 나와서	
		
		System.out.println("============================");
		System.out.println("=========프로그램  종료=========");
		System.out.println("============================");
		
		
/////	끝		
		sc.close();
		bw.close();
		br.close();

	}

}
