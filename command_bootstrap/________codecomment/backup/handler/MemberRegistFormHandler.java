package backup.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberRegistFormHandler implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/member/registForm";
		
		return url;
	}

}
