package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)  // 메서드별 테스트용 JUnit4
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	     "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) // 참고할 파일
@Log4j2 
@WebAppConfiguration	// 프론트영역 테스트용
public class BoardControllerTests {
	
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;	// 톰캣 대타
	
	private MockMvc mockMvc;	// 크롬 대타
	
	@Before 	// import org.junit.Before; 테스트 구동전 선행해야하는 코드 작성
	public void serup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test	// url과 결과를 처리해주는 테스트
	public void testList() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))	// url
				.andReturn()		// 결과를
				.getModelAndView()	// 모델에서 뷰까지
				.getModelMap()		// 표형식
				);
	}
	
	@Test
	public void testRegister() throws Exception {
		
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "컨트롤러 테스트 제목")
				.param("content", "컨트롤러 테스트 내용")
				.param("writer", "컨트롤러 사용자"))
				.andReturn().getModelAndView()
				.getViewName();	// 리턴값을 받아 String 처리
		
		log.info("결과 url : " + resultPage);	// 결과 url : redirect:/board/list
	}
	
	
	@Test	// bno가 넘어가면 돌아오는 값은 객체
	public void testGet() throws Exception {
		// http://localhost:80/board/get?bno=6
		log.info("결과 : " +mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
				.param("bno", "6"))
				.andReturn().getModelAndView()
				.getModelMap());	// select 결과는 getModelMap 사용
	}
	
	@Test
	public void testmodify() throws Exception {
		
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "6")
				.param("title", "컨트롤러 수정 테스트 제목")
				.param("content", "컨트롤러 수정 테스트 내용")
				.param("writer", "컨트롤러 수정 사용자"))
				.andReturn().getModelAndView()
				.getViewName();	// 리턴값을 받아 String 처리
		
		log.info("결과 url : " + resultPage);	// 결과 url : redirect:/board/list
	}
	
	
	@Test
	public void testRemove() throws Exception {
		
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "7"))
				.andReturn().getModelAndView()
				.getViewName();	// 리턴값을 받아 String 처리
		
		log.info("결과 url : " + resultPage);	// 결과 url : redirect:/board/list
	}
	
	
	
	
	
	
	
	
	
	
	
}
