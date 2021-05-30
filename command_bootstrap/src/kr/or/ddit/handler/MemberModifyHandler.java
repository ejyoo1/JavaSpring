package kr.or.ddit.handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.NotEnoughDataException;
import kr.or.ddit.exception.NotEnoughResultException;
import kr.or.ddit.service.MemberService;

public class MemberModifyHandler implements Handler {
	private static final Logger EXCEPTION_LOGGER = Logger.getLogger(MemberModifyHandler.class);
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/common/result";
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
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
			
			int cnt = memberService.modifyMember(member);
			if(cnt == 0) {
				throw new NotEnoughResultException();
			} else if(cnt > 0) {
				script = "alert('수정되었습니다.');"
						+ "location.href='/member/list.do';";
			}
		} catch (SQLException e) {
			script = "alert('문제가 발생하였습니다. 관리자에게 문의하여 주세요.');"
					+ "history.go(-1);";
			EXCEPTION_LOGGER.error(e.getMessage());
		} catch (NotEnoughDataException e) {
			script = "alert('입력값을 확인하여주세요.');"
					+ "history.go(-1);";
			EXCEPTION_LOGGER.error(e.getMessage());
		} catch (NotEnoughResultException e) {
			script = "alert('회원 수정이 실패하였습니다. 관리자에게 문의하여 주세요.');"
					+ "history.go(-1);";
			EXCEPTION_LOGGER.error(e.getMessage());
		}
		request.setAttribute("script", script);
		
		return url;
	}
}
