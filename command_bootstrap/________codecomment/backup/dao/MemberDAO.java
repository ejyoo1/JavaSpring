package backup.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.NotEnoughResultException;

public interface MemberDAO { // 서비스로두터 Session을 받아서 처리할 것을 생각. (
	
	// 회원 정보 조회(아이디)
	MemberVO selectMemberById(SqlSession session, String id) throws SQLException;
	
	// 회원 정보 조회(전체)
	List<MemberVO> selectMember(SqlSession session) throws SQLException;
	
	// 회원 정보 수정
	int updateMember(SqlSession session, MemberVO member) throws SQLException;
	
	// 회원 정보 삭제 
	int deleteMember(SqlSession session, String id) throws SQLException;
	
	// 회원 정보 등록
	int insertMember(SqlSession session, MemberVO member) throws SQLException;
}
