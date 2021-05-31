package kr.or.ddit.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;

import kr.or.ddit.context.ApplicationContext;
import kr.or.ddit.handler.Handler;

public class HandlerMapper {
	private static final Logger EXCEPTION_LOGGER = Logger.getLogger(HandlerMapper.class);
	private static final Logger INFO_LOGGER = Logger.getLogger(HandlerMapper.class);
	
	private Map<String, Handler> commandMap = new HashMap<String,Handler>();
	
	public HandlerMapper() throws Exception {
		String path="kr/or/ddit/properties/handler"; 
		INFO_LOGGER.info("■■■HandlerMapper init■■■");
		ResourceBundle rbHome = ResourceBundle.getBundle(path); // ResourceBundle (알아서 properties를 붙임) KeySet 리턴
		
		Set<String> actionSetHome = rbHome.keySet(); // uri set
		
		Iterator<String> it = actionSetHome.iterator();
		
		while(it.hasNext()) {
			String command = it.next();
			
			String actionClassName = rbHome.getString(command);
			
			System.out.println(actionClassName);
			try {
				Class<?> actionClass = Class.forName(actionClassName);
				Handler commandAction = (Handler) actionClass.newInstance();
				
				// 조립 (리플렉션 - 메서드 쫙 꺼내는것) ==> 이후 의존주입
				// 의존주입 (service, dao....)
				// 의존성 확인 및 조립
				Method[] methods = actionClass.getMethods();
				for (Method method : methods) {
					if (method.getName().contains("set")) {
						INFO_LOGGER.info("method.getParameterTypes() : " + method.getParameterTypes());
						INFO_LOGGER.info("method.getParameterTypes()[0] : " + method.getParameterTypes()[0]);
						INFO_LOGGER.info("method.getParameterTypes()[0].getName() : " + method.getParameterTypes()[0].getName());
						String paramType = method.getParameterTypes()[0].getName();
						paramType=paramType.substring(paramType.lastIndexOf(".")+1);
						INFO_LOGGER.info("paramTypeBefore : " + paramType);
						
						paramType=(paramType.charAt(0) + "").toLowerCase() + paramType.substring(1); // set 자르고 첫글자 소문자 변경
						INFO_LOGGER.info("paramTypeAfter : " + paramType);
						try {
							method.invoke(commandAction, 
									ApplicationContext.getApplicationContext().get(paramType)); // set 메서드 할 클래스, 사용할 클래스 ==> invoke
							INFO_LOGGER.info("[invoke] " + ApplicationContext.getApplicationContext().get(paramType));
						} catch (Exception e) {
							EXCEPTION_LOGGER.error("invoke 도중 에러 발생함.");
							e.printStackTrace();
							throw e;
						}
					}
				} // 의존 주입 종료
				
				
				commandMap.put(command, commandAction);
				INFO_LOGGER.info(command + ":" + commandAction + "가 준비되었습니다.");
			} catch (ClassNotFoundException e) { // 클래스를 찾지 못함
				INFO_LOGGER.info(actionClassName + "이 존재하지 않습니다.");
				throw e;
			} catch (InstantiationException e) { // 인스턴스를 생성하지 못함
				INFO_LOGGER.info(actionClassName + "인스턴스를 생성할 수 없습니다.");
			} catch (IllegalAccessException e) { // 문법적 오류가 발생함
				INFO_LOGGER.info("문법적 오류가 발생하였습니다.");
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	public Handler getHandler(String url) {
		INFO_LOGGER.info("■■■getHandler init■■■");
		Handler handler = commandMap.get(url);
		return handler;
	}
}
