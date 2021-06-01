package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import kr.or.ddit.command.Criteria;
import kr.or.ddit.dto.NoticeVO;

public class NoticeDAOImpl implements NoticeDAO{
	private static final Logger EXCEPTION_LOGGER = Logger.getLogger(MemberDAOImpl.class);
	private static final Logger INFO_LOGGER = Logger.getLogger(MemberDAOImpl.class);
	
	@Override
	public List<NoticeVO> selectNoticeList(SqlSession session, String nno) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
