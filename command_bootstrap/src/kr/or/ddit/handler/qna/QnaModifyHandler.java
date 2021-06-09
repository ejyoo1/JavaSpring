package kr.or.ddit.handler.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.XSSResolver;
import kr.or.ddit.dto.QnaVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.QnaService;

public class QnaModifyHandler implements Handler {

	private QnaService qnaService;
	public void setQnaService(QnaService qnaService) {
		this.qnaService = qnaService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "redirect:detail.do?from=modify&qno="+request.getParameter("qno"); // 조회수 증가되지 않게 하기 위해 from, nno 추가 
		
		XSSResolver.parseXSS(request);
		
		// 파라미터 저장
		int qno = Integer.parseInt(request.getParameter("qno"));
		String title = (String)request.getAttribute("XSStitle");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		QnaVO qna = new QnaVO();
		qna.setQno(qno);
		qna.setTitle(title);
		qna.setContent(content);
		qna.setWriter(writer);
		
		qnaService.modify(qna);
		
		return url;
	}

}
