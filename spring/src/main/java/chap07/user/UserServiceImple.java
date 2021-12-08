package chap07.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	UserDao dao;
	
	@Override
	@Transactional //
	public boolean insert(UserVo vo, HttpServletRequest req) {
		boolean r = false;
		
		int cnt = dao.insert(vo);
		//school 데이터 등록
		//UserVo 객체에는 userno가 저장된 상태
		//학교 정보 배열로 받아오기
		/*getParameterValues : Spring 배열 리턴
		String[] school = req.getParameterValues("school"); //파라미터는 다 String
		String[] year = req.getParameterValues("year");//커맨드객체를 쓰지 않으면 이런 방식으로 해야
		SchoolVo svo = new SchoolVo();
		svo.setUserno(vo.getUserno());
		
		for(int i=0;i<school.length;i++) {
			svo.setSchool(school[i]);
			svo.setYear(year[i]);
			cnt += dao.insertSchool(svo);
		
		}
		*/
		//배열필드 사용
		SchoolVo svo = new SchoolVo();
		svo.setUserno(vo.getUserno());//user 테이블에 저장
		//userno는 똑같기 때문에 반복문에 안넣어도 됨
		
		for(int i=0;i<vo.getSchool().length;i++) {//3개의 길이만큼 반복
			svo.setSchool(vo.getSchool()[i]);
			svo.setYear(vo.getYear()[i]);
			cnt += dao.insertSchool(svo);
		}
		if(cnt == vo.getSchool().length+1) { //cnt:배열의 길이+1
			r=true;
		}
		
		return r;
	}

	@Override
	public int idcheck(String id) {
		return dao.idcheck(id);
	}

	@Override
	public boolean login(UserVo vo, HttpSession sess) {//vo :id와 pwd
		UserVo uv = dao.login(vo); //uv :id와 pwd를 select한 객체
		if(uv != null) { //로그인 성공
			sess.setAttribute("loginInfo", uv);
			return true;
		}
		return false;
	}

	

}
