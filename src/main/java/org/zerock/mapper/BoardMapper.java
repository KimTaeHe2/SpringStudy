package org.zerock.mapper; // db와 영속성을 가진 패키지

import java.util.List;

// import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

public interface BoardMapper {
	// 인터페이스로 선언한 이유는 추상 메서드와 xml을 결합하여 구현 클래스를 사용하는 마이바티스이기 때문
	// xml을 생성할 때는 resources안에 폴더를 계층별로 만들고 파일명을 인터페이스와 똑같이 xml을 생성
	// 인터페이스에 자체적인 추상메서드를 활용
	// @Select("select * from tbl_board where bno > 0") // where bno > 0 조건을 건 이유는 bno가 pk라 인덱싱이 되어 있어 빠름
	public List<BoardVO> getList(); // 인터페이스안에는 추상메서드임.
	// 리턴은 List<BoardVO> 임으로 배열 안쪽에 객체가 BoardVO로 완성된다.
	
	
	// board 삽입용 코드
	public void insert(BoardVO board);
	
	// 삽입할 번호를 먼저 파악후 게시물 등록
	public void insertSelectKey(BoardVO board);
	
	// 게시물의 번호를 받아 객체를 출력한다.
	public BoardVO read(long bno);
	
	// 게시물의 번호를 받아 객체를 수정한다.
	public int update(BoardVO boardVO);
	
	// 게시물의 번호를 받아 객체를 삭제한다.
	public int delete(long bno);
}
