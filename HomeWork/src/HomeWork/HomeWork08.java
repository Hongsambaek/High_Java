package HomeWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import HomeWork.Util.JDBCUtil2;

public class HomeWork08 {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		HomeWork08 board = new HomeWork08();
		board.start();
	}

	public void displayMenu() {
		System.out.println();
		System.out.println("---------------------------");
		System.out.println("  1. 전체 목록 출력");
		System.out.println("  2. 새글 작성");
		System.out.println("  3. 수정");
		System.out.println("  4. 삭제");
		System.out.println("  5. 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("---------------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1:
				displayAllBoard();
				break;
			case 2:
				insertBoard();
				break;
			case 3:
				updateMember();
				break;
			case 4:
				deleteBoard();
				break;
			case 5:
				searchBoard();
				break;
			case 6:
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);
	}

	private void searchBoard() {

		System.out.println();
		System.out.println("검색할 작성자 이름를 입력해 주세요.");
		System.out.print("작성자 이름 >> ");
		String boardWriter = scan.next();

		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println(" 번호\t글 제목\t작성자 이름\t작성 날짜\t내용");
		System.out.println("---------------------------------------------------");

		try {
			conn = JDBCUtil2.getConnection();
			String sql = " select * from jdbc_board where BOARD_Writer = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, boardWriter);

			rs = pstmt.executeQuery();

			boolean hasresults = false;
			while (rs.next()) {
				hasresults = true;
				String boardNo = rs.getNString("BOARD_NO");
				String boardName = rs.getNString("BOARD_TITLE");
				LocalDate boardDate = rs.getTimestamp("BOARD_DATE").toLocalDateTime().toLocalDate();
				String boardContent = rs.getNString("BOARD_CONTENT");

				System.out.println(
						boardNo + "\t" + boardName + "\t" + boardWriter + "\t" + boardDate + "\t" + boardContent);
			}
			if (!hasresults) {
				System.out.println("등록된 정보가 없습니다."); // 검색 결과가 없을 경우 메시지 출력
			}
			System.out.println("출력 끝....");

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
		}
	}

	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 게시판 번호를 입력해 주세요.");
		System.out.print("게시판 번호 >> ");
		String boardNo = scan.next();

		//////////////////////////////////////////////////////
		try {
			conn = JDBCUtil2.getConnection();
			String sql = " delete from jdbc_board where BOARD_NO = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, boardNo);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(boardNo + "번의 글을 삭제하였습니다");
			} else {
				System.out.println(boardNo + "번의 글 삭제 실패");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
		}
	}

	private void updateMember() {
		boolean isExist = false;
		String boardNo = "";
		do {
			System.out.println();
			System.out.println("수정 할 게시판 번호를 입력해 주세요.");
			System.out.print("게시판 번호 >> ");
			boardNo = scan.next();

			isExist = checkBoard(boardNo);

			if (!isExist) {
				System.out.println(boardNo + "의 게시판이 없습니다.");
				System.out.println("다시 입력해 주세요.");
			}
		} while (!isExist);

		System.out.print("게시판 이름 >> ");
		String boardTitle = scan.next();
		scan.nextLine();		

		System.out.print("작성자 이름 >> ");
		String boardWriter = scan.next();

		scan.nextLine();

		System.out.print("게시판 내용 >> ");
		String boardContent = scan.nextLine();

		////////////////////////////////////////////////////////////////////////

		try {
			conn = JDBCUtil2.getConnection();
			String sql = " update jdbc_board\r\n"
					+ "set BOARD_TITLE=?, BOARD_WRITER=?, BOARD_CONTENT=? where BOARD_NO = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);
			pstmt.setString(4, boardNo);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(boardWriter + "님의 [" + boardTitle + "] 작성 완료");
			} else {
				System.out.println(boardWriter + "의 글 작성 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납 close = finally 넣는이유 반드시 실행해야하기때문에
			JDBCUtil2.close(conn, stmt, pstmt, rs);
		}
	}

	private void displayAllBoard() {
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println(" 번호\t글 제목\t작성자 이름\t작성 날짜\t내용");
		System.out.println("---------------------------------------------------");

		try {
			conn = JDBCUtil2.getConnection();

			stmt = conn.createStatement();

			rs = stmt.executeQuery(" select * from jdbc_board ");

			while (rs.next()) {
				String boardNo = rs.getNString("BOARD_NO");
				String boardName = rs.getNString("BOARD_TITLE");
				String boardWriter = rs.getNString("BOARD_WRITER");
				LocalDate boardDate = rs.getTimestamp("BOARD_DATE").toLocalDateTime().toLocalDate();
				String boardContent = rs.getNString("BOARD_CONTENT");

				System.out.println(
						boardNo + "\t" + boardName + "\t" + boardWriter + "\t" + boardDate + "\t" + boardContent);
			}
			System.out.println("---------------------------------------------------");
			System.out.println("출력 끝....");

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
		}

	}

	private void insertBoard() {

		System.out.print("게시판 이름 >> ");
		String boardTitle = scan.next();
		scan. nextLine();

		System.out.print("작성자 이름 >> ");
		String boardWriter = scan.next();

		scan.nextLine();

		System.out.print("게시판 내용 >> ");
		String boardContent = scan.nextLine();

		///////////////////////////////
		try {

			conn = JDBCUtil2.getConnection();
			String sql = " insert into jdbc_board(board_no, board_title, board_writer, board_date, board_content)\r\n"
					+ "values(BOARD_SEQ.NEXTVAL,?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setNString(1, boardTitle);
			pstmt.setNString(2, boardWriter);
			pstmt.setNString(3, boardContent);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(boardWriter + "님의 [" + boardTitle + "] 작성 완료");
			} else {
				System.out.println(boardWriter + "의 글 작성 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
		}
	}

	private boolean checkBoard(String boardNo) {

		boolean isExist = false;
		try {
			conn = JDBCUtil2.getConnection();
			String sql = " select count(*) as cnt\r\n" + "from jdbc_board\r\n" + "where board_no=? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardNo);

			rs = pstmt.executeQuery();

			int cnt = 0;
			while (rs.next()) {
				cnt = rs.getInt("CNT");
			}

			if (cnt > 0) {
				isExist = true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			// 꼭! 마지막 자원반납해주기
			JDBCUtil2.close(conn, stmt, pstmt, rs);
		}
		return isExist;
	}
}
