package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.QnaVO;

public interface QnaDAO {
	
	List<QnaVO> selectSearchQnaList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	int selectSearchQnaListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	QnaVO selectQnaByQno(SqlSession session, int qno) throws SQLException;
	
	void increaseViewCount(SqlSession session, int qno) throws SQLException;
	
	int selectQnaSequenceNextValue(SqlSession session) throws SQLException;
}
