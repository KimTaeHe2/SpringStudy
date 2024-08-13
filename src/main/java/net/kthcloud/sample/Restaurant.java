package net.kthcloud.sample;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

	@Component	// 스프링이 관리해 주세요 root-context.xml 에 context:component-scan 추가
	@Data		// lombok이 dto처럼 관리해 주세요
public class Restaurant {

	// 필드
	@Setter(onMethod_ = @Autowired) // 자동으로 setchef()를 컴파일 시 생성한다.
	private Chef chef ;				// setChef(chef)
	private String restaurantName;  
	private Date openTime;
	private Date closeTime;
	
	// 생성자
	
	
	// 메서드
}
