package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import kr.or.ddit.command.Criteria;
import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.MemberDAO;
import kr.or.ddit.dao.MemberDAOImpl;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotFoundIDException;

public class MemberServiceImpl implements MemberService {
    private static final Logger INFO_LOGGER = Logger.getLogger(MemberDAOImpl.class);
   
    private SqlSessionFactory sqlSessionFactory;
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
       this.sqlSessionFactory = sqlSessionFactory;
    }
   
    private MemberDAO memberDAO;
    public void setMemberDAO(MemberDAO memberDAO) {
       this.memberDAO = memberDAO;
    }
   
   @Override
   public void login(String id, String pwd) throws SQLException, NotFoundIDException, InvalidPasswordException {
      SqlSession session = sqlSessionFactory.openSession();
      try {
    	  MemberVO member = memberDAO.selectMemberById(session, id);
    	  if (member == null) 
    		  throw new NotFoundIDException();
    	  if (!pwd.equals(member.getPwd()))
    		  throw new InvalidPasswordException();
      } catch (SQLException e){
    	  e.printStackTrace();
    	  System.out.println("DB 작업 도중 문제 발생함.");
      }	catch (NotFoundIDException e) { 
    	  e.printStackTrace();
      } catch (InvalidPasswordException e) {
    	  e.printStackTrace();
      } catch (Exception e){
    	  e.printStackTrace();
    	  System.out.println("처리되지 않은 예외 발생함.");
      } finally {
    	  session.close();
      }
   }

   @Override
   public MemberVO getMember(String id) throws SQLException {
      SqlSession session = sqlSessionFactory.openSession();
      MemberVO member = null;
      try {
    	 member = memberDAO.selectMemberById(session, id);
         
      } catch (SQLException e){
    	  e.printStackTrace();
    	  System.out.println("DB 작업 도중 문제 발생함.");
      } catch (Exception e) {
    	  e.printStackTrace();
    	  System.out.println("예외처리 되지 않은 예외 발생함.");
      } finally {
    	  session.close();
      }
      return member;
   }

	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			List<MemberVO> memberList = memberDAO.selectMemberList(session);
			return memberList;
	    } finally {
	    	session.close();
	    }
	}

	@Override
	public List<MemberVO> getMemberList(Criteria cri) throws SQLException { // 오버로딩(확장)
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			List<MemberVO> memberList = memberDAO.selectMemberList(session, cri);
			return memberList;
	    } finally {
	    	session.close();
	    }
	}

	@Override
	public Map<String,Object> getMemberList(SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		try {
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(memberDAO.selectMemberListCount(session, cri));
			
			List<MemberVO> memberList = memberDAO.selectMemberList(session, cri);
			
			dataMap.put("memberList", memberList);
			dataMap.put("pageMaker", pageMaker);
			
			return dataMap;
	    } finally {
	    	session.close();
	    }
	}
}