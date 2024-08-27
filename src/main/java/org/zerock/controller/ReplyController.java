package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController // Rest방식 -> view/jsp 가 아닌 json,xml로 나옴
@RequestMapping("/replies")	// http://localhost:80/replies/????
@Log4j2
@AllArgsConstructor		// new ReplyController(ReplyService);	
public class ReplyController { // Rest 방식의 컨트롤러로 구현 + AJAX(프론트) 처리

	private ReplyService service;
	
	// http://localhost/replies/new
	@PostMapping(value = "/new", consumes = "application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	// 입력값은 json으로 받겠다.
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		log.info("ReplyVO 객체 입력 값 : " + vo); 	// 파라미터로 입력 값 처리
		int insertCount = service.register(vo);		// sql 처리 후에 결과값이 1 | 0 이 나옴
		log.info("서비스 + 매퍼 처리결과 : " + insertCount);
		
		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK) 			// HttpStatus.OK = 200 정상
								: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 	// HttpStatus.INTERNAL_SERVER_ERROR = 500 서버오류
		// 리턴은 200 | 500 으로 처리된다
		// 삼항 연산자나 if로 리턴을 할때 정상 처리인지 오류 값인지를 전달 해야 한다.
	}
	
	// http://localhost:80/replies/pages/11/1 -> xml
	// http://localhost:80/replies/pages/11/1.json -> json
	@GetMapping(value="/page/{bno}/{page}", produces= {MediaType.APPLICATION_XML_VALUE,
													   MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno){
		
		log.info("ReplyController.getList() 메서드 실행.");
		log.info("페이지번호 : " + page);
		log.info("찾을번호 : " + bno);
		
		Criteria cri = new Criteria(page, 10); // 현재 페이지와 리스트 갯수를 전달
		log.info("Criteria : " + cri);
		
		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK); // 200 정상
//		{"rno":7,"bno":11,"reply":"댓글 11","replyer":"kkw","replyDate":1724723539000,"updateDate":1724723539000},
//		{"rno":16,"bno":11,"reply":"댓글 11","replyer":"kkw","replyDate":1724723582000,"updateDate":1724723582000},
//		{"rno":20,"bno":11,"reply":"댓글 11","replyer":"kkw","replyDate":1724723591000,"updateDate":1724723591000},
//		{"rno":29,"bno":11,"reply":"댓글 11","replyer":"kkw","replyDate":1724723657000,"updateDate":1724723657000},
//		{"rno":34,"bno":11,"reply":"매퍼댓글테스트","replyer":"매퍼","replyDate":1724724704000,"updateDate":1724724704000}
	}
	
	// http://localhost:80/replies/4
	@GetMapping(value="/{rno}" , produces= {MediaType.APPLICATION_XML_VALUE,
											MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		
		log.info("ReplyController.get() 메서드 실행");
		log.info("찾을 번호 : " + rno);
		
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK); // 200 정상
	}
	
	// http://localhost:80/replies/4
	@DeleteMapping(value="/{rno}" , produces= {MediaType.TEXT_PLAIN_VALUE}) // json으로 나올 필요가 없음
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		
		log.info("ReplyController.remove() 메서드 실행");
		log.info("삭제할 번호 : " + rno );
		
		return service.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) 			// HttpStatus.OK = 200 정상
								: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 	// HttpStatus.INTERNAL_SERVER_ERROR = 500 서버오류
		// 리턴은 200 | 500 으로 처리된다
		// 삼항 연산자나 if로 리턴을 할때 정상 처리인지 오류 값인지를 전달 해야 한다.
	}
	
	// http://localhost:80/replies/
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH}, value="/{rno}",
					consumes = "application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
		//								  이미 폼(프론트)에 있는 값    수정할 번호
		
		vo.setRno(rno);	// 이미 가지고 잇는 객체의 rno 값을 넣음
		
		log.info("ReplyController.modify() 메서드 실행");
		log.info("수정할 객체 : " + vo);
		
		return service.modify(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) 			// HttpStatus.OK = 200 정상
									   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 	// HttpStatus.INTERNAL_SERVER_ERROR = 500 서버오류
		// 리턴은 200 | 500 으로 처리된다
		// 삼항 연산자나 if로 리턴을 할때 정상 처리인지 오류 값인지를 전달 해야 한다.;
	}
	
	
	
	
	
	
	
}
