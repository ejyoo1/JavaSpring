package kr.or.ddit.handler.pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.FileDownloadResolver;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.PdsService;
import kr.or.ddit.util.GetUploadPath;

public class PdsDownloadImgHandler implements Handler {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		// 파일명
		String fileName = pdsService.getAttachByAno(Integer.parseInt(request.getParameter("ano"))).getFileName();		
		// 실제 저장 경로를 설정.
		String savePath = GetUploadPath.getUploadPath("pds.upload");
		
		
		FileDownloadResolver.sendFile(fileName, savePath, request, response);
		
		return url;
	}

}
