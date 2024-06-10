package HomeWork;

import java.util.Scanner;
import java.util.TreeSet;

public class HomeWork02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("──────────────────────────────────────────────────");
			System.out.println("LOTTO 프로그램");
			System.out.println("──────────────────────────────────────────────────");
			System.out.println("1. LOTTO 구입");
			System.out.println("2. Program End");
			System.out.println();
			
			System.out.print("Menu : ");
			int sel = sc.nextInt();
			
			if(sel == 1) {
				System.out.println("──────────────────────────────────────────────────");
				System.out.println("LOTTO 구입 시작");
				System.out.println();
				System.out.println("1000원에 로또번호 하나입니다.");
				System.out.print("금액을 입력하시오 : ");
				int money = sc.nextInt();
				System.out.println();
				
				int Lotto = money / 1000;
				int change = money % 1000;
				for(int i = 0; i<Lotto; i++) {
					TreeSet<Integer> intRnd = new TreeSet<Integer>();
					while(intRnd.size() < 6) {
						int num = (int)(Math.random() * 45 + 1);
						intRnd.add(num);
					}
					System.out.println("오늘의 로또 번호는? " + (i+1) + " : " + intRnd);
				}
				System.out.println("──────────────────────────────────────────────────");
				System.out.println("받은 금액은 : " + money + "이고 거스름돈은 : " + change + "입니다.");
				continue;
				
			}
			
			else if(sel == 2) {
				System.out.println(" 😎 LOTTO를 종료합니다  Thank You 😎 ");
				break;
			}
			
			else {
				System.out.println("MENU로 다시 가십시오.");
				continue;
			}
		}
		
	}
}
