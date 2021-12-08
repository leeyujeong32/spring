package chap07;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import chap07.user.UserVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//스프리에서 매핑된 주소를 찾아서 메서드를 실행시켜서 jsp를 포워딩하는것
@Controller //component로 써도 돌아감
public class BoardController {

	@Autowired
	BoardService boardService; //BoardService가 부모타입
	
	@GetMapping({"/board/index.do","/user/mypage.do"}) //핸들러매핑이 찾기
	public String index(Model model, HttpServletRequest req, BoardVo vo, HttpSession sess) { //커맨드객체를 자동으로 바운딩?
		log.debug("1111");
		log.debug(req.getRequestURI());
		log.debug(req.getServletPath());
		//System.out.println(req.getRequestURI());
		//System.out.println(req.getServletPath());
		
		String view = "board/index";
		if("/user/mypage.do".equals(req.getServletPath())) {//마이페이지로 접속한 경우
			vo.setUserno(((UserVo)sess.getAttribute("loginInfo")).getUserno());
			view = "user/mypage";
		}
		
		//count는 mapping url이 따로 있으면 안됨
		int totCount = boardService.count(vo); //총 게시물 수
		int totPage = totCount/10; //총 페이지 수
		if(totCount % 10 > 0) totPage++;
		
		int startIdx = (vo.getPage()-1) * 10; //시작인덱스 구하기
		vo.setStartIdx(startIdx); //vo에 startIdx 넣어주기
		
//		String searchWord = req.getParameter("searchWord"); //request의 getParameter를 이용해 검색어를 받아옴
//		String searchType = req.getParameter("searchType"); /*vo로 받기 때문에 이제 사용 x */
		
		List<BoardVo> list = boardService.selectList(vo); //전달한 데이터가 list에 넣어짐 : list(selectList의 리턴값=ArrayList 객체)
		model.addAttribute("list", list); //이름, 리턴값 - 다른 곳에서 사용하기 위해서 저장
		model.addAttribute("totPage", totPage); //이름, 리턴값 - index.jsp에서 사용하기 위해 request에 넣기
//		model.addAttribute("word", searchWord); 
		return view;
		
	}
	
	@RequestMapping("/board/write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/board/insert.do") //write.jsp에서 post방식으로 전송했기 때문에 PostMapping만 가능
	public String insert(BoardVo vo, HttpServletRequest req, MultipartFile file, HttpSession sess) { //커맨드 객체방식으로(BoardVo vo), file과 jsp의 name이 같아야
		vo.setUserno(((UserVo)sess.getAttribute("loginInfo")).getUserno());
		
//		UserVo uv = (UserVo)sess.getAttribute("loginInfo");
//		int userno = uv.getUserno();
//		vo.setUserno(userno);
		//위에 줄과 같은 코드
		
		
		//파일 저장
		if(!file.isEmpty()) { //사용자가 파일을 첨부했다면
			try {
				String path = req.getRealPath("/upload/");
				String filename = file.getOriginalFilename(); //사용자가 첨부한 파일(원본) 이름
				file.transferTo(new File(path+filename)); //경로에 파일을 저장
				vo.setFilename(filename); //vo에 파일명 저장 - insert문에 넣어서 db에 저장할 수 있게
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		//vo 안에는 입력받은 값이 들어있다
		int r = boardService.insert(vo); //데이터가 들어있는 vo를 전달 - r=>등록된 갯수
		System.out.println("r : "+r);
		
		//정상적으로 등록되었습니다. alert
		//insert.do
		if(r > 0) {
			req.setAttribute("msg", "정상적으로 등록되었습니다."); //result.do에 msg와 url을 포워딩
			req.setAttribute("url", "index.do");
		}else {
			req.setAttribute("msg", "등록 오류"); //result.do에 msg와 url을 포워딩
			req.setAttribute("url", "write.do");
		}
		return "include/result"; //incluse/result.jsp로 리턴
		
	}
	
	@GetMapping("/board/detail.do")
	public String detail(Model model, @RequestParam int boardno) {
		model.addAttribute("data",boardService.selectOne(boardno));
		
		return "board/detail";
	}
	@GetMapping("/board/detail2.do")
	public String detail2(Model model, @RequestParam int boardno) {
		model.addAttribute("data",boardService.selectOne2(boardno)); //data, 조회한 행
		
		return "board/detail2";
	}
	
	@GetMapping("/board/edit.do")
	public String edit(Model model, @RequestParam int boardno) { //여러 값을 전송하기 위해서 커맨드 객체 사용
		model.addAttribute("data", boardService.selectOne(boardno));
		
		return "board/edit";
	}
	
	@PostMapping("/board/update.do")
	public String update(Model model, BoardVo vo) { //url를 보내고 : model 필요, 커맨드 객체 BoardVo vo
		int r = boardService.update(vo);
		if(r > 0) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url","detail.do?boardno="+vo.getBoardno());//성공했을 때 다시 상세 페이지
		}else {
			model.addAttribute("msg", "수정 오류"); //edit.jsp에서 boardno 값이 없기 떄문에 에러 -> input의 hidden을 이용해 boardno 전달
			model.addAttribute("url","edit.do?boardno="+vo.getBoardno()); // 실패했을 떄 수정페이지로 이동
		}
		return "include/result";
	}
	
	@GetMapping("/board/delete.do") //delete.do로 BoardVo를 전송하면 삭제 완료
	public String delete(Model model, BoardVo vo) { //url를 보내고 : model 필요, 커맨드 객체 BoardVo vo
		int r = boardService.delete(vo);
		if(r > 0) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url","index.do");//성공했을 때 목록 페이지
		}else {
			model.addAttribute("msg", "삭제 오류"); 
			model.addAttribute("url","detail.do?boardno="+vo.getBoardno()); // 실패했을 떄 상세페이지로 이동
		}
		return "include/result";
	}
}
