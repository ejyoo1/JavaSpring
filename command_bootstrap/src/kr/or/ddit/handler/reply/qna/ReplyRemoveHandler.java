package kr.or.ddit.handler.reply.qna;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.ReplyQnaService;
import kr.or.ddit.service.ReplyService;

public class ReplyRemoveHandler implements Handler {

	private ReplyQnaService replyQnaService;
	public void setReplyQnaService(ReplyQnaService replyQnaService) {
		this.replyQnaService = replyQnaService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		int rno = Integer.parseInt((String) request.getParameter("rno"));
		int page = Integer.parseInt((String)request.getParameter("page"));
		int qno = Integer.parseInt((String)request.getParameter("qno"));
		
		try {
			replyQnaService.removeReply(rno);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(new SearchCriteria());
			pageMaker.setTotalCount(replyQnaService.getReplyListCount(qno));
			
			int realEndPage = pageMaker.getRealEndPage();
			
			if(page>realEndPage) {page=realEndPage;}
			
			PrintWriter out = response.getWriter();
			out.print(realEndPage);
					
		} catch(SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return null;
	}

}
