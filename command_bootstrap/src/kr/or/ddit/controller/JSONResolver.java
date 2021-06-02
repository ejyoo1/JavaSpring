package kr.or.ddit.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONResolver { // java 오브젝트를 json 형태로 바꾸는 (시리얼라이즈)
	private static final Logger EXCEPTION_LOGGER = Logger.getLogger(JSONResolver.class);
	private static final Logger INFO_LOGGER = Logger.getLogger(JSONResolver.class);
	
	public static void view(HttpServletResponse response, Object target) throws Exception{
		INFO_LOGGER.info("■■■JSONResolver view init■■■");
		// 출력
		ObjectMapper mapper = new ObjectMapper();
		
		// content Type 결정
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 내보내기
		out.println(mapper.writeValueAsString(target));
		
		// out 객체를 종료하고 환원
		out.close();
	}
}
