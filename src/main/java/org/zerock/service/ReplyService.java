package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyService { // 구현 클래스 필수 ReplyServiceImpl.java
// 추상메서드
	
	public int register(ReplyVO vo); // 댓글 등록용
	
	public ReplyVO get(Long rno); 	 // 댓글 1개 가져옴
	
	public int modify(ReplyVO vo);	 // 객체 수정 후 int 리턴
	
	public int remove(Long rno);	 // 댓글 1개 삭제 후 int 리턴
	
//	전체 리스트 불러오기
	
	public List<ReplyVO> getList(Criteria cri, Long bno);	// 게시글의 번호를 이용해 모든 댓글을 가져온다
}
