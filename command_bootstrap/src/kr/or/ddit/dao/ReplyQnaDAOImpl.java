package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.ReplyQnaVO;

public class ReplyQnaDAOImpl implements ReplyQnaDAO{

	@Override
	public void insertReply(SqlSession session, ReplyQnaVO reply) throws SQLException {
		session.update("ReplyQna-Mapper.insertReply", reply);
	}

	@Override
	public void updateReply(SqlSession session, ReplyQnaVO reply) throws SQLException {
		session.update("ReplyQna-Mapper.updateReply", reply);
	}

	@Override
	public void deleteReply(SqlSession session, int rno) throws SQLException {
		session.update("ReplyQna-Mapper.deleteReply", rno);
	}

	@Override
	public List<ReplyQnaVO> selectReplyListPage(SqlSession session, int qno, SearchCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<ReplyQnaVO> replyList = session.selectList("ReplyQna-Mapper.selectReplyList", qno, rowBounds);
		return replyList;
	}

	@Override
	public int countReply(SqlSession session, int qno) throws SQLException {
		int count = session.selectOne("ReplyQna-Mapper.countReply", qno);
		return count;
	}
	
	@Override
	public int selectReplySeqNextValue(SqlSession session) throws SQLException {
		int rno = (Integer) session.selectOne("ReplyQna-Mapper.selectReplySeqNextValue");
		return rno;
	}

}
