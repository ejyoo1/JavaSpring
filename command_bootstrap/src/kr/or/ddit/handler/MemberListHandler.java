package kr.or.ddit.handler;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.NotEnoughResultException;
import kr.or.ddit.service.MemberService;

public class MemberListHandler implements Handler {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "member/memberList";
		List<MemberVO> memberList = null;
		
		try {
			memberList = memberService.getMemberList();
			request.setAttribute("memberList", memberList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NotEnoughResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외처리 되지 않은 예외 발생함.");
		}
		return url;
	}

}