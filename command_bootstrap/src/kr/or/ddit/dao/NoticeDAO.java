package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.NoticeVO;

public interface NoticeDAO {
	
	// 공지사항 조회
	List<NoticeVO> selectSearchNoticeList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	int selectSearchNoticeListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	NoticeVO selectNoticeByNno(SqlSession session, int nno) throws SQLException;
	
	// viewcnt 증가
	void increaseViewCount(SqlSession session, int nno) throws SQLException;
	
	// Notice seq.nextval 가져오기
	int selectNoticeSequenceNextValue(SqlSession session) throws SQLException;
}
