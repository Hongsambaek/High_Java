package HomeWork.dao;

import java.util.List;

import HomeWork.vo.BoardVO;

public interface IBoardDao {
	public int insertBoard(BoardVO bv);

	public int updateBoard(BoardVO bv);

	public boolean checkBoard(String boardNo);

	public int deleteBoard(String boardNo);

	public List<BoardVO> getAllBoard();

	public List<BoardVO> searchBoard(String boardWriter);

}
