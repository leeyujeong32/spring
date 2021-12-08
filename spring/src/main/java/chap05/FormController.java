package chap05;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {

	@GetMapping("/form.do")
	public String form() {
		return "form";
	}
	/*
	 * 파라미터를 받는 방법
	 * 1. HttpServletRequest 객체
	 * 2. @RequestParam
	 * 3. 커멘드객체
	 * - 파라미터의 이름과 동일한 필드에 자동으로 값을 넣어줌
	 */
	@PostMapping("/send.do")
	public String send(HttpServletRequest req, @RequestParam("email") String email,
			@RequestParam(value="tel", required = false) String tel,
			MemberVo vo) { //requestparam 알고있기
		String name = req.getParameter("name");
		req.setAttribute("msg", name+"님, 안녕하세요");
		req.setAttribute("msg2", "이메일 : "+email);
		req.setAttribute("tel", tel); //Required String parameter 'tel' is not present : tel이름의 파라미터가 존재하지 않다
		
//		MemberVo vo  = new MemberVo();
//		vo.setName(name);
//		vo.setEmail(email);
		
		System.out.println(vo.getName()+" "+vo.getEmail()+" "+vo.getNo());
		
		if(vo.getHobby() != null) {
			for(int i=0;i<vo.getHobby().length;i++) {
				System.out.println(vo.getHobby()[i]);
			}
			for(String hobby : vo.getHobby()) {
				System.out.println(hobby);
			}
		}
		//커맨드객체 없이 취미값을 vo2의 hobby에 저장하려면?
		MemberVo vo2 = new MemberVo();
		String[] hobbys = new String[req.getParameterValues("hobby").length];
		for(int i=0;i<req.getParameterValues("hobby").length;i++) {
			hobbys[i] = req.getParameterValues("hobby")[i];
		}
		
		
		return "send";
	}
	/*
	 * 뷰에서 사용하기 위한 값 컨트롤러에서 저장하는 방법
	 * -request에 set
	 * -session에 set
	 * -ModelAndView에 add
	 */
	//ModelAndView 객체 : model + view
	@GetMapping("/test9.do")
	public ModelAndView test8() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name","홍길동");
		mav.setViewName("test9");
		return mav;
		
	}
}
