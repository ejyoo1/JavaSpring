package kr.or.ddit.handler.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.QnaService;

public class QnaRemoveHandler implements Handler {

	private QnaService qnaService;
	public void setQnaService(QnaService qnaService) {
		this.qnaService = qnaService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "qna/remove_success";
		
		int qno = Integer.parseInt(request.getParameter("qno"));
		
		qnaService.remove(qno);
		
		return url;
	}

}
