package kr.or.ddit.context;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.command.SummernoteDeleteImgCommand;

public class ApplicationContext {
	private static final Logger INFO_LOGGER = Logger.getLogger(ApplicationContext.class);
	{
		INFO_LOGGER.info("■■■■■■■■ApplicationContext 호출■■■■■■■■");
	}
	
	private static Map<String, Object> applicationContext = new HashMap<String, Object>(); // 외부에서 Map 형식으로 사용하기 위함.
	
	private ApplicationContext() {} // 해당 인스턴스를 못만들게 제어.
	
	public static Map<String, Object> getApplicationContext(){ // 준비된 것을 가지고 쓸 수 있도록 Map 형식으로 설정
		INFO_LOGGER.info("■■■■■■■■getApplicationContext 호출■■■■■■■■");
		return applicationContext;
	}
}
