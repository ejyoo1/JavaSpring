package kr.or.ddit.handler.pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.FileDownloadResolver;
import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.PdsService;
import kr.or.ddit.util.GetUploadPath;

public class PdsGetFileHandler implements Handler {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		int ano = Integer.parseInt(request.getParameter("ano"));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@"+ano+"ggggggggggggggggggggggggggggrwerew");
		
		AttachVO attach = pdsService.getAttachByAno(ano);
		// 파일명
		String fileName = attach.getFileName();	
		String savedPath = attach.getUploadPath();
		
		FileDownloadResolver.sendFile(fileName, savedPath, request, response);
		
		return url;
	}

}
