package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import backup.handler.MemberModifyHandler;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.handler.Handler;

public class FrontServlet extends HttpServlet {
	private static final Logger EXCEPTION_LOGGER = Logger.getLogger(FrontServlet.class);
	private static final Logger INFO_LOGGER = Logger.getLogger(FrontServlet.class);
	
	private HandlerMapper handlerMapper;
	
	@Override
	public void init(ServletConfig config) throws ServletException { // 이니셜라이즈
		INFO_LOGGER.info("■■■FrontServlet init■■■");
		String handlerMapperType = config.getInitParameter("handlerMapper");
		INFO_LOGGER.info("handlerMapperType : " + handlerMapperType);
		try {
			this.handlerMapper = (HandlerMapper) injectionBean(handlerMapperType);
			INFO_LOGGER.info("handlerMapper 처리완료. 모든 핸들러가 준비되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			INFO_LOGGER.info("handlerMapper 처리 실패. 핸들러가 준비되지 않았습니다.");
		}
	}
	
	private Object injectionBean(String classType) throws Exception {
		INFO_LOGGER.info("■■■injectionBean init■■■");
		Class<?> cls = Class.forName(classType);
		return cls.newInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		INFO_LOGGER.info("■■■doGet init■■■");
		requestPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		INFO_LOGGER.info("■■■doPost init■■■");
		requestPro(request,response);
	}
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		INFO_LOGGER.info("■■■requestPro init■■■");
		/* 사용자 요청 uri 검출 -> uri init handler Mapper에 의해 핸들러를 받음 
		-> process 메서드를 받으면서 req,resp -> view를 받아서 -> view 확인 있다면 resolver에게 반환*/
		
		// 사용자 URI 검출
		String command = request.getRequestURI(); // contextPath 포함.
		INFO_LOGGER.info("getRequestURI" + command);
		if (command.indexOf(request.getContextPath()) == 0) { // contextPath 삭제
			command = command.substring(request.getContextPath().length());
			INFO_LOGGER.info("getRequestURI ==> remove contextPath : " + command);
		}
		
		
		// commandHandler 실행 (HandlerMapper 의뢰 handler 할당)
		Handler handler = null;
		String view = null;
		
		if (handlerMapper != null) { // 핸들러가 준비되었음.
			handler = handlerMapper.getHandler(command);
			if(handler != null) { // 올바른 요청
				try {
					view = handler.process(request, response);
					
					if(view != null) ViewResolver.view(request, response, view);
				} catch (Exception e) { // 핸들러에서 Exception 터질 시 500에러 발생
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					e.printStackTrace();
				}
				
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 Error
			}
		} else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Error
		}
	}
}
