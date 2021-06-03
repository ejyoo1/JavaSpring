package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import kr.or.ddit.dto.MenuVO;

public class MenuDAOImpl implements MenuDAO{
	private static final Logger EXCEPTION_LOGGER = Logger.getLogger(MemberDAOImpl.class);
	private static final Logger INFO_LOGGER = Logger.getLogger(MemberDAOImpl.class);

	@Override
	public List<MenuVO> selectMainMenu(SqlSession session) throws SQLException {
		INFO_LOGGER.info("■■■MemberDAOImpl init■■■");
		List<MenuVO> menuList = session.selectList("Menu-Mapper.selectMainMenu");
		return menuList;
	}

	@Override
	public List<MenuVO> selectSubMenu(SqlSession session, String mCode) throws SQLException {
		INFO_LOGGER.info("■■■MemberDAOImpl init■■■");
		List<MenuVO> menuList = session.selectList("Menu-Mapper.selectSubMenu", mCode);
		return menuList;
	}

	@Override
	public MenuVO selectMenuByMcode(SqlSession session, String mCode) throws SQLException {
		INFO_LOGGER.info("■■■MemberDAOImpl init■■■");
		MenuVO menu = session.selectOne("Menu-Mapper.selectMenuByMcode", mCode);
		return menu;
	}

	@Override
	public MenuVO selectMenuByMname(SqlSession session, String mName) throws SQLException {
		MenuVO menu = session.selectOne("Menu-Mapper.selectMenuByMname", mName);
		return menu;
	}

}
