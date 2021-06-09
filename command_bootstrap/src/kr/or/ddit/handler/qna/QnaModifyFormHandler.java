package kr.or.ddit.handler.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.QnaVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.QnaService;

public class QnaModifyFormHandler implements Handler {
	
	private QnaService qnaService;
	public void setQnaService(QnaService qnaService) {
		this.qnaService = qnaService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url ="qna/modify";
		
		int qno = Integer.parseInt(request.getParameter("qno"));
		
		QnaVO qna=qnaService.getQnaForModify(qno); // 조회수가 올라가지 않는 것 호출
		
		request.setAttribute("qna", qna);
		
		return url;
	}

}
