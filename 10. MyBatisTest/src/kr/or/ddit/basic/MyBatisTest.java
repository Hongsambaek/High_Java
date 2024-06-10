package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MyBatisTest {
	public static void main(String[] args) {
		
		/* MyBatis를 이용하여 DB작업을 처리하는 작업순서 
		 * 1. MyBatis의 환경설정 파일을 읽어와 필요한 객체를 생성한다.
		 * 
		 */
		
		SqlSessionFactory sqlSessionFactory = null;
		
		try {
			// 1-1. XML설정 파일 읽어오기
			Charset charset = Charset.forName("UTF-8"); // 설정파일의 인코딩정보 설정(한글처리를 위해서....)
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 SqlSessionFactory 객체를 생성한다.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close(); // 스트림 닫기
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		// 2. 실행할 SQL문에 맞는 쿼리문을 호출하여 원하는 작업을 수행한다.
		
		// 2-1. insert 작업 연습
		System.out.println("insert 작업 시작...");
		
		// 1) 저장할 데이터를 VO에 담는다.
		MemberVO mv = new MemberVO();
		mv.setMemId("d002");
		mv.setMemName("강감찬");
		mv.setMemTel("1234-5678");
		mv.setMemAddr("충청남도");
		
		// 2) SqlSession 객체를 이용하여 해당 쿼리문을 실행한다.
		SqlSession session = sqlSessionFactory.openSession(false); // autoCommit 여부 설정
		
		try {
			
			// 형식) insert("namespace값.쿼리ID값, 파라미터객체);
			int cnt = session.insert("memberTest.insertMember", mv);
			
			if(cnt > 0) {
				System.out.println("insert 작업 성공!!");
				session.commit(); // 커밋
			}else {
				System.out.println("insert 작업 실패!!!");
			}
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close(); // 커넥션풀에 커넥션 반납하기
		}
		
		System.out.println("────────────────────────────────────────────────────");
		
		// 2-2. update 연습
		System.out.println("update 작업 시작.....");
		
		mv = new MemberVO();
		mv.setMemId("d001");
		mv.setMemName("홍길동");
		mv.setMemTel("8077-9999");
		mv.setMemAddr("세종특별시");
		
		session = sqlSessionFactory.openSession(); // 파라미터 넣지 않으면 autoCommit false로 지정함.
		
		try {
			
			int cnt = session.update("memberTest.updateMember", mv);
			
			if(cnt > 0) {
				System.out.println("update 작업 성공 !!");
				session.commit();
			}else {
				System.out.println("update 작업 실패 !!!");
			}
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		
		// 2-3) delete 연습
//		System.out.println("delete 작업 시작....");
//		
//		session = sqlSessionFactory.openSession(); // autoCommit false....
//		
//		try {
//			
//			int cnt = session.delete("memberTest.deleteMember", "d001");
//			
//			if(cnt > 0) {
//				System.out.println("delete 작업 성공 !!");
//				session.commit();
//			}else {
//				System.out.println("delete 작업 실패 !!!");
//			}
//			
//			
//			
//		}catch(PersistenceException ex) {
//			ex.printStackTrace();
//		}finally {
//			session.close();
//		}
		
		/////////////////////////////////////////////////////////////
		
		// 2-4) select 연습
		
		// 1) 응답의 결과가 여러개일 경우..
		System.out.println("select 연습(결과가 여러 개일 경우...)");
		
		session = sqlSessionFactory.openSession(true);
		
		try {
			
			List<MemberVO> memList =  session.selectList("memberTest.selectAllMember");
			
			for(MemberVO mv2 : memList) {
				System.out.println("ID : " + mv2.getMemId());
				System.out.println("이름 : " + mv2.getMemName());
				System.out.println("전화번호 : " + mv2.getMemTel());
				System.out.println("주소 : " + mv2.getMemAddr());
				
				System.out.println("────────────────────────────────────────────────────");
			}
			System.out.println("전체 회원정보 출력 끝...");
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		
		// 2) 응답의 결과가 1개일 경우....
		System.out.println("select 연습(결과가 한 개일 경우...)");
		
		session = sqlSessionFactory.openSession(true);
		
		try {
			
			MemberVO mv3 = session.selectOne("memberTest.getMember", "d001");
			System.out.println("ID : " + mv3.getMemId());
			System.out.println("이름 : " + mv3.getMemName());
			System.out.println("전화번호 : " + mv3.getMemTel());
			System.out.println("주소 : " + mv3.getMemAddr());
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
}
