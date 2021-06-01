package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.NoticeVO;

public interface NoticeDAO {
	
	// 공지사항 조회
	List<NoticeVO> selectNoticeList(SqlSession session, String nno) throws SQLException;
	
}
