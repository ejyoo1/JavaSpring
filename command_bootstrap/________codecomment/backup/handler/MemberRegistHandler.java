package backup.handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.NotEnoughDataException;
import kr.or.ddit.exception.NotEnoughResultException;
import kr.or.ddit.service.MemberService;

public class MemberRegistHandler implements Handler {
	
	private static final Logger EXCEPTION_LOGGER = Logger.getLogger(MemberRegistHandler.class);
	
	private MemberService memberService;
    public void setMemberService(MemberService memberService) {
      this.memberService = memberService;
    }
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/common/result"; 
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		String script = "";
		try {
			if(id == "" || pwd == "" || phone == "" || email == "") {
				throw new NotEnoughDataException();
			} else if(id == null || pwd == null || phone == null || email == null) {
				throw new NotEnoughDataException();
			}
			
			MemberVO member = new MemberVO();
			member.setId(id);
			member.setPwd(pwd);
			member.setPhone(phone);
			member.setEmail(email);
		
			int cnt = memberService.registMember(member);
			if(cnt == 1) {
				script = "alert('회원가입이 정상적으로 이루어졌습니다.');"
						+ "location.href='/member/list.do';";
			} else {
				throw new NotEnoughResultException();
			}
		} catch (SQLException e) {
			script = "alert('문제가 발생하였습니다. 관리자에게 문의하여 주세요.');"
					+ "history.go(-1);";
			EXCEPTION_LOGGER.error(e.getMessage());
		} catch (NotEnoughDataException e) {
			script = "alert('입력값을 확인하여 주세요.');"
					+ "history.go(-1);";
			EXCEPTION_LOGGER.error(e.getMessage());
		} catch (NotEnoughResultException e) {
			script = "alert('회원 등록이 실패하였습니다. 관리자에게 문의하여 주세요.');"
					+ "history.go(-1);";
			EXCEPTION_LOGGER.error(e.getMessage());
		}
		
		request.setAttribute("script", script);
		
		return url;
	}
}
