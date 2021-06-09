package kr.or.ddit.command;

import org.apache.log4j.Logger;

import kr.or.ddit.command.Criteria;

public class SearchCriteria extends Criteria {
	private static final Logger INFO_LOGGER = Logger.getLogger(SearchCriteria.class);
	{
		INFO_LOGGER.info("■■■■■■■■SearchCriteria 호출■■■■■■■■");
	}
	
	// 사용자가 값을 주지 않아도 null이 되지 않기위해 명시적 초기화 수행함.
	private String searchType = ""; // 검색구분 
	private String keyword = ""; // 검색어
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
