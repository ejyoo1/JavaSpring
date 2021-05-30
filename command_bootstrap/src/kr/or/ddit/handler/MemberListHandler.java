package kr.or.ddit.handler;

import java.io.PrintWriter;
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
		String action = request.getParameter("action");
		List<MemberVO> memberList = null;
		MemberVO member = null;
		
		try {
			if("search".equals(action)) {
				String id = request.getParameter("id");
				member = memberService.getMember(id);
				PrintWriter out = response.getWriter();
				if(member != null) {
					out.print("1");
				}
			} else {
				memberList = memberService.getMemberList();
				request.setAttribute("memberList", memberList);
				return url;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외처리 되지 않은 예외 발생함.");
		}
		return null;
	}

}
