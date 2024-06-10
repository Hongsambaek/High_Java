package HomeWork;

import java.util.List;
import java.util.Scanner;

import HomeWork.service.BoardServiceImpl;
import HomeWork.service.IBoardService;
import HomeWork.vo.BoardVO;

public class HomeWork09 {

	private Scanner scan;
	private IBoardService boardService;

	public HomeWork09() {
		scan = new Scanner(System.in);
		boardService = BoardServiceImpl.getInstance();
	}

	public static void main(String[] args) {
		HomeWork09 board = new HomeWork09();
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
		System.out.println("번호\t글 제목\t작성자 이름\t작성 날짜\t\t내용");
		System.out.println("---------------------------------------------------");

		List<BoardVO> boardlist = boardService.searchBoard(boardWriter);

		if (boardlist.size() == 0) {
			System.out.println("게시판이 없습니다.");
		} else {
			for (BoardVO bv : boardlist) {
				System.out.println(bv.getBoardNo() + "\t" + bv.getBoardTitle() + "\t" + bv.getBoardWriter() + "\t"
						+ bv.getRegDt() + "\t" + bv.getBoardContent());
			}
		}
		System.out.println("---------------------------------------------------");
		System.out.println("출력 끝");
	}

	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 게시판 번호를 입력해 주세요.");
		System.out.print("게시판 번호 >> ");
		String boardNo = scan.next();

		//////////////////////////////////////////////////////
		int cnt = boardService.removeBoard(boardNo);
		if (cnt > 0) {
			System.out.println(boardNo + "번의 글을 삭제하였습니다");
		} else {
			System.out.println(boardNo + "번의 글 삭제 실패");
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

			isExist = boardService.checkBoard(boardNo);

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

		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		bv.setBoardNo(boardNo);

		int cnt = boardService.modifyBoard(bv);

		if (cnt > 0) {
			System.out.println(boardWriter + "님의 [" + boardTitle + "] 작성 완료");
		} else {
			System.out.println(boardWriter + "의 글 작성 실패");
		}
	}

	private void displayAllBoard() {
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println("번호\t글 제목\t작성자 이름\t작성 날짜\t\t내용");
		System.out.println("---------------------------------------------------");

		List<BoardVO> boardlist = boardService.getTotalBoard();

		if (boardlist.size() == 0) {
			System.out.println("게시판이 없습니다.");
		} else {
			for (BoardVO bv : boardlist) {
				System.out.println(bv.getBoardNo() + "\t" + bv.getBoardTitle() + "\t" + bv.getBoardWriter() + "\t"
						+ bv.getRegDt() + "\t" + bv.getBoardContent());
			}
		}
		System.out.println("---------------------------------------------------");
		System.out.println("출력 끝");
	}

	private void insertBoard() {

		System.out.print("게시판 이름 >> ");
		String boardTitle = scan.next();
		scan.nextLine();

		System.out.print("작성자 이름 >> ");
		String boardWriter = scan.next();

		scan.nextLine();

		System.out.print("게시판 내용 >> ");
		String boardContent = scan.nextLine();

		///////////////////////////////

		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		int cnt = boardService.registerBoard(bv);
		if (cnt > 0) {
			System.out.println(boardWriter + "님의 [" + boardTitle + "] 작성 완료");
		} else {
			System.out.println(boardWriter + "의 글 작성 실패");
		}
	}
}
