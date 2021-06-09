package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.ReplyQnaVO;
import kr.or.ddit.dto.ReplyVO;

public interface ReplyQnaDAO {
	void insertReply(SqlSession session, ReplyQnaVO reply) throws SQLException;
	void updateReply(SqlSession session, ReplyQnaVO reply) throws SQLException;
	void deleteReply(SqlSession session, int rno) throws SQLException;
	
	int selectReplySeqNextValue(SqlSession session) throws SQLException;
	List<ReplyQnaVO> selectReplyListPage(SqlSession session, int qno, SearchCriteria cri) throws SQLException;
	int countReply(SqlSession session, int qno) throws SQLException;
}
