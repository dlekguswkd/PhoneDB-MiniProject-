package com.javaex.phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneDB {

	public static void main(String[] args) throws IOException{
		
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
		
		String str;
		
		//한줄씩읽기
		while(true) {
			
			//파일 한줄씩 읽기
			str=br.readLine();
			
			//데이터 없으면 끝
			if (str==null) {
				break;
			}
			
			//데이터 있으면 ,로 나눠주기
			String[] personInfo=str.split(",");
			String name=personInfo[0];
			String hp=personInfo[1];
			String company=personInfo[2];
			
			//Person 으로 묶는다
			Person person=new Person(name, hp, company);
			
			
			//리스트에 담는다
			personList.add(person);
				
		}			
//=========리스트생성 끝		
		
		
//메뉴번호 반복하기		
		while(true) {
			
			System.out.println("");
			System.out.println("1.리스트	2.등록	3.삭제	4.검색	5.종료");
			System.out.println("-------------------------------------");
			System.out.print(">메뉴번호:");
			
			int num =sc.nextInt();		//메뉴번호 num
			
//######메뉴번호 if문 시작				
   /////메뉴번호 5일떄	
			if (num==5) {
				
				System.out.println("");
				System.out.println("*************************************");
				System.out.println("*	      감사합니다  	 	    *");
				System.out.println("*************************************");
				break;
			
  ////메뉴번호 1일떄
			}else if(num==1) {
					
				//출력
				System.out.println("<1.리스트>");
				for (int i=0; i<personList.size(); i++) {
					System.out.print((i+1)+".   ");
					personList.get(i).showInfo();
				}
		
		
  /////메뉴번호 2일떄			
			}else if(num==2) {
				
				//파일 쓰기 스트림
				OutputStream out=new FileOutputStream("C:\\javaStudy\\PhoneDB.txt");
				OutputStreamWriter osw=new OutputStreamWriter(out);
				BufferedWriter bw=new BufferedWriter(osw);
				
				sc.nextLine();		//숫자 쓰던걸 문자로 바꿔주기
				
				System.out.println("<2.등록>");
				System.out.print(">이름: ");
				String name=sc.nextLine();		
				System.out.print(">휴대전화: ");
				String hp=sc.nextLine();	
				System.out.print(">회사전화: ");
				String company=sc.nextLine();	
				
				personList.add(new Person(name, hp, company));
				System.out.println("[등록되었습니다.]");
			
				for(int i=0; i<personList.size(); i++) {
					bw.write(personList.get(i).getName() + "," + personList.get(i).getHp() + "," + personList.get(i).getCompany());
					bw.newLine();
				}
				
				bw.close();
				
				
    /////메뉴번호 3일떄				
			}else if(num==3) {
				
				//파일 쓰기 스트림
				OutputStream out=new FileOutputStream("C:\\javaStudy\\PhoneDB.txt");
				OutputStreamWriter osw=new OutputStreamWriter(out);
				BufferedWriter bw=new BufferedWriter(osw);
				
				System.out.println("<3.삭제>");
				System.out.println(">번호: ");
				int del = sc.nextInt();
				personList.remove(del-1);		
				System.out.println("[삭제되었습니다.]");
				
				for(int i=0; i<personList.size(); i++) {
					bw.write(personList.get(i).getName() + "," + personList.get(i).getHp() + "," + personList.get(i).getCompany());
					bw.newLine();
				}
				
				bw.close();;
				
 				
    /////메뉴번호 4일떄	
			}else if(num==4) {
				
				sc.nextLine();		//숫자 쓰던걸 문자로 바꿔주기
				
				System.out.println("<4.검색>");
				System.out.print(">이름: ");
				String search = sc.nextLine();
		
		//---내가찾는글자가 없을때, 있을때
				boolean s=false;
				
				for(int i = 0; i< personList.size(); i++) {
				
		//----있을때 나타나는 문장
					if(personList.get(i).getName().contains(search)) {
						System.out.print((i+1)+".   ");
						personList.get(i).showInfo();
						s=true;
					}
					
				}
				
		//----없을때 나타나는 문장		
				if(s==false) {
					System.out.println("검색어와 일치하는 이름이 없습니다.");
				}
				
	 /////메뉴번호 없는 번호일떄
			}else {
				
				System.out.println("[다시 입력해 주세요.]");
			
			}
		
		}
		
/////	끝	
		br.close();
		sc.close();
	}
	
}
