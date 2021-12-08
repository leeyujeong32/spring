package chap07.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {
	boolean insert(UserVo vo, HttpServletRequest req);
	int idcheck(String id);
	boolean login(UserVo vo, HttpSession sess);//로그인이 잘 됐을 경우 session에서

}
