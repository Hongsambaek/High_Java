package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 객체 입출력을 위한 보조스트림 예제
public class T15ObjectStreamTest {
   public static void main(String[] args) throws IOException {
      MemberVO mem1 = new MemberVO("홍길동", 20, "대전");
      MemberVO mem2 = new MemberVO("이순신", 30, "부산");
      MemberVO mem3 = new MemberVO("일지매", 40, "대구");
      MemberVO mem4 = new MemberVO("강감찬", 50, "광주");

      // 객체 출력을 위한 보조스트림 생성
      ObjectOutputStream oos = new ObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream("d:/D_Other/memObj.bin")));

      // 객체 쓰기 (저장)
      // 직렬화
      oos.writeObject(mem1);
      oos.writeObject(mem2);
      oos.writeObject(mem3);
      oos.writeObject(mem4);

      oos.close();
      System.out.println("객체 쓰기 작업 완료....");

      ////////////////////////////////////////////////////////////

      // 저장된 객체정보를 읽어와 출력
      ObjectInputStream ois = new ObjectInputStream(
    		  					  new BufferedInputStream(
    		  						  new FileInputStream("d:/D_Other/memObj.bin")));

      // 객체 입력처리를 위한 보조스트림 생성

      Object obj = null;

      try {
         while ((obj = ois.readObject()) != null) {
            MemberVO mem = (MemberVO) obj;
            System.out.println("이름 : " + mem.getName());
            System.out.println("나이 : " + mem.getAge());
            System.out.println("주소 : " + mem.getAddr());
            System.out.println("-------------------------------------");
         }
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
//         e.printStackTrace();
         System.out.println("읽기 작업 끝...");
      }

   }
}

class MemberVO implements Serializable {

//   transient => 직렬화 되지 않을 멤버 변수에 저장
//               (static필드도 질력화가 되지 않는다)
//            질력화 되지 않는 멤버변수는 기본값으로 저장
//               (참조형 변수 : null, 숫자형: 0, 논리형변수  : false)

   private String name;
   transient private int age;
   transient private String addr;

   public MemberVO(String name, int age, String addr) {
      super();
      this.name = name;
      this.age = age;
      this.addr = addr;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public String getAddr() {
      return addr;
   }

   public void setAddr(String addr) {
      this.addr = addr;
   }

   @Override
   public String toString() {
      return "MemberVO [name=" + name + ", age=" + age + ", addr=" + addr + "]";
   }
}
