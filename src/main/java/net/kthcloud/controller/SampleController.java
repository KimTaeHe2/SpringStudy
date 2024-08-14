package net.kthcloud.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;
import net.kthcloud.domain.SampleDTO;
import net.kthcloud.domain.SampleDTOList;
import net.kthcloud.domain.TodoDTO;


@Controller //servlet-context.xml에 있는 context:component-scan base-package="net.kthcloud.controller" 코드가 관리함 
@RequestMapping("/sample/*") // url 생성됨 http://localhost:80/sample/*
@Log4j2
public class SampleController {
	
	// 필드

	
	
	// 생성자
	
	
	// 메서드
//	@DateTimeFormat을 DTO 필드에 명시하면 아래 코드 사용 안함
	
//	@InitBinder // 문자열을 날짜 형식으로 변경용
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(simpleDateFormat, false));
//	}
	
	@DateTimeFormat
	
	
	
	@RequestMapping("") // http://localhost:80/sample/
	public void basic() {
		// 리턴 타입이 void인 경우에는 경로와 같은 url.jsp를 찾는다
		// 파일 [/WEB-INF/views/sample.jsp]을(를) 찾을 수 없습니다.
		log.info("SampleController.basic() 메서드 실행");
	}
	
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST}) // http://localhost:80/sample/basic
	// {RequestMethod.GET (get 메서드로 호출), RequestMethod.POST (post 메서드로 호출)}
	public void basicGet() {
		log.info("SampleController.basicGet Get+POST방식의 메서드 호출용");
	}
	
	@GetMapping("/basicOnlyGet") // http://localhost:80/sample/basicOnlyGet
	public void basicGetOnly() {
		// 파일 [/WEB-INF/views/sample/basicOnlyGet.jsp]을(를) 찾을 수 없습니다.
		log.info("SampleController.basicGetOnly Get방식의 메서드 호출용");
	}
	
	@PostMapping("/basicOnlyPost") // http://localhost/sample/basicOnlyPost
	public void basicPostOnly() {
		log.info("SampleController.basicPostOnly POST방식의 메서드 호출용");
	}
	
	@GetMapping("/ex01") // http://localhost:80/sample/ex01
	public String ex01(SampleDTO sampleDTO) {
		
		log.info("SampleController.ex01(SampleDTO sampleDTO) 메서드 실행" + sampleDTO);
		
		return "ex01"; // /WEB-INF/views/ex01.jsp
	}
	
	@GetMapping("/ex02") // http://localhost:80/sample/ex02?id=kth&age=30
	public String ex02(@RequestParam("id") String name, @RequestParam("age") int age) {
		// @RequestParam = 파라미터로 사용된 변수 이름과 전달되는 파라미터의 이름이 다른경우 유용
		// 타입을 정해서 넣을 수 있어 안전하다.
		log.info("name : " + name);
		log.info("age : " + age);
		return "ex02";
	}
	
	// 리스트, 배열처리
	@GetMapping("/ex02List") // http://localhost:80/sample/ex02List?ids=111&ids=222&ids=333
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		
		log.info("ids : " + ids);
		
		return "ex02List";
	}
	
	@GetMapping("/ex02Array") // http://localhost:80/sample/ex02Array?ids=111&ids=222&ids=333
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("array ids : " + Arrays.toString(ids));
		return "ex02Array";
	}
	
	@GetMapping("/ex02Bean") 
	// http://localhost:80/sample/ex02Bean?list[0].name=kkw&list[0].age=30&list[1].name=kth&list[1].age=28 -> 특수문자 오류
	// http://localhost/sample/ex02Bean?list%5B0%5D.name%3Dkkw%26list%5B0%5D.age%3D30%26list%5B1%5D.name%3Dkth%26list%5B1%5D.age%3D28
	public String ex02Bean(SampleDTOList list) { // 리스트 객체를 매개값으로 받음
		
		log.info("list dtos : " + list);
		return "ex02Bean";
	}
	
	// 상단에서 만든 initBinder를 활용한 날짜타입 입력
	@GetMapping("/ex03") // http://localhost:80/sample/ex03?title=study&dueDate=2024/08/14&check=true
	// todo : TodoDTO(title=study, dueDate=Wed Aug 14 00:00:00 KST 2024, check=true)
	public String ex03(TodoDTO todoDTO) {
		log.info("todo : " + todoDTO);
		return "ex03";
	}
	
	
	
	
	
	
	
	
	
}
