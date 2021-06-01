package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.command.Criteria;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotFoundIDException;

public interface MemberService {
	
	// 로그인
	void login(String id, String pwd) throws SQLException, NotFoundIDException,
											InvalidPasswordException;
	
	// 회원 정보 조회
	MemberVO getMember(String id) throws SQLException;
	
	// 회원 목록 조회(오버로딩)
	List<MemberVO> getMemberList() throws SQLException; // 전체 목록
	List<MemberVO> getMemberList(Criteria cri) throws SQLException; // 페이지 목록
	Map<String,Object> getMemberList(SearchCriteria cri) throws SQLException; // 검색 목록
	
}
