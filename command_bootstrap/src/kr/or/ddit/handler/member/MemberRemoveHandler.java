package kr.or.ddit.handler.member;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.util.GetUploadPath;

public class MemberRemoveHandler implements Handler {

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "member/remove_success";
		try {
			
			String id = request.getParameter("id");
			
			//getmember picturefile remove
			MemberVO member = memberService.getMember(id);
			
			// 이미지 삭제
			String savedPath = GetUploadPath.getUploadPath("member.picture.upload");
			String fileName = member.getPicture();
			File picture = new File(savedPath,fileName);
			
			//없는데 지우면 FileNotFoundException;
			if(picture.exists()) {
				picture.delete();
			}
			
			//DB삭제
			memberService.remove(id);
			
			// service remove session login user
			//로그인 유저 확인
			// 삭제되는 회원이 로그인 회원인 경우 로그아웃해야함.
			MemberVO loginUser = (MemberVO) request.getSession().getAttribute("loginUser");
			// login user => invalidate session
			if(loginUser.getId().equals(member.getId())) {
				request.getSession().invalidate(); // session 갱신
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}
}
