package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.PdsVO;

public class PdsDAOImpl implements PdsDAO {
	private static final Logger EXCEPTION_LOGGER = Logger.getLogger(PdsDAOImpl.class);
	private static final Logger INFO_LOGGER = Logger.getLogger(PdsDAOImpl.class);

	@Override
	public List<PdsVO> selectSearchPdsList(SqlSession session, SearchCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<PdsVO> pdsList = 
				session.selectList("Pds-Mapper.selectSearchPdsList", cri, rowBounds);
		
		return pdsList;
	}

	@Override
	public int selectSearchPdsListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		int count = session.selectOne("Pds-Mapper.selectSearchPdsListCount", cri);
		return count;
	}

	@Override
	public PdsVO selectPdsByPno(SqlSession session, int pno) throws SQLException {
		PdsVO pds = session.selectOne("Pds-Mapper.selectPdsBypno", pno);
		return pds;
	}

	@Override
	public void increaseViewCount(SqlSession session, int pno) throws SQLException {
		session.update("Pds-Mapper.increaseViewCount", pno);
	}

	@Override
	public int selectPdsSequenceNextValue(SqlSession session) throws SQLException {
		int seq_num = session.selectOne("Pds-Mapper.selectPdsSequenceNextValue");
		return seq_num;
	}

	@Override
	public void insertPds(SqlSession session, PdsVO pds) throws SQLException {
		session.update("Pds-Mapper.insertPds", pds);
	}

	@Override
	public void updatePds(SqlSession session, PdsVO pds) throws SQLException {
		session.update("Pds-Mapper.updatePds", pds);
	}

	@Override
	public void deletePds(SqlSession session, int pno) throws SQLException {
		session.update("Pds-Mapper.deletePds", pno);
	}

}
