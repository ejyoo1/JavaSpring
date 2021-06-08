package kr.or.ddit.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.josephoconnell.html.HTMLInputFilter;

// 1. 파라미터 맵을 사용해서 파라미터 모두 추출(첨부파일 할 때 사용하기 좀 어려운 가능성이 존재함 => encType제외(multipartformData). 꺼낼때 빈값일 것임. (HTMLInputFiler를 직접 주어야 함.)
// 2. 이름과 값을 꺼내고 HTMLInputFiler로 변환한 뒤 "XSS" 타입으로 변환된 값을 추가
public class XSSResolver { // 입력 필드에서 스크립트 코드로 인한 취약점을 막기 위함.
	
	public static void parseXSS(HttpServletRequest request) throws IOException, ServletException {
	      Enumeration<String> crossParamNames = request.getParameterNames();
	      
	      while (crossParamNames.hasMoreElements()) {
	         
	         String paramName = crossParamNames.nextElement();
	         System.out.println("paramName!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+paramName);
	         String paramValue = request.getParameter(paramName);
	         System.out.println("paramName!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+paramName);
	         System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + HTMLInputFilter.htmlSpecialChars(paramValue));
	         
	         request.setAttribute("XSS" + paramName, HTMLInputFilter.htmlSpecialChars(paramValue));
	      }
	}

}
