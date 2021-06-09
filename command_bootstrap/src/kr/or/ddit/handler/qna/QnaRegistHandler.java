package kr.or.ddit.handler.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.XSSResolver;
import kr.or.ddit.dto.QnaVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.QnaService;

public class QnaRegistHandler implements Handler{
	private QnaService qnaService;
	public void setQnaService(QnaService qnaService) {
		this.qnaService = qnaService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "qna/regist_success";
		
		XSSResolver.parseXSS(request);
		
		String title = (String) request.getAttribute("XSStitle");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		QnaVO qna = new QnaVO();
		qna.setTitle(title);
		qna.setContent(content);
		qna.setWriter(writer);
		
		qnaService.regist(qna);
		
		return url;
	}
}
