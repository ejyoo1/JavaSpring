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

	@Override
	public Map<String, Object> getQnaList(SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			List<QnaVO> qnaList = qnaDAO.selectSearchQnaList(session, cri);
			int totalCount = qnaDAO.selectSearchQnaListCount(session, cri);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(totalCount);
			
			dataMap.put("qnaList", qnaList);
			dataMap.put("pageMaker", pageMaker);
			
			return dataMap;
		} finally {
			session.close();
		}
	}

	@Override
	public QnaVO getQna(int qno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		QnaVO qna = null;
		try {
			qna = qnaDAO.selectQnaByQno(session, qno);
			qnaDAO.increaseViewCount(session, qno);
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
		} finally {
			session.close();
		}
		return qna;
	}

}
