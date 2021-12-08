package chap07.user;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import chap07.BoardService;
import chap07.BoardVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/user/regist.do")
	public String regist() {
		return "user/regist";
	}
	
	@PostMapping("/user/regist.do")
	public String regist(Model model, UserVo vo, HttpServletRequest req) {
		//System.out.println("등록전 userno :"+vo.getUserno());
		log.info("등록전 userno :"+vo.getUserno());
		boolean r = service.insert(vo, req);
		//System.out.println("등록후 user no : "+vo.getUserno());
		log.info("등록후 userno :"+vo.getUserno());
		if(r) {
			model.addAttribute("msg","정상적으로 가입되었습니다.");
			model.addAttribute("url","/spring/board/index.do");
		}else {
			model.addAttribute("msg","가입오류");
			model.addAttribute("url", "regist.do");
		}
		
		return "include/result";
	}
	
	@GetMapping("/user/idcheck.do")
	public String idcheck(Model model,@RequestParam String id) {
		int r = service.idcheck(id);
		model.addAttribute("ret",r);
		
		return "include/return";
	}
	
	@RequestMapping("/user/login.do")
	public String login(@CookieValue (value="cookieId", required=false) Cookie cookie,UserVo vo) { //cookie:객체값
		if(cookie != null) { //이전에 아이디 저장 체크하고 로그인한 경우
			vo.setId(cookie.getValue());
			vo.setCheckId("check");
			
		}
		
		return "/user/login";
	}
	
	@RequestMapping("/user/loginProcess.do")
	public String loginProcess(UserVo vo,HttpSession sess,Model model,HttpServletResponse res) {
		
		if(service.login(vo, sess)) { //로그인이 잘 됐을 떄
			//쿠키객체 생성
			Cookie cookie = new Cookie("cookieId",vo.getId());
			if("check".equals(vo.getCheckId())) { //사용자가 아이디저장 체크
				cookie.setMaxAge(60*60*24*30);//30일동안 보관
			}else {
				cookie.setMaxAge(0);
			}
			res.addCookie(cookie); //response객체에 쿠키 추가
			return "redirect:/board/index.do";
		}else {
			model.addAttribute("msg","아이디 비밀번호가 올바르지 않습니다.");
			model.addAttribute("url","login.do");
		}
		
		return "include/result";
	}
	
	@RequestMapping("/user/logout.do")
	public String logout(HttpSession sess, Model model) {
		
		//sess.removeAttribute("loginInfo");
		sess.invalidate();
		model.addAttribute("msg","로그아웃 되었습니다");
		model.addAttribute("url","/spring/board/index.do");
		
		return "include/result";
	}
	
	@RequestMapping("/user/mypage.do")
	public String mypage(Model model,HttpSession sess,BoardVo vo) { 
		vo.setUserno(((UserVo)sess.getAttribute("loginInfo")).getUserno());
		
		int totCount = boardService.count(vo); //총 게시물 수
		int totPage = totCount/10; //총 페이지 수
		if(totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10; //시작인덱스 구하기
		vo.setStartIdx(startIdx); //vo에 startIdx 넣어주기
		
//		String searchWord = req.getParameter("searchWord"); //request의 getParameter를 이용해 검색어를 받아옴
//		String searchType = req.getParameter("searchType"); /*vo로 받기 때문에 이제 사용 x */
		
		List<BoardVo> list = boardService.selectList(vo); //전달한 데이터가 list에 넣어짐 : list(selectList의 리턴값=ArrayList 객체)
		model.addAttribute("list", list); //이름, 리턴값 - 다른 곳에서 사용하기 위해서 저장
		model.addAttribute("totPage", totPage); //이름, 리턴값 - index.jsp에서 사용하기 위해 request에 넣기
//		model.addAttribute("word", searchWord); 
		return "user/mypage";
	}
}
