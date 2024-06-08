package kr.or.ddit.reflect;

import java.io.Serializable;

public class SampleVo implements Serializable {
	public String id;
	protected String name;
	private int age;
	
	public SampleVo(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public SampleVo() {
		// TODO Auto-generated constructor stub
	}

	public String getId() throws Exception {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "SampleVo [아이디=" + id + ", 이름=" + name + ", 나이=" + age + "]";
	}
	
	
	
	
}
