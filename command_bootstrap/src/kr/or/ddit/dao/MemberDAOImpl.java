package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import kr.or.ddit.command.Criteria;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.controller.ViewResolver;
import kr.or.ddit.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	private static final Logger EXCEPTION_LOGGER = Logger.getLogger(MemberDAOImpl.class);
	private static final Logger INFO_LOGGER = Logger.getLogger(MemberDAOImpl.class);
	
	
	@Override
	public MemberVO selectMemberById(SqlSession session, String id) throws SQLException {
		INFO_LOGGER.info("■■■MemberDAOImpl init■■■");
		MemberVO member = null;
		try{
			member = session.selectOne("Member-Mapper.selectMemberById",id);
		} catch (Exception e) {
			e.printStackTrace();
			EXCEPTION_LOGGER.error("selectMemberById 수행도중 Error 발생함");
		}
		return member;
	}
	
	@Override
	public List<MemberVO> selectMemberList(SqlSession session) throws SQLException {
		INFO_LOGGER.info("■■■MemberDAOImpl init■■■");
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList");
		return memberList;
	}

	@Override
	public List<MemberVO> selectMemberList(SqlSession session, Criteria cri) throws SQLException { // 오버로딩(확장)
		INFO_LOGGER.info("■■■MemberDAOImpl init■■■");
		int offset = cri.getStartRowNum(); // 시작할 Row 번호
		int limit = cri.getPerPageNum(); // 한페이지에 보여줄 개수
		RowBounds rowBounds = new RowBounds(offset,limit); // 몇번부터 몇번까지 짜를 값 세팅
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList", null, rowBounds);
		return memberList;
	}

	@Override
	public List<MemberVO> selectMemberList(SqlSession session, SearchCriteria cri) throws SQLException {
		INFO_LOGGER.info("■■■MemberDAOImpl init■■■");
		int offset = cri.getStartRowNum(); // 시작할 Row 번호
		int limit = cri.getPerPageNum(); // 한페이지에 보여줄 개수
		RowBounds rowBounds = new RowBounds(offset,limit); // 몇번부터 몇번까지 짜를 값 세팅
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectSearchMemberList", cri, rowBounds);
		return memberList;
	}

	@Override
	public int selectMemberListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		int count = 0;
		count = session.selectOne("Member-Mapper.selectSearchMemberListCount",cri);
		return count;
	}

	@Override
	public void insertMember(SqlSession session, MemberVO member) throws SQLException {
		session.update("Member-Mapper.insertMember",member);
	}

	@Override
	public void updateMember(SqlSession session, MemberVO member) throws SQLException {
		session.update("Member-Mapper.updateMember",member);
	}

	@Override
	public void deleteMember(SqlSession session, String id) throws SQLException {
		session.update("Member-Mapper.deleteMember",id);
	}

	@Override
	public void disabledMember(SqlSession session, String id) throws SQLException {
		session.update("Member-Mapper.disabledMember",id);
	}

	@Override
	public void enabledMember(SqlSession session, String id) throws SQLException {
		session.update("Member-Mapper.enabledMember",id);
	}
}
