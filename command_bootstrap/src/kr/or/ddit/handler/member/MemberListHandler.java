package kr.or.ddit.handler.member;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.NotEnoughResultException;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.MemberService;

public class MemberListHandler implements Handler {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) { // setMethod 네이밍 (변수명 첫글자 대문자)
		this.memberService = memberService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 작성 시 어떻게 데이터를 받을것인가, 받은것을 어떻게 처리할 것인가, 예외가 발생하면 어떻게 할 것인가, 예외발생시 다음 작업은 어떻게되는가 생각
		String url = "member/list"; // view Resolver 사용
		
		List<MemberVO> memberList = memberService.getMemberList(); // JSP forward 시 넘겨줄 데이터(request에 심어서 보내줄 것) // Exception 발생 위험 있음. (에러 페이지 이동은 FrontServlet이 처리함)
		
		request.setAttribute("memberList", memberList);
		return url; // viewresolver에 리턴
	}

}
