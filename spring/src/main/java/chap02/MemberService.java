package chap02;

/*
 * XXService 클래스는 로직을 담당하는 클래스
 * 컨트롤러가 특정 기능을 실행하기 위해 호출
 * 
 */
public class MemberService {

	MemberDao dao; //dao 객체를 주입받을 필드 
	//주입방법 ( 1. setter메서드			2.생성자)
	//세터메서드
	public MemberService setDao(MemberDao dao) {
		this.dao = dao;
		return this;
	}
	
	/*
	 * 회원 등록 기능을 수행하는 메서드
	 * 여기서는 XXDao의 어떤 메서드를 호출, 비즈니스 로직 수행
	 */
	public int insert(String name) {
		
		int r = dao.insert(name);
		return r;
	}
}
