package kr.or.ddit.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.dto.MenuVO;

public class TestOracleMyBatisSqlSessionFactory {
	
	private SqlSessionFactory factory = new OracleMyBatisSqlSessionFactory(); // 테스트 할 때 래퍼런스는 가지고 있어야 하므로 밖에 생성
	private SqlSession session;
	
	@Before
	public void openSession() {
		session = factory.openSession(); // 테스트 할 때, 객체는 계속 새로 생성해야 하므로 안에 생성
	}
	
	@Test
	public void testSqlSession() throws Exception{
		Assert.assertNotNull(session.getConnection());
	}
	
	@Test
	public void testSQL() { // 쿼리 수마다 해야함.
		MenuVO menu = session.selectOne("Menu-Mapper.selectMenuByMcode","M010100");
		Assert.assertEquals("회원목록", menu.getMname());
	}
	
	@After
	public void closeSession() {
		session.close();
	}
}
