package backup.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public MemberVO selectMemberById(SqlSession session, String id) throws SQLException {
		MemberVO member = session.selectOne("Member-Mapper.selectMemberById",id);
		
		return member;
	}

	@Override
	public List<MemberVO> selectMember(SqlSession session) throws SQLException {
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList");
		
		return memberList;
	}

	@Override
	public int updateMember(SqlSession session, MemberVO member) throws SQLException {
		int cnt = 0;
		
		cnt = session.update("Member-Mapper.updateMember", member);
		
		return cnt;
	}
	
	public int deleteMember(SqlSession session, String id) throws SQLException {
		int cnt = 0;
		
		cnt = session.delete("Member-Mapper.deleteMemberById", id);
		
		return cnt;
	}

	@Override
	public int insertMember(SqlSession session, MemberVO member) throws SQLException {
		int cnt = 0;
		
		cnt = session.insert("Member-Mapper.insertMember", member);
		
		return cnt;
	}
	

}
