package kr.or.ddit.handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotFoundIDException;
import kr.or.ddit.service.MemberService;

public class LoginHandler implements Handler {

   private MemberService memberService;
   public void setMemberService(MemberService memberService) {
      this.memberService = memberService;
   }

   @Override
   public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      String url="redirect:index.do";
      
      //로그인처리
      String id=request.getParameter("id");
      String pwd=request.getParameter("pwd");
      
      HttpSession session = request.getSession();
      
      try {
         memberService.login(id, pwd);
         session.setAttribute("loginUser", memberService.getMember(id));
         session.setMaxInactiveInterval(6*60);
      } catch (SQLException e) {
         e.printStackTrace();
         throw e;
      } catch (NotFoundIDException | InvalidPasswordException e) {
         url = "redirect:";
         //redirect나 forwarding을 하면 아이디 패스워드 비밀번호 안남아 있다.history back을 해야 함.
         
      } catch (Exception e) {
    	  e.printStackTrace();
    	  throw e;
      }
      return url;
   }

}