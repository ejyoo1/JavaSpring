package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.ReplyQnaDAO;
import kr.or.ddit.dto.ReplyVO;

public class ReplyQnaServiceImpl implements ReplyQnaService {
	
	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	private ReplyQnaDAO replyQnaDAO;
	public void setReplyQnaDAO(ReplyQnaDAO replyQnaDAO) {
		this.replyQnaDAO = replyQnaDAO;
	}

	@Override
	public Map<String, Object> getReplyList(int qno, SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			List<ReplyVO> replyList = replyQnaDAO.selectReplyListPage(session, qno, cri);
			
			int count = replyQnaDAO.countReply(session, qno);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(count);
			
			dataMap.put("replyList", replyList);
			dataMap.put("pageMaker",  pageMaker);
			
			return dataMap;
		} finally {
			session.close();
		}
	}

	@Override
	public int getReplyListCount(int qno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int count = replyQnaDAO.countReply(session, qno);
			return count;
		} finally {
			session.close();
		}
	}

	@Override
	public void registReply(ReplyVO reply) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int rno = replyQnaDAO.selectReplySeqNextValue(session);
			reply.setRno(rno);
			
			replyQnaDAO.insertReply(session, reply);
		} finally {
			session.close();
		}
	}

	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			replyQnaDAO.updateReply(session, reply);
		} finally {
			session.close();
		}
	}

	@Override
	public void removeReply(int rno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			replyQnaDAO.deleteReply(session, rno);
		} finally {
			session.close();
		}
	}
	
}
