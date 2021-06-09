package kr.or.ddit.handler.pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.XSSResolver;
import kr.or.ddit.dto.PdsVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.PdsService;

public class PdsRegistHandler implements Handler {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "pds/regist_success";
		
		XSSResolver.parseXSS(request);
		
		String title = (String) request.getAttribute("XSStitle");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		PdsVO pds = new PdsVO();
		pds.setTitle(title);
		pds.setContent(content);
		pds.setWriter(writer);
		
		pdsService.regist(pds);
		
		return url;
	}

}
