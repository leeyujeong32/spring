package chap07.user;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo{
	
	private int userno;
	private String id;
	private String pwd;
	private String name;
	private Timestamp regdate;
	private String checkId; //아이디 저장
	
	private String[] school;//3개의 값이 들어있단
	private String[] year;
	
	
	
}
