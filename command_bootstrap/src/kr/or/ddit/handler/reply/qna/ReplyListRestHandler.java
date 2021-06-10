package kr.or.ddit.handler.reply.qna;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.controller.JSONResolver;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.ReplyQnaService;

public class ReplyListRestHandler implements Handler {
	
	private ReplyQnaService replyQnaService;
	public void setReplyQnaService(ReplyQnaService replyQnaService) {
		this.replyQnaService = replyQnaService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		int qno = Integer.parseInt(request.getParameter("qno"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		SearchCriteria cri = new SearchCriteria(); // Criteria해도 됨. 검색이 없음.
		cri.setPage(page);
		
		Map<String, Object> dataMap = replyQnaService.getReplyList(qno, cri);
		JSONResolver.view(response,dataMap);
		
		return url;
	} // 화면 전환용 Handler가 아님
	
}
