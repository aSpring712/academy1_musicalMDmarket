package com.board.model;

import java.util.ArrayList;

public interface QnAboardDAO {
	// 추가
	public void boardInsert(QnAboardDTO board);
		
	// 수정
	public void boardUpdate(QnAboardDTO board);
	
	// 전체보기
	public ArrayList<QnAboardDTO> boardList(int startRow, int endRow, String field, String word);
	
	// 삭제
	public int boardDelete(int num);
	
	// 게시글 수
	public int boardCount(String field, String word);

	// 상세보기
	public QnAboardDTO findByNum(int num);
	
	// -------comment
	public ArrayList<QnAcommentDTO> findAllComment(int bnum);
	public void commentInsert(QnAcommentDTO comment);
}
