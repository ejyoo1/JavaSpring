package kr.or.ddit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver { // 해결사
	// request 필요한 이유 : 
	public static void view(HttpServletRequest request, HttpServletResponse response,
			String url) throws ServletException, IOException {
		if(url == null) {
			return;
		}
		
		if(url.indexOf("redirect:") > -1) { // redirect 일 때
			String contextPath = request.getContextPath();
			
			url = contextPath + url.replace("redirect:", "");
			
			response.sendRedirect(url);
		} else { // forward 일때
			String prefix = "/WEB-INF/views/";
			String subfix = ".jsp";
			url = prefix + url + subfix;
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}
