package HomeWork;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class HomeWork07 {
	private Scanner scan = new Scanner(System.in);
	private TreeMap<Integer, MemberVO> map = new TreeMap<Integer, MemberVO>();
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;
	
	
	public static void main(String[] args) {
		
		new HomeWork07().hotelBookStart();
	}
	
	
	public void Menu() {
		System.out.println("***************************");
		System.out.println("   호텔 문을 열었습니다.");
		System.out.println("***************************");
		System.out.println();
		System.out.println("****************************************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인 	2. 체크아웃	3. 객실상태	4. 업무종료");
		System.out.println("****************************************************************");
		System.out.print("메뉴 선택 >> ");
	}
	
	private void hotelBookStart() {
		boolean openClose = true;
		Menu();
		Open();
		while(openClose) {
			
			int menuNum = scan.nextInt();
			
			switch(menuNum) {
			case 1 :
				checkIn();
				break;
			case 2 :
				checkOut();
				break;
			case 3 :
				roomStatus();
				break;
			case 4 :
				Close();
				openClose = false;
				break;
			default :
				System.out.println("잘못된 번호입니다. 다시 입력하십시오.");
				
				
			}
		}
	}
	
	
	private void Open() {
		try {
			ois = new ObjectInputStream(
					  new FileInputStream("d:/D_Other/homework07.txt"));
			Object obj = null;
			try {
				while ((obj = ois.readObject()) != null) {
					MemberVO mem = (MemberVO) obj;
					map.put(mem.getNo(), new MemberVO(mem.getName(), mem.getNo()));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
		
	}
	


	private void Close() {
		System.out.println();

		try {
			oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream("/Users/gimmingang/D_Other/homework07.txt")));
			for (Entry<Integer, MemberVO> entry : map.entrySet()) {
				oos.writeObject(entry.getValue());
			}
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
	}


	private void checkIn() {
		System.out.println();
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.print("방 호 수 >> ");
		int no = scan.nextInt();
		
		if(map.get(no) != null) {
			System.out.println(map.get(no).getName() + "씨는 이미 등록된 사람입니다.");
			return;
		}
		System.out.println("누구를 체크인 하시겠습니까? >> ");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		map.put(no, new MemberVO(name, no));
		
		System.out.println("체크인 되었습니다.");
		
	}
	
	private void checkOut() {
		System.out.println();
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방 호 수 >> ");
		int no = scan.nextInt();
		
		if(map.remove(no) == null) {
			System.out.println(map.get(no).getName() + "씨는 등록되지 않은 사람입니다.");
		}
		else {
			System.out.println(map.get(no).getName() + "씨가 정상적으로 체크아웃 되었습니다.");
		}
		System.out.println("체크아웃 되었습니다.");
		
	}
	
	private void roomStatus() {
		Set<Integer> KeySet = map.keySet();
		
		if(KeySet.size() == 0) {
			System.out.println("등록된 투숙객이 아닙니다.");
		}
		else {
			Iterator<Integer> it = KeySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				cnt++;
				int no = it.next();
				MemberVO mem = map.get(no);
				System.out.println(cnt  + "\t" + mem.getName() + "\t" + no );
				System.out.println();
			}
		}
	}
}










class MemberVO implements Serializable {
	private String name;
	private int No;
	/**
	 * @param name
	 * @param no
	 */
	
	public MemberVO(String name, int no) {
		super();
		this.name = name;
		this.No = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return No;
	}

	public void setNo(int no) {
		No = no;
	}

	@Override
	public String toString() {
		return "MemberVO [투숙객=" + name + ", 방호수=" + No + "]";
	}
	
	
	
}