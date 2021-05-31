package filter;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.MemberVO;

/**
 * 로그인 되어있는지 체크하는 필터
 * @author 유은지
 * 1. 세션
 * 2. 세션 어떻게 가져올것인가?
 * 3. URL 상황에 따라 나뉘기에 외부에서 파라미터를 받아야 함.
 * 4. 로그인 체크를 할 URI 와 로그인 체크를 하지 않을 URI 중 선택. ==> 후자
 */

// 필터는 언로드 없이 바로 필터가 로딩이 됨.
//@WebFilter("/LoginCheckFilter") // 이게 시작되면 톰캣이 구동될 때 모두 올라감. (실행 순서는 ApplicationContext 다음)
public class LoginCheckFilter_Comment implements Filter { // 왜 인터페이스로 만들었나? (만든사람이 꼭 필요하다고 느껴서)
	
	// 제외할 URI를 등록해놓고 요청이 올 때 꺼내 쓰려고 함.(디자인 패턴이 들어감)
	private List<String> exURLs = new ArrayList<String>();
	
    public void destroy() {
		// TODO Auto-generated method stub
	}

    // post, get 구분하지 않기에 doFilter만 사용
    // FilterChain 필터의 연결고리 설정 위함.
    // ServletRequest은 HttpServletRequest의 부모이고, ServletRequest의 래퍼런스가 HttpServletRequest 에 할당 할 수 있고
    // ServletRequest 에는 getSession, getRequestURI이 없고HttpServletRequest에 있기 때문에
    // 다형성을 기반으로 해서 캐스팅을 하였음.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		String reqUrl = httpReq.getRequestURI().substring(httpReq.getContextPath().length());
		
		//System.out.println("request URI : " + reqUrl);
		
		//return; // 여기에서 리턴 수행 시 서블릿에 넘어가지않아서 아무것도 보이지 않게 됨.
		if(excludeCheck(reqUrl)) {
			chain.doFilter(request, response); // 다음 체인으로 간다. (맨 마지막에 서블릿에 도달하면 서블릿에 넘겨준다.)
			return;
		}
		
		// login check
		HttpSession session = httpReq.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		// login 확인
		if(loginUser==null) { // 비 로그인 상태
			httpResp.setContentType("text/html;charset=utf-8");
			PrintWriter out = httpResp.getWriter();
			out.println("<script>");
			out.println("alert('로그인은 필수입니다.');");
			out.println("location.href='/';");
			out.println("</script>");
			out.close();
		}else {
			chain.doFilter(request, response);
		}
		
	}
	
	
	

	public void init(FilterConfig fConfig) throws ServletException {
		String excludeURLNames = fConfig.getInitParameter("exclude"); // xml 에서 설정한 login,resources
		System.out.println(excludeURLNames);
		StringTokenizer st = new StringTokenizer(excludeURLNames, ",");
		while(st.hasMoreTokens()) {
			exURLs.add(st.nextToken().trim()); // xml에 혹시나 존재할 공백을 제거
		}
		System.out.println("exURLs : " + exURLs);
	}
	
	// 하나라도 발견되면 true를 리턴
	private boolean excludeCheck(String url) {
		if(url.length() <= 1) 
			return true;
			
		for(String exURL : exURLs) {
			if(url.contains(exURL)) {
				return true;
			}
		}
		return false;
	}

}
