package chap07;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVo extends Parameter{ //Parameter를 상속
	private int boardno; //초기값 0
	private String title;
	private String content;
	private String writer;
	private Timestamp regdate;
	private String filename;
	private int userno;
	
	
}
