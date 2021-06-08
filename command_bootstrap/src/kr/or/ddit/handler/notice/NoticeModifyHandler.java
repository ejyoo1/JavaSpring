package kr.or.ddit.handler.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.XSSResolver;
import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.NoticeService;

public class NoticeModifyHandler implements Handler {

	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "redirect:detail.do?from=modify&nno="+request.getParameter("nno"); // 조회수 증가되지 않게 하기 위해 from, nno 추가 
		
		XSSResolver.parseXSS(request);
		
		// 파라미터 저장
		int nno = Integer.parseInt(request.getParameter("nno"));
		String title = (String)request.getAttribute("XSStitle");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		NoticeVO notice = new NoticeVO();
		notice.setNno(nno);
		notice.setTitle(title);
		notice.setContent(content);
		notice.setWriter(writer);
		
		noticeService.modify(notice);
		
		return url;
	}

}
