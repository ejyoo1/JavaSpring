package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.QnaVO;

public interface QnaService {
	Map<String, Object> getQnaList(SearchCriteria cri) throws SQLException;
	
	QnaVO getQna(int qno) throws SQLException;
	
	QnaVO getQnaForModify(int qno) throws SQLException;
}
