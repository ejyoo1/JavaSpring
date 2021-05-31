package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.Criteria;
import kr.or.ddit.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public MemberVO selectMemberById(SqlSession session, String id) throws SQLException {
		MemberVO member = session.selectOne("Member-Mapper.selectMemberById",id);
		
		return member;
	}
	
	@Override
	public List<MemberVO> selectMemberList(SqlSession session) throws SQLException {
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList");
		return memberList;
	}

	@Override
	public List<MemberVO> selectMemberList(SqlSession session, Criteria cri) throws SQLException { // 오버로딩(확장)
		int offset = cri.getStartRowNum(); // 시작할 Row 번호
		int limit = cri.getPerPageNum(); // 한페이지에 보여줄 개수
		RowBounds rowBounds = new RowBounds(offset,limit); // 몇번부터 몇번까지 짜를 값 세팅
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList", null, rowBounds);
		return memberList;
	}
	

	
	

}
