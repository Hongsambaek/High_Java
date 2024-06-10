package HomeWork;

import java.util.Scanner;

public class HomeWork04 {

	private final static double pie = 3.14;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("겉 넓이를 구하고 싶은 행성을 입력하시오 : ");

		Planet myPlanet;

		String str = sc.nextLine();

		myPlanet = Planet.valueOf(str);

		System.out.println(str + "의 겉넓이는 " + pie * 4 * myPlanet.getData() * myPlanet.getData() + "km² 입니다");
		System.out.println(str + "의 반지름은 " + myPlanet.getData() + "km 입니다");

	}
}

enum Planet {
	수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);

	private int data;

	Planet(int data) {
		this.data = data;
	}

	public int getData() {
		return data;
	}
}