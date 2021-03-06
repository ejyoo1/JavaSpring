package backup.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.dao.MemberDAO;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotEnoughResultException;
import kr.or.ddit.exception.NotFoundIDException;

public class MemberServiceImpl implements MemberService {
   
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
		List<MemberVO> memberList = null;
		
		try {
			memberList = memberDAO.selectMember(session);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 작업 도중 문제 발생함.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외처리 되지 않은 예외 발생함.");
		} finally {
	    	  session.close();
	    }
		return memberList;
	}

	@Override
	public int modifyMember(MemberVO member) throws SQLException, NotEnoughResultException {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = 0;
		
		try {
		  cnt = memberDAO.updateMember(session, member);
		  if(cnt == 0) {
			  throw new NotEnoughResultException();
		  }
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 작업 도중 문제 발생함.");
		} catch (NotEnoughResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외처리 되지 않은 예외 발생함.");
		} finally {
	    	  session.close();
	    }
		return cnt;
	}

	@Override
	public int removeMember(String id) throws SQLException, NotFoundIDException {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = 0;
		
	      try {
	    	  MemberVO member = memberDAO.selectMemberById(session, id);
	    	  if (member == null) 
	    		  throw new NotFoundIDException();
	    	  cnt = memberDAO.deleteMember(session, id);
	      } catch (SQLException e){
	    	  e.printStackTrace();
	    	  System.out.println("DB 작업 도중 문제 발생함.");
	      }	catch (NotFoundIDException e) { 
	    	  e.printStackTrace();
	      } catch (Exception e){
	    	  e.printStackTrace();
	    	  System.out.println("처리되지 않은 예외 발생함.");
	      } finally {
	    	  session.close();
	      }
	      return cnt;
	}

	@Override
	public int registMember(MemberVO member) throws SQLException, NotEnoughResultException {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = 0;
		
		try {
		  cnt = memberDAO.insertMember(session, member);
		  if(cnt == 0) {
			  throw new NotEnoughResultException();
		  }
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 작업 도중 문제 발생함.");
		} catch (NotEnoughResultException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외처리 되지 않은 예외 발생함.");
		} finally {
	    	  session.close();
	    }
		return cnt;
	}

}