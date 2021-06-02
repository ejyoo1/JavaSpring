package kr.or.ddit.util;

import java.util.UUID;

/**
 * 파일명을 고유하게 받는 방법
 * @author PC-19
 * UUID + 구분자
 */
public class MakeFileName {
	public static String toUUIDFileName(String fileName, String delimiter) {// fileName : originalFileName /
		 String uuid = UUID.randomUUID().toString().replace("-", "");
		 return uuid + delimiter + fileName;
	}
	
	public static String parseFileNameFromUUID(String fileName, String delimiter) {
		String[] uuidFileName = fileName.split(delimiter);
		return uuidFileName[uuidFileName.length -1];
	}
}
