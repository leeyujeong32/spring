package chap01;

public class Greeter {

	private String name;
	
	public String greet() {
		return name +"님 안녕하세요";
	}
	
	public void setName(String name) { //setter
		this.name=name;
	}
}
