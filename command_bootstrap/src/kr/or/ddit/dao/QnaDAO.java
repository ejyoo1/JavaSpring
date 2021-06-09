package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.QnaVO;

public interface QnaDAO {
	// 질의응답 조회
	List<QnaVO> selectSearchQnaList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// 질의응답 글 수 조회
	int selectSearchQnaListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// qno 기준 글 조회
	QnaVO selectQnaByQno(SqlSession session, int qno) throws SQLException;
	
	// 조회수 증가
	void increaseViewCount(SqlSession session, int qno) throws SQLException;
	
	// 글 다음 번호 가져오기
	int selectQnaSequenceNextValue(SqlSession session) throws SQLException;
	
	// 질의응답 등록
	void insertQna(SqlSession session, QnaVO qna) throws SQLException;
	
	// 질의응답 수정
	void updateQna(SqlSession session, QnaVO qna) throws SQLException;
	
	// 질의응답 삭제
	void deleteQna(SqlSession session, int qno) throws SQLException;
}
