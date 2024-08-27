package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// Java Config
// @ContextConfiguration(classes = {org.zerock.config.RootConfig.class} )
@Log4j2
public class ReplyMapperTests {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

	@Test
	public void testMapper() {
		
		log.info(mapper);
	}

	@Test
	public void testCreate() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(11L);
		vo.setReply("매퍼댓글테스트");
		vo.setReplyer("매퍼");
		
		mapper.insert(vo);
	}
	
	@Test
	public void testRead() {
		Long targetRno = 10L;
		ReplyVO vo = mapper.read(targetRno);
		log.info("select + rno 결과 = " + vo);
		// ReplyVO(rno=10, bno=8, reply=댓글 8, replyer=kkw, replyDate=Tue Aug 27 10:52:22 KST 2024, updateDate=Tue Aug 27 10:52:22 KST 2024)
	}
	
	@Test
	public void testUpdate() {
		Long targetRno = 10L;
		ReplyVO vo = mapper.read(targetRno); // 10번 객체를 가져온다.
		vo.setReply("매퍼로 수정한 내용22");
		int count = mapper.update(vo);
		log.info("수정된 갯수 : " + count);
		log.info("수정된 객체 : " + vo);
//		INFO  org.zerock.mapper.ReplyMapperTests(testUpdate58) - 수정된 갯수 : 1
//		INFO  org.zerock.mapper.ReplyMapperTests(testUpdate59) - 수정된 객체 : ReplyVO(rno=10, bno=8, reply=매퍼로 수정한 내용22, replyer=kkw,
//		replyDate=Tue Aug 27 10:52:22 KST 2024, updateDate=Tue Aug 27 12:06:16 KST 2024)
	}
	
	@Test
	public void testDelete() {
		Long targetRno = 1L;
		log.info("삭제 후 결과 : " + mapper.delete(targetRno));
		// INFO  org.zerock.mapper.ReplyMapperTests(testdelete55) - 삭제 후 결과 : 1
	}
	
// 	번호를 이용한 객체가 리스트로 나옴
	@Test
	public void testList() {
		
		Criteria cri = new Criteria();
		log.info("Criteria : " + cri);
// 		INFO  org.zerock.mapper.ReplyMapperTests(testList77) - Criteria : Criteria(pageNum=1, amount=10, type=null, keyword=null)
		
		List<ReplyVO> replys = mapper.getListWithPaging(cri, 10L);
		
		replys.forEach(reply -> log.info(reply));
//		 ReplyVO(rno=2, bno=10, reply=댓글 11, replyer=kkw, replyDate=Tue Aug 27 10:51:46 KST 2024, updateDate=Tue Aug 27 10:51:46 KST 2024)
//		 ReplyVO(rno=8, bno=10, reply=댓글 10, replyer=kkw, replyDate=Tue Aug 27 10:52:20 KST 2024, updateDate=Tue Aug 27 10:52:20 KST 2024)
//		 ReplyVO(rno=21, bno=10, reply=댓글 10, replyer=kkw, replyDate=Tue Aug 27 10:53:12 KST 2024, updateDate=Tue Aug 27 10:53:12 KST 2024)
//		 ReplyVO(rno=26, bno=10, reply=댓글 10, replyer=kkw, replyDate=Tue Aug 27 10:54:07 KST 2024, updateDate=Tue Aug 27 10:54:07 KST 2024)
//		 ReplyVO(rno=30, bno=10, reply=댓글 10, replyer=kkw, replyDate=Tue Aug 27 10:54:18 KST 2024, updateDate=Tue Aug 27 10:54:18 KST 2024)
	}
	
	
}
