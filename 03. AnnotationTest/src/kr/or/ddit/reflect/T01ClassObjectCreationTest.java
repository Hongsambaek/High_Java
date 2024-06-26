package kr.or.ddit.reflect;

public class T01ClassObjectCreationTest {
/*
	Java Reflection 에 대해서....
	
 1. 리플렉션은 런타임 시점에 클래스 또는 멤버변수, 메소드, 생성자에 대한 정보를 가져오거나 수정 할 수 있고, 
 	새로운 객체를 생성하거나 메소드를 실행할 수 있다.
 	(컴파일 시점에 해당 정보를 알 수 없는 경우(소스코드 부재)에 유용하게 사용될 수 있음)
 	
 2. Reflection API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공된다.
 	
 3. java.lang.Class의 주요 메소드
 	- getName(), getSuperclass(), getInterfaces(), getModifiers() 등.
 	
 4. java.lang.reflect 패키지의 주요 클래스
 	- Field, Method, Constructor, Modifier 등.	
 */
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		// Class객체 (클래스 정보를 담고 있는 객체) 생성하기
		// 첫 번째 방법 : Class.forName 메소드 이용하기
		Class<?> klass = Class.forName("kr.or.ddit.reflect.T01ClassObjectCreationTest");
		
		// 두 번째 방법 : getClass() 메소드 이용하기
		T01ClassObjectCreationTest obj = new T01ClassObjectCreationTest();
		klass = obj.getClass();
		
		// 세 번째 방법 : .class 이용하기
		klass = T01ClassObjectCreationTest.class;
	}
}
