package chap06;

public class Parameter {
	private String searchType;
	private String searchWord;
	private String orderCond;
	private int startIdx; //parameter는 아니지만 모든 vo에 필요하기 때문에 여기에 입력 - limit 시작값
	private int page; //사용자가 요청한 페이지
	
	public Parameter() { //생성자
		//controller에서 BoardVo가 요청되는 순간 ->  page=1로 됨
		page = 1; //사용자가 요청했는데 0페이지일 순 없으니까 1로 초기화
		
	}
	
	public int getStartIdx() {
		return startIdx;
	}
	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getOrderCond() {
		return orderCond;
	}
	public void setOrderCond(String orderCond) {
		this.orderCond = orderCond;
	}
}
