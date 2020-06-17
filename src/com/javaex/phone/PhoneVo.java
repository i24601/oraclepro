package com.javaex.phone;

public class PhoneVo {
	private int person_id;
	private String name, hp, company;
	
	public PhoneVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public PhoneVo(int person_id, String name, String hp, String company) {
		this.person_id = person_id;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
}
