package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.QnaVO;

public class QnaDAOImpl implements QnaDAO{
	private static final Logger EXCEPTION_LOGGER = Logger.getLogger(QnaDAOImpl.class);
	private static final Logger INFO_LOGGER = Logger.getLogger(QnaDAOImpl.class);
	
	@Override
	public List<QnaVO> selectSearchQnaList(SqlSession session, SearchCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<QnaVO> qnaList = session.selectList("Qna-Mapper.selectSearchQnaList", cri, rowBounds);
		return qnaList;
	}
	@Override
	public int selectSearchQnaListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		int count = session.selectOne("Qna-Mapper.selectSearchQnaListCount", cri);
		return count;
	}
	@Override
	public QnaVO selectQnaByQno(SqlSession session, int qno) throws SQLException {
		QnaVO qna = session.selectOne("Qna-Mapper.selectQnaByQno", qno);
		return qna;
	}
	@Override
	public void increaseViewCount(SqlSession session, int qno) throws SQLException {
		session.update("Qna-Mapper.increaseViewCount", qno);
	}
	@Override
	public int selectQnaSequenceNextValue(SqlSession session) throws SQLException {
		int result = session.update("Qno-mapper.insertQna");
		return result;
	}
}
