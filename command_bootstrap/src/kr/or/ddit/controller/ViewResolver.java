package kr.or.ddit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ViewResolver { // JSP 포워딩, 리다이렉트
	private static final Logger EXCEPTION_LOGGER = Logger.getLogger(ViewResolver.class);
	private static final Logger INFO_LOGGER = Logger.getLogger(ViewResolver.class);
	
	public static void view(HttpServletRequest request, HttpServletResponse response,
			String url) throws ServletException, IOException {
		INFO_LOGGER.info("■■■ViewResolver view init■■■");
		INFO_LOGGER.info("url : " + url);
		if(url == null) {
			return;
		}
		
		if(url.indexOf("redirect:") > -1) { // redirect 일 때
			String contextPath = request.getContextPath();
			
			url = contextPath + url.replace("redirect:", "");
			INFO_LOGGER.info("redirect = url : " + url);
			
			response.sendRedirect(url);
		} else { // forward 일때
			String prefix = "/WEB-INF/views/";
			String subfix = ".jsp";
			url = prefix + url + subfix;
			INFO_LOGGER.info("forward = url : " + url);
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}
