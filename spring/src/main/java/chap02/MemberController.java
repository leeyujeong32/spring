package chap02;

/*
 * 컨트롤러가 하는 역할
 * 클라이언트의 요청을 받아서, 해당하는 로직을 처리(service), 화면을 응답(View)
 */
public class MemberController {
	MemberService service;
	
	//MemberService 객체를 주입받음
	public void setService(MemberService service) {
		this.service = service;
	}
	
	//등록폼에서 저장버튼을 클릭하면 전송되는 위치
	public String Insert(String name) {
		
		if(service.insert(name) > 0) {
			//등록 성공 시 처리
		}else {
			//등록 실패시 처리
			
		}
		return "응답";
	}
}
