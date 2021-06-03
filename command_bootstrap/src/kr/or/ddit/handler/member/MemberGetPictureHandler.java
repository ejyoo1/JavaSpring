package kr.or.ddit.handler.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.FileDownloadResolver;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.util.GetUploadPath;

public class MemberGetPictureHandler implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null; // FileResolver 에 넘겨주기때문에 null
		
		String fileName = request.getParameter("picture");
		String savedPath = GetUploadPath.getUploadPath("member.picture.upload");
		
		FileDownloadResolver.sendFile(fileName, savedPath, request, response);
		
		return url;
	}

}
