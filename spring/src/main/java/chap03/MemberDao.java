package chap03;

/*
 * XXDao는 데이터 처리를 위한 클래스
 * Dao(Data Access Object)
 * 예) insert, select, update, delete....
 */
public class MemberDao {

	//db에 회원 등록하는 메서드
	public int insert(String name) {
		//db에 저장
		System.out.println(name+" 저장!!!");
		return 1;
	}
}
