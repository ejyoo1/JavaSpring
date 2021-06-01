package kr.or.ddit.handler.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.MemberService;

public class MemberListSearchPageHandler implements Handler {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "member/list";
		
		String page = request.getParameter("page");
		String perPageNum = request.getParameter("perPageNum");
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		cri.setSearchType(searchType);
		cri.setKeyword(keyword);
		
		Map<String, Object> dataMap = memberService.getMemberList(cri);
		
		request.setAttribute("memberList", dataMap.get("memberList")); // (List<MemberVO>) dataMap.get("memberList") 해야하지만 EL문에서는 자동으로 캐스팅 하기때문에 캐스팅 생략
		request.setAttribute("pageMaker", dataMap.get("pageMaker"));// 상태 유지 위한 인자 = 페이지 상태값 넘김
		
		return url;
	}
}
