package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T03LambdaTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("모코코");
		list.add("늘푸른모코코");
		list.add("벛꽃모코코");
		
		for(String str : list) {
			System.out.println(str);
		}
		
		System.out.println("────────────────────────────");
		
		list.forEach(name -> System.out.println(name));
		
		///////////////////////////////////////
		
		list.forEach(System.out::println);
		
		///////////////////////////////////////
		
		// 메소드 참조의 일반적인 형태(::)
		
		/* 메소드 참조의 일반적인 형태
		 
		 참조변수::인스턴스메소드명
		 클래스명::정적메소드명
		 클래스명::인스턴스메소드명
		 생성자명::new
		  
		 */
		
		MyPrint mp = new MyPrint();
		
		System.out.println("참조변수::인스턴스메소드명...");
		list.forEach(mp::printName);
		
		System.out.println("클래스명::정적메소드명...");
		list.forEach(MyPrint::printName2);
		
		System.out.println("생성자명::new   ...");
		list.forEach(MyPrint::new);
		
		
		
		
	}
}


class MyPrint {
	public MyPrint() {
		// TODO Auto-generated constructor stub
	}
	
	public MyPrint(String name) {
		System.out.println("생성자에서 출력함 : " + name);
	}
	
	public void printName(String name) {
		System.out.println("name : " + name);
	}
	
	public static void printName2(String name) {
		System.out.println("name : " + name);
	}
	
}