package kr.or.ddit.handler.reply;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.ReplyRegistCommand;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.ReplyService;

public class ReplyRegistHandler implements Handler {
	
	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		ReplyRegistCommand replyReq = mapper.readValue(request.getReader(), ReplyRegistCommand.class);
		
		PrintWriter out = response.getWriter(); 
		try {
			replyService.registReply(replyReq.toReplyVO());
			
			// 페이지 세팅 
			// 페이지 세팅할 때 perpage 세팅이 필요하지만 searchCritera에 기본값이 등록되어 있으므로 세팅하지 않음.
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(new SearchCriteria());
			pageMaker.setTotalCount(replyService.getReplyListCount(replyReq.toReplyVO().getBno()));
			
			int realEndPage = pageMaker.getRealEndPage();
			
			out.print("SUCCESS,"+realEndPage);
		} catch (SQLException e) {
			e.printStackTrace();
			out.print("FAIL");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("FAIL");
		}
		
		return url;
	}

}
