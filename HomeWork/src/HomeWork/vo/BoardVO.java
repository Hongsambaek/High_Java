package HomeWork.vo;

import java.time.LocalDate;

public class BoardVO {

	private String boardTitle;
	private String boardWriter;
	private LocalDate boardDate;
	private String boardContent;
	private int boardNo;

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public LocalDate getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(LocalDate boardDate) {
		this.boardDate = boardDate;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "BoardVO [boardTitle=" + boardTitle + ", boardWriter=" + boardWriter + ", boardDate=" + boardDate
				+ ", boardContent=" + boardContent + ", boardNo=" + boardNo + "]";
	}

}
