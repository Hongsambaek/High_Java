package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해 서비스에
 * 전달하기위한 DAO Interface
 * @author PC-27
 *
 */
public interface IMemberService {
	
	/**
	 * MemberVo에 담겨진 회원정보를 DB에 Insert하기 위한 메소드
	 * @param mv 회원정보를 담은 MemberVO 객체
	 * @return DB작업이 성공하면 1, 실패하면 0 반환됨.
	 */
	public int registerMember(MemberVO mv);
	
	
	
	
	/**
	 * MemberVo에 담겨진 회원정보를 DB에 Update하기 위한 메소드
	 * @param mv 회원정보를 담은 MemberVO 객체
	 * @return DB작업이 성공하면 1, 실패하면 0 반환됨.
	 */
	public int modifyMember(MemberVO mv);
	
	
	/**
	 * 해당 회원의 존재여부를 확인하기 위한 메소드
	 * @param memId 존재여부 확인하기 위한 회원ID
	 * @return 해당 회원이 존재하면 true, 존재하지 않으면 false 리턴함.
	 */
	public boolean checkMember(String memId);
	
	
	
	/**
	 * 해당 회원정보를 삭제하기 위한 메소드
	 * @param memId 삭제하고자 하는 회원 ID
	 * @return 삭제처리가 성공하면 1, 실패하면 0 반환됨
	 */
	public int removeMember(String memId);
	
	
	
	/**
	 * 모든 회원정보를 가져오기 위한 메소드
	 * @return 모든 회원정보를 담은 List객체
	 */
	public List<MemberVO> getTotalMember();
}
   