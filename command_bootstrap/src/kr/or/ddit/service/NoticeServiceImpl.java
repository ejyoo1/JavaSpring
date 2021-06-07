package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.NoticeDAO;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.NoticeVO;

public class NoticeServiceImpl implements NoticeService{
	
	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	private NoticeDAO noticeDAO;
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	@Override
	public Map<String, Object> getNoticeList(SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			// 현재 Page 번호에 맞는 리스트를 perPageNum 개수만큼 가져오기.
			List<NoticeVO> noticeList = noticeDAO.selectSearchNoticeList(session, cri);
			
			// 전체 board 개수
			int totalCount = noticeDAO.selectSearchNoticeListCount(session, cri);
			
			// pageMaker 생성.
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(totalCount);
			
			dataMap.put("noticeList", noticeList);
			dataMap.put("pageMaker", pageMaker);
			
			return dataMap;
		} finally {
			session.close();
		}
	}

	@Override
	public NoticeVO getNotice(int nno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeVO notice = null;
	      try {
	    	  notice = noticeDAO.selectNoticeByNno(session, nno);
	          noticeDAO.increaseViewCount(session, nno);
	      } catch (SQLException e){
	    	  e.printStackTrace();
	    	  System.out.println("DB 작업 도중 문제 발생함.");
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println("예외처리 되지 않은 예외 발생함.");
	      } finally {
	    	  session.close();
	      }
	      return notice;
	}

	@Override
	public NoticeVO getNoticeForModify(int nno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeVO notice = null;
	      try {
	    	  notice = noticeDAO.selectNoticeByNno(session, nno);
	      } catch (SQLException e){
	    	  e.printStackTrace();
	    	  System.out.println("DB 작업 도중 문제 발생함.");
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println("예외처리 되지 않은 예외 발생함.");
	      } finally {
	    	  session.close();
	      }
	      return notice;
	}
}
