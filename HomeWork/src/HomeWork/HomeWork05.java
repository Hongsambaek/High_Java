package HomeWork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sound.sampled.Line;

/*
 10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 >로 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수를 기준으로 정렬하여 출력한다.

 */

public class HomeWork05 {
	
	public static int rank = 1;
	
	public static void main(String[] args) {
		
		Racing[] race = {new Racing("1번마"), new Racing("2번마"), new Racing("3번마"), new Racing("4번마"), new Racing("5번마"),
						 new Racing("6번마"), new Racing("7번마"), new Racing("8번마"), new Racing("9번마"), new Racing("10번마"),
						 new Racing("11번마"), new Racing("12번마")};
		
		List<Racing> RacingList = new ArrayList<Racing>();
		for(int i = 0; i<12; i++) {
			RacingList.add(race[i]);
		}
	
		for(Racing rc : RacingList) {
			rc.start();
		}
		
		while (HomeWork05.rank <= 12) {
			for (Racing hs : RacingList) {
				System.out.println(hs.getStr());
			}
			System.out.println();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (Racing h : RacingList) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Collections.sort(RacingList);
		
		System.out.println("Game End!!!");
		System.out.println("────────────────────────────────────────────");
		System.out.println(" 경기 결과 ");
		System.out.println();
		System.out.println("────────────────────────────────────────────");
		System.out.println("순위\t:\t이름");
		System.out.println("────────────────────────────────────────────");
		
		for (Racing rc : RacingList) {
			System.out.println(rc.getRank() + "\t:\t" + rc.getName());
	}
  }
}


class Racing extends Thread implements Comparable<Racing> {
	
	private String name;
	private int rank;
	private String str = "";
	
	public Racing(String name) {
		super(name);
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public void run() {
		for(int i = 0; i<50; i++) {
			String string = name + " : \t";
			for (int j = 1; j < i; j++) {
				string += "=";
			}
			string += ">";

			for (int j = i + 1; j <= 50; j++) {
				string += "-";
			}

			setStr(string);
			
			try {
				Thread.sleep((int) (Math.random() * 300));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		this.setRank(HomeWork05.rank++);
	}

	@Override
	public int compareTo(Racing rc) {
		
		return new Integer(this.rank).compareTo(rc.getRank());
	}

	@Override
	public String toString() {
		return "Racing [말=" + name + ", 순위=" + rank + "]";
	}
}

