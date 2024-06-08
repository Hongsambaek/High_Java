package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class T07EqualsHashCodeTest {

	/*
	 * 해시함수(Hash function)는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑해주는 함수이다. 해시함수에 의해 얻어지는 값은
	 * 해시값,해시 코드, 또는 간단하게 해시라고도 부른다.
	 * 
	 * HastSet, HastMap, Hashtable과 같은 컬렉션 클래스를 사용할 경우에는 객체가 서로 같은지를 비교하기 위해
	 * HashCode()와 eqauls() 메서드를 호출한다. 그러므로 객체가 서로 같은지를 적절히 판단하기 위해서는 두 메서드를 재정의
	 * 해주어야 한다. (객체가 추가되는 시점에 객체가 같은지 여부를 판단한다.)
	 * 
	 * => eqauls()는 두 객체의 내용(값)이 같은지 비교하기 위한 메서드이다. =============================<
	 * => hashCode()는 객체에 대한 해시코드를 가져오기 위한 메서드이다. => 해시테이블 작성시 사용됨.
	 * 
	 * =>equals()와 hashCode() 메서드에 대한 관련된 규칙(Convention) 1. 두 객체가 같으면 반드시 같은
	 * hashCode값을 가져야한다. 2. 두 객체가 같으면 equals() 메서드를 호출했을 때 true을 반환해야 한다. 즉, 객체 a,d가
	 * 같다면 a.equals(b)와 b.equals(a) 둘 다 true를 반환해야 한다. 3. 두 객체의 hashCode가 같다고 해서 구
	 * 객체가 반드시 같은 객체는 아니다. 4. equalsI()를 재정의 할 때는 hashCode()도 재정의 해주어야 한다. 5.
	 * hashCode()는 기본적으로 Heap메모리에 있는 각 객체에 대한 메모리 주소 매핑 정보를 기반으로 한 정수값을 반환한다. 그러므로
	 * 클래스에서 hashCode()를 재정의하지 않으면 절대로 두 객체가 같은 것으로 간주될 수는 없다.
	 */

	public static void main(String[] args) {

		Person p1 = new Person(1, "홍길동");
		Person p2 = new Person(1, "홍길동");
		Person p3 = new Person(1, "이순신");

		System.out.println("p1 == p2 : " + (p1 == p2));
		System.out.println("p1.equals(p2) : " + p1.equals(p2));

		Set<Person> pSet = new HashSet<Person>();

		System.out.println("add(p1) 성공여부 : " + pSet.add(p1));
		System.out.println("add(p2) 성공여부 : " + pSet.add(p2));

		System.out.println("p1, p2 등록 후 데이터");

		for (Person p : pSet) {
			System.out.println(p.getId() + " : " + p.getName());
		}

		System.out.println("add(p3) 성공여부 : " + pSet.add(p3));
		System.out.println("p3 등록 후 데이터");
	}

}

class Person {
	private int id;
	private String name;

	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

//	@Override
//	public boolean equals(Object obj) {
//		Person p = (Person) obj;
//
//		return this.getId() == p.getId() && this.getName().equals(p.getName());
//
//	}
//
//	@Override
//	public int hashCode() {
//		// TODO Auto-generated method stub
//		return (name + id).hashCode();
//	}

}
