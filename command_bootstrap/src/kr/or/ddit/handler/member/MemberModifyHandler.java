package kr.or.ddit.handler.member;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.controller.FileUploadResolver;
import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.util.GetUploadPath;
import kr.or.ddit.util.MultipartHttpServletRequestParser;

public class MemberModifyHandler implements Handler {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 2. 업로드 파일 환경설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB // 고정수. 상수취급
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 화면 구성
		String url = "member/modify_success";
		
		// 3. 업로드 파일 초기 세팅
		MultipartHttpServletRequestParser multiReq = new MultipartHttpServletRequestParser(request,MEMORY_THRESHOLD,
														MAX_FILE_SIZE,
														MAX_REQUEST_SIZE);
		
		// 4. 서비스 넣기 위한 파라미터 준비 
		String id = multiReq.getParameter("id");
		String pwd = multiReq.getParameter("pwd");
		System.out.println("pwd : " + pwd);
		String email = multiReq.getParameter("email");
		String phone = multiReq.getParameter("phone");
		String authority= multiReq.getParameter("authority");
		String name = multiReq.getParameter("name");
		
		// MemberVO setting
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		member.setEmail(email);
		member.setPhone(phone);
		member.setAuthority(authority);
		member.setName(name);
		
		// 저장경로 설정
		String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
		File file = new File(uploadPath); // 경로 만들기
		if (!file.mkdirs()) {
			System.out.println(uploadPath + " 가 이미 존재하거나 생성을 실패했습니다.");
		}
		
		// 기존 사진 변경 유무 확인
		String uploadPicture = multiReq.getParameter("uploadPicture");
		if(uploadPicture != null && !uploadPicture.isEmpty()) { // 사진 변경
			// 기존 사진 이미지 삭제
			File deleteFile = new File(uploadPath, multiReq.getParameter("oldPicture"));
			if(deleteFile.exists()) {
				deleteFile.delete();
			}
			// 최근 사진 이미지 저장
			List<AttachVO> attachList
			= FileUploadResolver.fileUpload(multiReq.getFileItems("picture"), uploadPath);
			String saveFileName = attachList.get(0).getFileName(); // 어짜피 하나라 0번지
			member.setPicture(saveFileName);
		} else { // 기존 파일 삽입
			member.setPicture(multiReq.getParameter("oldPicture"));
		}
		// 처리
		memberService.modify(member);
		
		request.setAttribute("member", member);
		
		// 로그인 사용자 확인
		request.setAttribute("parentReload", false);
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(member.getId().equals(loginUser.getId())) {
			request.setAttribute("parentReload", true);
			session.setAttribute("loginUser", memberService.getMember(member.getId()));
		}
		
		return url;
	}

}
