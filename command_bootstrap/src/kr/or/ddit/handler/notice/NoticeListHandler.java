package kr.or.ddit.handler.notice;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.NoticeService;

//cri 만들고 service에 인가
// front에게 view 리턴
// request 데이터 맵 심기
public class NoticeListHandler implements Handler {
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "notice/list";
		
		String page = request.getParameter("page");
		String perPageNum = request.getParameter("perPageNum");
		String searchType = request.getParameter("searchType");
		System.out.println("searchType : " + searchType);
		String keyword = request.getParameter("keyword");
		System.out.println("keyword : " + keyword);
		
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		cri.setSearchType(searchType);
		cri.setKeyword(keyword);
		
		Map<String,Object> dataMap = null;
		
		try {
			dataMap = noticeService.getNoticeList(cri);
			request.setAttribute("dataMap", dataMap);
			//request.setAttribute("noticeList", dataMap.get("noticeList"));
			//request.setAttribute("pageMaker", dataMap.get("pageMaker"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			// url = null;
		}
		
		return url;
	}

}
