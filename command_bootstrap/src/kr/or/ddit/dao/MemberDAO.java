package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.Criteria;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;

public interface MemberDAO { // 서비스로두터 Session을 받아서 처리할 것을 생각. (
	
	// 회원 정보 조회(아이디)
	MemberVO selectMemberById(SqlSession session, String id) throws SQLException;
	
	// 회원 정보 조회(전체)
	List<MemberVO> selectMemberList(SqlSession session) throws SQLException;
	List<MemberVO> selectMemberList(SqlSession session, Criteria cri) throws SQLException; // 페이징
	List<MemberVO> selectMemberList(SqlSession session, SearchCriteria cri) throws SQLException; // 검색에 페이징
	
	// 검색 결과의 전체 리스트 개수
	int selectMemberListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// 회원 추가
	public void insertMember(SqlSession session, MemberVO member) throws SQLException;
}
