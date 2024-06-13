package HomeWork.main;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import HomeWork.Util.ScanUtil;
import HomeWork.vo.BoardVO;

public class HomeWork10 {
	private Scanner sc;
	private static SqlSessionFactory sqlSessionFactory;

	private HomeWork10() {
		sc = new Scanner(System.in);
		sqlSessionFactory = null;
	}

	public static void main(String[] args) {
		HomeWork10 hw = new HomeWork10();
		try {
			// 1-1. XML설정 파일 읽어오기
			Charset charset = Charset.forName("UTF-8"); // 설정파일의 인코딩정보 설정(한글처리를 위해)
			Resources.setCharset(charset);

			Reader rd = Resources.getResourceAsReader("config1/mybatis-config.xml");

			// 1-2. 위에서 읽어온 Reader 객체를 이용하여 SqlSessionFactory 객체를 생성
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
			rd.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		hw.printStart();

	}

	private void printStart() {
		while (true) {
			printHome();
			int sel = sc.nextInt();
			switch (sel) {
			case 1:
				displayAll();
				break;
			case 2:
				insert();
				break;
			case 3:
				update();
				break;
			case 4:
				delete();
				break;
			case 5:
				search();
				break;
			default:
				break;
			}
		}
	}

	private void printHome() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------");
		System.out.println("\t\t  어떤 업무를 하시겠습니까?");
		System.out.println("1. 전체목록 출력  2. 새글작성  3. 수정  4. 삭제  5. 검색");
		System.out.println("-----------------------------------------------------------------");
		System.out.print("메뉴 => ");
	}

	private void search() {
		SqlSession session = sqlSessionFactory.openSession(true);
		int cnt = 0;
		System.out.println();
		String name = ScanUtil.nextLine("검색할 이름을 입력해주세요 => ");

		try {
			cnt = session.selectOne("boardTest.checkName", name);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		if (cnt == 0) {
			System.out.println();
			System.out.println("입력하신 번호의 게시판이 존재하지 않습니다.");
			return;
		}
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println("번호\t제목\t\t작성자\t날짜\t\t내용");
		System.out.println("---------------------------------------------------------");

		session = sqlSessionFactory.openSession(true);

		try {
			List<BoardVO> boardList = session.selectList("boardTest.searchBoard", name);
			if (boardList.size() == 0) {
				System.out.println();
				System.out.println("회원정보가 존재하지 않습니다.");
			}
			for (BoardVO bl : boardList) {
				System.out.println(bl.getBoardNo() + "\t" + bl.getBoardTitle() + "\t" + bl.getBoardWriter() + "\t"
						+ bl.getBoardDate() + "\t\t" + bl.getBoardContent());
			}
			System.out.println("---------------------------------------------------------");
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

	}

	private void delete() {
		SqlSession session = sqlSessionFactory.openSession(true);
		int cnt = 0;
		System.out.println();
		int boardNo = ScanUtil.nextInt("삭제할 번호를 입력하세요 => ");

		try {
			cnt = session.selectOne("boardTest.checkName", boardNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		if (cnt == 0) {
			System.out.println();
			System.out.println("입력하신 번호의 게시판이 존재하지 않습니다.");
			return;
		}
		session = sqlSessionFactory.openSession(false);
		try {
			cnt = session.delete("memberTest.deleteMember", "d001");
			if (cnt > 0) {
				System.out.println();
				System.out.println("삭제에 성공했습니다.");
				session.commit();
			} else {
				System.out.println();
				System.out.println("삭제에 실패했습니다.");
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void update() {
		SqlSession session = sqlSessionFactory.openSession(true);
		int cnt = 0;
		System.out.println();
		int boardNo = ScanUtil.nextInt("수정할 번호를 입력하세요 => ");
		try {
			cnt = session.selectOne("boardTest.checkName", boardNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		if (cnt == 0) {
			System.out.println();
			System.out.println("입력하신 번호의 게시판이 존재하지 않습니다.");
			return;
		}
		session = sqlSessionFactory.openSession(false);

		try {
			cnt = session.delete("memberTest.deleteMember", "d001");
			if (cnt > 0) {
				System.out.println();
				System.out.println("삭제에 성공했습니다.");
				session.commit();
			} else {
				System.out.println();
				System.out.println("삭제에 실패했습니다.");
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		String title = ScanUtil.nextLine("제목 => ");
		String content = ScanUtil.nextLine("내용 => ");

		BoardVO bv = new BoardVO();
		bv.setBoardTitle(title);
		bv.setBoardContent(content);
		bv.setBoardNo(boardNo);

		try {
			cnt = session.update("boardTest.updateBoard", bv);
			if (cnt > 0) {
				System.out.println();
				System.out.println("수정에 성공했습니다.");
				session.commit();
			} else {
				System.out.println();
				System.out.println("수정에 실패했습니다.");
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}

	}

	private void insert() {
		SqlSession session = sqlSessionFactory.openSession(false);
		int cnt = 0;
		System.out.println();
		String title = ScanUtil.nextLine("제목 => ");
		String writer = ScanUtil.nextLine("작성자 => ");
		String content = ScanUtil.nextLine("내용 => ");

		BoardVO bv = new BoardVO();
		bv.setBoardTitle(title);
		bv.setBoardWriter(writer);
		bv.setBoardContent(content);

		try {
			cnt = session.insert("boardTest.insertBoard", bv);
			if (cnt > 0) {
				System.out.println();
				System.out.println("게시 새글작성 성공했습니다.");
				session.commit();
			} else {
				System.out.println();
				System.out.println("게시 새글작성 실패했습니다.");
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	private void displayAll() {
		SqlSession session = sqlSessionFactory.openSession(true);
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println("번호\t제목\t\t작성자\t날짜\t\t내용");
		System.out.println("---------------------------------------------------------");

		try {
			List<BoardVO> boardList = session.selectList("boardTest.selectAllBoard");
			if (boardList.size() == 0)
				System.out.println("회원정보가 존재하지 않습니다.");
			else {
				for (BoardVO bv : boardList) {
					System.out.println(bv.getBoardNo() + "\t" + bv.getBoardTitle() + "\t" + bv.getBoardWriter() + "\t"
							+ bv.getBoardDate() + "\t\t" + bv.getBoardContent());

				}
			}

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		System.out.println("---------------------------------------------------------");

	}

}
