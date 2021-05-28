package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.MemberVO;

public interface MemberDAO { // 서비스로두터 Session을 받아서 처리할 것을 생각. (
	
	// 회원 정보 조회(아이디)
	MemberVO selectMemberById(SqlSession session, String id) throws SQLException;
	
	// 회원 정보 조회(전체)
	List<MemberVO> selectMember(SqlSession session) throws SQLException;
	
}
