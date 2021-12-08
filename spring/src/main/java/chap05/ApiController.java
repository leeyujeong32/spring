package chap05;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*
 *  RestAPI 컨트롤러 
 */
@RestController
public class ApiController {

	@GetMapping("/api/test")
	public MemberVo test() {
		MemberVo vo = new MemberVo();
		vo.setName("홍길동");
		vo.setEmail("hong@gmail.com");
		vo.setNo(1);
		return vo;
	}
	
	/*
	 * 파라미터를 받는 4번째 방법
	 * @PathVariable
	   /api/list/1 -> 1페이지 - page=1, searchWord=자바
	   /api/list/2 -> 2페이지 - page=2, searchWord=파이썬
	 */
	
	@GetMapping("/api/list/{page}/{searchWord}")
	public List<MemberVo> list(@PathVariable int page, @PathVariable String searchWord){
		System.out.println("page : "+page);
		System.out.println("searchWord : "+searchWord);
		
		List<MemberVo> list = new ArrayList<MemberVo>();
		MemberVo vo = new MemberVo();
		vo.setName("홍길동");
		vo.setEmail("hong@gmail.com");
		vo.setNo(1);
		list.add(vo);
		list.add(vo);
		return list;
	}
}
