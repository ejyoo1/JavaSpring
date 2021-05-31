package kr.or.ddit.handler.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.command.Criteria;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.MemberService;

public class MemberListPageHandler implements Handler {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) { 
		this.memberService = memberService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "member/list";
		
		// request에 페이지 번호와 perpage가 올것이므로 작업
		String pageParam = request.getParameter("page"); // 번호가 없으면 1페이지
		String perPageNumParam = request.getParameter("perPageNum");
		System.out.println("pageParam" + pageParam + ",perPageNumParam" + perPageNumParam);
	
		
		Criteria cri = new Criteria();
		cri.setPage(pageParam);
		cri.setPerPageNum(perPageNumParam);
		
		List<MemberVO> memberList = memberService.getMemberList(cri);
		
		request.setAttribute("memberList", memberList);
		return url; 
	}
}
