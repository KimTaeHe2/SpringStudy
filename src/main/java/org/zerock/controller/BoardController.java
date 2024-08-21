package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller		// 스프링이 컨트롤러 역할을 제공
@Log4j2
@RequestMapping("/board/*") 	// http://localhost:80/board/???
@AllArgsConstructor		// 모든 필드값으로 생성자 만듬
public class BoardController {

	private BoardService service; // BoardController boardController = new BoardController(BoardService);
	
	@GetMapping("/list") // http://localhost:80/board/list
	public void list(Model model) { // import org.springframework.ui.Model; 스프링이 관리하는 메모리
		log.info("BoardController.list 메서드 실행");
		model.addAttribute("list",service.getList()); // name : list, Object : list<BoardVO>
	}
	
	@PostMapping("/register") // http://localhost:80/board/register
	public String register(BoardVO board, RedirectAttributes rttr) {
		// RedirectAttributes rttr = 1회성의 값을 제공 (addFlashAttribute("이름","값:)
		log.info("BoardController.register 메서드 실행");
		service.register(board);	// 프론트에서 form값이 객체로 넘어옴
		
		rttr.addFlashAttribute("result", board.getBno());	// 객체에 있는 bno값을 1회성으로 가지고 있음(Model영역)
		
		return "redirect:/board/list";	// 등록후에는 list페이지로 보냄 http://localhost:80/board/list
	}
	
	@GetMapping("/get") // http://localhost:80/board/get
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("BoardController.get 메서드 실행");
		model.addAttribute("board", service.get(bno));
		// 서비스계층에 get메서드에 bno 값을 넣어주면 객체(sql처리후)가 나옴
	}
	
	@PostMapping("/modify") // http://localhost:80/board/modify
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("BoardController.modify 메서드 실행");
		if(service.modify(board)) { // service.modify 타입이 boolean
			rttr.addFlashAttribute("result", "success"); // 수정 성공시 success 메시지를 보냄
		}else {
			rttr.addFlashAttribute("result", "fail"); // 수정 실패시 fail 메시지를 보냄
		}
		return "redirect:/board/list"; // 결론 http://localhost:80/board/list
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("BoardController.remove 메서드 실행");
		if(service.remove(bno)) { // service.modify 타입이 boolean
			rttr.addFlashAttribute("result", "success"); // 수정 성공시 success 메시지를 보냄
		}else {
			rttr.addFlashAttribute("result", "fail"); // 수정 실패시 fail 메시지를 보냄
		}
		return "redirect:/board/list";
	}
	
	
	
	
	
	
	
	
	
}
