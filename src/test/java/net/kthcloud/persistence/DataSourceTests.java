package net.kthcloud.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import net.kthcloud.mapper.TimeMapper;

@RunWith(SpringJUnit4ClassRunner.class) // 메서드 별로 테스트 
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 참조할 파일
@Log4j2 //log출력용
public class DataSourceTests {
	
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	
	@Setter(onMethod_ = @Autowired)
	private SqlSessionFactory sqlSessionFactory;
	
	@Setter(onMethod_ = @Autowired)
	private TimeMapper timeMapper;
	
	@Test // JUnit 테스트용
	public void testConnection() {
		Connection connection;
		try {
			connection = dataSource.getConnection();
			log.info(connection);
			// INFO net.kthcloud.persistence.DataSourceTests(testConnection32) 
			// HikariProxyConnection@2028510206 wrapping oracle.jdbc.driver.T4CConnection@3249a1ce
		} catch (SQLException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test // testMyBatis 테스트용
	public void testMyBatis() {
		// 동적 쿼리문을 처리해주는 sql 매핑용 테스트
		try {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Connection connection = sqlSession.getConnection();
			log.info(sqlSession);
			log.info(connection);
//			INFO  net.kthcloud.persistence.DataSourceTests(testMyBatis52) - org.apache.ibatis.session.defaults.DefaultSqlSession@61f80d55
//			INFO  net.kthcloud.persistence.DataSourceTests(testMyBatis53) - HikariProxyConnection@665317128 wrapping oracle.jdbc.driver.T4CConnection@57bd2029
			
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTime() {
		log.info(timeMapper.getClass().getName()); // 클래스의 이름을 출력
		log.info(timeMapper.getTime()); // select 쿼리가 있는 메서드
	}
	
	@Test
	public void testGetTimeXML() {
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTimeXML());
//		INFO  net.kthcloud.persistence.DataSourceTests(testGetTimeXML76) - 2024-08-13 16:31:57
		
		// log4 JDBC 적용후 결과
		
//		|--------------------|
//		|sysdate             |
//		|--------------------|
//		|2024-08-13 16:41:43 |
//		|--------------------|
	}
	
}
