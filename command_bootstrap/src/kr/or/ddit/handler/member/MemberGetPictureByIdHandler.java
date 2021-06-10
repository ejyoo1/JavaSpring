package kr.or.ddit.handler.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kr.or.ddit.controller.FileDownloadResolver;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.util.GetUploadPath;

public class MemberGetPictureByIdHandler implements Handler {
	private static final Logger INFO_LOGGER = Logger.getLogger(MemberGetPictureByIdHandler.class);
	{
		INFO_LOGGER.info("■■■■■■■■MemberGetPictureByIdHandler 호출 ■■■■■■■■");
	}
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		String fileName = memberService.getMember(request.getParameter("id")).getPicture();
		INFO_LOGGER.info("fileName : " + fileName);
		String savedPath = GetUploadPath.getUploadPath("member.picture.upload");
		
		FileDownloadResolver.sendFile(fileName, savedPath, request, response);
		
		return url;
	}
}
