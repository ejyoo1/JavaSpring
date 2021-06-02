package kr.or.ddit.handler.member;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.FileUploadResolver;
import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.util.GetUploadPath;
import kr.or.ddit.util.MultipartHttpServletRequestParser;

public class MemberUploadPictureHandler implements Handler {
	
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB // 고정수. 상수취급
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null; // url이 없다는 것을 명시적으로 알려줌.
		
		MultipartHttpServletRequestParser multi = null;
		
		try {
			multi = new MultipartHttpServletRequestParser(request,MEMORY_THRESHOLD,
														MAX_FILE_SIZE,
														MAX_REQUEST_SIZE);
			// 파일 저장 경로 설정 => 업로드 패스
			String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
			
			// 사진 이미지 저장 => 파일 아이템 어레이 받아옴. => 저장하고 AttachVO 만들어줘서 리턴
			List<AttachVO> attachList
			= FileUploadResolver.fileUpload(multi.getFileItems("pictureFile"), uploadPath);
			
			if(attachList.size() > 0) {
				response.setCharacterEncoding("utf-8");// 이것 아니면 MultipartHttpServletRequestParser에서 name에 UTF-8 줄것
				PrintWriter out = response.getWriter();
				for(AttachVO attach : attachList) {
					out.print(attach.getFileName());
				}
			}
			
			// 기존 이미지 삭제
			String oldPicture = multi.getParameter("oldPicture");
			File oldFile = new File(uploadPath + File.separator + oldPicture);
			if(oldFile.exists()) {
				oldFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return url;
		// multi ( getFile= fileitemArray, getName = getFile 한개)
	}

}
