package kr.or.ddit.command;

import org.apache.log4j.Logger;

public class SummernoteDeleteImgCommand { // command 객체
	private static final Logger INFO_LOGGER = Logger.getLogger(SummernoteDeleteImgCommand.class);
	{
		INFO_LOGGER.info("■■■■■■■■SummernoteDeleteImgCommand 호출■■■■■■■■");
	}
	
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
