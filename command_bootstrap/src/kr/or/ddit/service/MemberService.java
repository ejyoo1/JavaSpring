package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotEnoughResultException;
import kr.or.ddit.exception.NotFoundIDException;

public interface MemberService {
	
	// 로그인
	void login(String id, String pwd) throws SQLException, NotFoundIDException,
											InvalidPasswordException;
	
	// 회원 정보 조회
	MemberVO getMember(String id) throws SQLException;
	
	// 회원 목록 조회
	List<MemberVO> getMemberList() throws SQLException;
	
	// 회원 정보 수정
	int modifyMember(MemberVO member) throws SQLException, NotEnoughResultException;
	
	// 회원 정보 삭제
	int removeMember(String id) throws SQLException, NotFoundIDException;
	
	// 회원 정보 추가
	int registMember(MemberVO member) throws SQLException, NotEnoughResultException;
}
