package HomeWork;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HomeWork03 {

	Scanner sc = new Scanner(System.in);
	Map<Integer, HotelVo> map = new HashMap<>();
	boolean flag = true;

	public static void main(String[] args) {
		HomeWork03 obj = new HomeWork03();
		obj.process();
	}

	public void process() {

		hotelOpen();
		while (flag) {
			selectMenu();
		}

	}

	public void hotelOpen() {
		System.out.println("*******************************************");
		System.out.println("호텔 문을 열었습니다.");

	}

	public void selectMenu() {
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.print("메뉴선택 → ");
		int sel = sc.nextInt();
		hotelWork(sel);

	}

	public void hotelWork(int sel) {

		if (sel == 1) {
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.print("방 번호 입력 : ");
			int room = sc.nextInt();

			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.print("이름 입력 : ");
			String name = sc.next();

			map.put(room, new HotelVo(room, name));
			System.out.println("체크인 되었습니다.");
		} else if (sel == 2) {
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			System.out.print("방 번호 입력 : ");
			int room = sc.nextInt();

			map.remove(room);
			System.out.println("체크아웃 되었습니다.");

		} else if (sel == 3) {
			Set<Integer> keySet = map.keySet();

			if (keySet.size() == 0) {
				System.out.println("등록된 정보가 없습니다.");
			} else {
				Iterator<Integer> it = keySet.iterator();

				while (it.hasNext()) {
					Integer room = it.next();
					HotelVo h = map.get(room);
					System.out.println("방 번호 : " + room + ", 투숙객 : " + h.getName());
				}
			}
//			System.out.println("방번호 : " + );

		} else if (sel == 4) {
			System.out.println("*******************************************");
			System.out.println("호텔 문을 닫았습니다.");
			System.out.println("*******************************************");
			flag = false;
		}
	}

	public void name() {

	}

	class HotelVo {
		private int room;
		private String name;

		public HotelVo(int room, String name) {
			super();
			this.room = room;
			this.name = name;
		}

		public int getRoom() {
			return room;
		}

		public void setRoom(int room) {
			this.room = room;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}