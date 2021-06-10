package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.QnaDAO;
import kr.or.ddit.dao.ReplyQnaDAO;
import kr.or.ddit.dto.QnaVO;

public class QnaServiceImpl implements QnaService {
	
	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	private QnaDAO qnaDAO;
	public void setQnaDAO(QnaDAO qnaDAO) {
		this.qnaDAO = qnaDAO;
	}
	
	private ReplyQnaDAO replyQnaDAO;
	public void setReplyQnaDAO(ReplyQnaDAO replyQnaDAO) {
		this.replyQnaDAO = replyQnaDAO;
	}
	
	@Override
	public Map<String, Object> getQnaList(SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		Map<String, Object> dataMap = null;
		try {
			dataMap = new HashMap<String, Object>();
			
			List<QnaVO> qnaList = qnaDAO.selectSearchQnaList(session, cri);
			
			for(QnaVO qna : qnaList) {
				int replycnt = replyQnaDAO.countReply(session, qna.getQno());
				qna.setReplycnt(replycnt);
			}
			
			int totalCount = qnaDAO.selectSearchQnaListCount(session, cri);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(totalCount);
			
			dataMap.put("qnaList", qnaList);
			dataMap.put("pageMaker", pageMaker);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dataMap;
	}

	@Override
	public QnaVO getQna(int qno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		QnaVO qna = null;
		try {
			qna = qnaDAO.selectQnaByQno(session, qno);
			qnaDAO.increaseViewCount(session, qno);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return qna;
	}

	@Override
	public QnaVO getQnaForModify(int qno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		QnaVO qna = null;
		try {
			qna = qnaDAO.selectQnaByQno(session, qno);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return qna;
	}

	@Override
	public void regist(QnaVO qna) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		int qno = 0;
		try {
			qno = qnaDAO.selectQnaSequenceNextValue(session);
			qna.setQno(qno);
			qnaDAO.insertQna(session, qna);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void modify(QnaVO qna) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			qnaDAO.updateQna(session, qna);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void remove(int qno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			qnaDAO.deleteQna(session, qno);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
