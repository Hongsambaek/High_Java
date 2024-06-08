package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil3;

/*
   회원정보를 관리하는 프로그램을 작성하는데 
   아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
   (DB의 MYMEMBER테이블을 이용하여 작업한다.)
   
   * 자료 삭제는 회원ID를 입력 받아서 삭제한다.
   
   예시메뉴)
   ----------------------
      == 작업 선택 ==
      1. 자료 입력         ---> insert
      2. 자료 삭제         ---> delete
      3. 자료 수정         ---> update
      4. 전체 자료 출력   ---> select
      5. 작업 끝.
   ----------------------
    
      
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
public class T01MemberInfoTest {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in);

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 자료 입력
				insertMember();
				break;
			case 2: // 자료 삭제
				deleteMember();
				break;
			case 3: // 자료 수정
				updateMember();
				break;
			case 4: // 전체 자료 출력
				displayAllMember();
				break;
			case 5: // 작업 끝
				System.out.println("작업 END!");
				break;
			default:
				System.out.println("JAX");
			}
		} while (choice != 5);
	}

	/**
	 * 모든 회원정보를 출력하는 method
	 */
	private void displayAllMember() {

		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("ID\t생성일 \t이 름 \t전화번호 \t주  소");
		System.out.println("----------------------------------------");

		try {
			conn = JDBCUtil3.getConnection();

			stmt = conn.createStatement();

			rs = stmt.executeQuery("select*from mymember");

			while (rs.next()) {

				String memID = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");

				LocalDateTime regDt = rs.getTimestamp("reg_dt").toLocalDateTime();

				System.out.println(memID + "\t" + regDt + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}

			System.out.println("----------------------------------------");
			System.out.println("출력끝...");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}

	}

	private void deleteMember() {

		System.out.println();
		System.out.println("Delete ??");
		System.out.print("아이디 >>");
		String memID = scan.next();

		try {

			conn = JDBCUtil3.getConnection();

			String sql = "delete from mymember where mem_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memID);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(memID + "인 회원정보 삭제 success");
			} else {
				System.out.println(memID + "인 회원정보 삭제 fail");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}

	}

	private void updateMember() {
		boolean isExist = false;
		String memID = "";
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력해주세요.");
			System.out.print("회원ID >> ");
			memID = scan.next();

			isExist = checkMember(memID);
			if (!isExist) {
				System.out.println("존재하지 않는 ID입니다.");
				continue;
			}
		} while (!isExist);

		System.out.print("회원 이름 >>");
		String memName = scan.next();

		System.out.print("회원 전화번호 >>");
		String memTel = scan.next();

		scan.nextLine(); // 엔터키 제거용

		System.out.print("회원 주소 >>");
		String memAddr = scan.nextLine();

		try {
			conn = JDBCUtil3.getConnection();

			String sql = "update mymember \r\n" + "set mem_name = ?, mem_tel = ?, mem_addr = ? \r\n"
					+ "where mem_id = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memID);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(memID + "인 회원정보 수정 success");
			} else {
				System.out.println(memID + "인 회원정보 수정 fail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}

	}

	private void insertMember() {
		boolean isExist = false;
		String memID = "";
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력해주세요.");
			System.out.print("회원ID >> ");
			memID = scan.next();

			isExist = checkMember(memID);
			if (isExist) {
				System.out.println("이미 존재하는 ID입니다.");
				continue;
			}
		} while (isExist);

		System.out.print("회원 이름 >>");
		String memName = scan.next();

		System.out.print("회원 전화번호 >>");
		String memTel = scan.next();

		scan.nextLine(); // 엔터키 제거용

		System.out.print("회원 주소 >>");
		String memAddr = scan.nextLine();

		try {
			conn = JDBCUtil3.getConnection();

			String sql = "insert into mymember(mem_id, mem_name, mem_tel, mem_addr)\r\n" + "values(?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memID);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(memID + "인 회원정보 등록 success");
			} else {
				System.out.println(memID + "인 회원정보 등록 fail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	private boolean checkMember(String memID) {

		boolean isExist = false;
		try {
			conn = JDBCUtil3.getConnection();

			String sql = "select count(*) as cnt\r\n" + "from mymember\r\n" + "where mem_id = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memID);
			rs = pstmt.executeQuery();
			int cnt = 0;
			while (rs.next()) {
				cnt = rs.getInt("CNT");
				if (cnt > 0) {
					isExist = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return isExist;
	}

	public static void main(String[] args) {
		T01MemberInfoTest memObj = new T01MemberInfoTest();
		memObj.start();
	}

}
