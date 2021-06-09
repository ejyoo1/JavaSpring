package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.PdsDAO;
import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.dto.PdsVO;

public class PdsServiceImpl implements PdsService {

	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;		
	}
	
	private PdsDAO pdsDAO;
	public void setNoticeDAO(PdsDAO pdsDAO) {
		this.pdsDAO = pdsDAO;
	}
	
	@Override
	public Map<String, Object> getPdsList(SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			List<PdsVO> pdsList = pdsDAO.selectSearchPdsList(session, cri);
			
			int totalCount = pdsDAO.selectSearchPdsListCount(session, cri);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(totalCount);
			
			dataMap.put("pdsList", pdsList);
			dataMap.put("pageMaker", pageMaker);
			
			return dataMap;
		} finally {
			session.close();
		}
	}

	@Override
	public PdsVO getPds(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		PdsVO pds = null;
		try {
			pds = pdsDAO.selectPdsByPno(session, pno);
			pdsDAO.increaseViewCount(session, pno);
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("DB 작업 도중 문제 발생함.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외처리 되지 않은 예외 발생함.");
		} finally {
			session.close();
		}
		return pds;
	}

	@Override
	public PdsVO getPdsForModify(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		PdsVO pds = null;
		try {
			pds = pdsDAO.selectPdsByPno(session, pno);
		} catch (SQLException e){
			e.printStackTrace();
	    	System.out.println("DB 작업 도중 문제 발생함.");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	System.out.println("예외처리 되지 않은 예외 발생함.");
	    } finally {
	    	session.close();
	    }
	    return pds;
	}

	@Override
	public void regist(PdsVO pds) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int pno = pdsDAO.selectPdsSequenceNextValue(session);
			pds.setPno(pno);
			pdsDAO.insertPds(session, pds);
		} finally {
			session.close();
		}
	}

	@Override
	public void modify(PdsVO pds) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			pdsDAO.updatePds(session, pds);
		} finally {
			session.close();
		}
	}

	@Override
	public void remove(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			pdsDAO.deletePds(session, pno);
		} finally {
			session.close();
		}
	}

}
