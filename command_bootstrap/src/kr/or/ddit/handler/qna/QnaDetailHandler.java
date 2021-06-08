package kr.or.ddit.handler.qna;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.QnaVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.QnaService;

public class QnaDetailHandler implements Handler {

	private QnaService qnaService;
	public void setQnaService(QnaService qnaService) {
		this.qnaService = qnaService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "qna/detail";
		
		int qno = Integer.parseInt(request.getParameter("qno"));
		String from = request.getParameter("from"); 
		
		try {
			QnaVO qna = null;
			if(from != null && from.equals("modify")) {
				qna = qnaService.getQnaForModify(qno);
			} else {
				qna = qnaService.getQna(qno);
			}
			
			request.setAttribute("qna", qna);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return url;
	}
}
