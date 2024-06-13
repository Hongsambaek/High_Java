package HomeWork09.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import HomeWork09.util.JDBCUtil2;
import HomeWork09.util.JDBCUtil3;
import HomeWork09.vo.BoardVO;

public class BoardDaoImplwithJDBC implements IBoardDao {
	private static IBoardDao boardao = new BoardDaoImplwithJDBC();

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private BoardDaoImplwithJDBC() {

	}

	public static IBoardDao getInstance() {
		return boardao;
	}

	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " insert into jdbc_board(board_no, board_title, board_writer, board_date, board_content)\r\n"
					+ "values(BOARD_SEQ.NEXTVAL,?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setNString(1, bv.getBoardTitle());
			pstmt.setNString(2, bv.getBoardWriter());
			pstmt.setNString(3, bv.getBoardContent());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " update jdbc_board\r\n"
					+ "set BOARD_TITLE=?, BOARD_WRITER=?, BOARD_CONTENT=? where BOARD_NO = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardWriter());
			pstmt.setString(3, bv.getBoardContent());
			pstmt.setString(4, bv.getBoardNo());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납 close = finally 넣는이유 반드시 실행해야하기때문에
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<BoardVO> searchBoard(String boardWriter) {
		List<BoardVO> boardlist = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " select * from jdbc_board where BOARD_Writer = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, boardWriter);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String boardNo = rs.getNString("BOARD_NO");
				String boardName = rs.getNString("BOARD_TITLE");
				LocalDate boardDate = rs.getTimestamp("BOARD_DATE").toLocalDateTime().toLocalDate();
				String boardContent = rs.getNString("BOARD_CONTENT");

				BoardVO bv = new BoardVO();
				bv.setBoardNo(boardNo);
				bv.setBoardTitle(boardName);
				bv.setBoardWriter(boardWriter);
				bv.setRegDt(boardDate);
				bv.setBoardContent(boardContent);
				boardlist.add(bv);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return boardlist;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		List<BoardVO> boardlist = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil3.getConnection();

			stmt = conn.createStatement();

			rs = stmt.executeQuery(" select * from jdbc_board ");

			while (rs.next()) {
				String boardNo = rs.getNString("BOARD_NO");
				String boardName = rs.getNString("BOARD_TITLE");
				String boardWriter = rs.getNString("BOARD_WRITER");
				LocalDate boardDate = rs.getTimestamp("BOARD_DATE").toLocalDateTime().toLocalDate();
				String boardContent = rs.getNString("BOARD_CONTENT");

				BoardVO bv = new BoardVO();
				bv.setBoardNo(boardNo);
				bv.setBoardTitle(boardName);
				bv.setBoardWriter(boardWriter);
				bv.setRegDt(boardDate);
				bv.setBoardContent(boardContent);
				boardlist.add(bv);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return boardlist;
	}

	@Override
	public boolean checkBoard(String boardNo) {
		boolean isExist = false;
		try {
			conn = JDBCUtil3.getConnection();
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
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return isExist;
	}

	@Override
	public int deleteBoard(String boardNo) {
		int cnt = 0;
		try {
			conn = JDBCUtil2.getConnection();
			String sql = " delete from jdbc_board where BOARD_NO = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, boardNo);

			cnt = pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}
}