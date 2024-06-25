package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T08HashMapTest {

	public static void main(String[] args) {

		Map<String, String> map = new HashMap<String, String>();

		// 데이터 추가 => put(key값, value값);
		map.put("name", "홍길동");
		map.put("tel", "010-1111-1111");
		map.put("addr", "대전");

		System.out.println("map => " + map);

		// 데이터 수정 => 데이터를 저장할 때 key 값이 같으면 나중에 입력한 값이 저장된다.
		// put(수정할 ket값, 새로운 value값);

		map.put("addr", "서울");
		System.out.println("수정 후 map =>" + map);

		// 데이터 삭제 => remove(삭제할 key값)
		map.remove("name");
		System.out.println("삭제 후 map =>" + map);

		// 데이터 읽기(가져오기) => get(key값);
		System.out.println("addr = " + map.get("addr"));
		System.out.println("==============================================");

		// key값들을 읽어와 데이터를 출력하는 방법

		// 방법1 => keySet() 메서드 이용하기
		Set<String> keySet = map.keySet();

		System.out.println("Iterator를 이용한 방법");
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + map.get(key));

		}
		System.out.println("-----------------------------------------------------");

		// 방법2 =>향상된 for문 'for-each'문을 이용한 방법
		for (String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}

		// 방법3 => value값들만 읽어와 출력하기
		System.out.println("value()메서드 이용하는 방법");
		for (String value : map.values()) {
			System.out.println(value);
		}
		// 방법4 => Map관련 클래스에는 Map.Entry 타입의 내부 클래스가 만들어져있다.
		// Map에서 이 Map.Entry 타입의 객체들을 Set타입의 객체로 반환해주는 메서드를 제공한다.
		System.out.println("entrySet()메서드를 이용한 방법");

		Set entrySet = map.entrySet();

		// 가져온 Entry 객체들을 접근하기 위한 Iterator 객체 가져오기
		Iterator entryIt = entrySet.iterator();
		while (entryIt.hasNext()) {
			Map.Entry entry = (Map.Entry) entryIt.next();
			System.out.println("key 값 : " + entry.getKey());
			System.out.println("value 값 : " + entry.getValue());
			System.out.println();
		}

	}

}
