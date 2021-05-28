package com.jsp.dto;

public class MemberVO {
	private String id;
	private String pwd;
	private String email;
	private String phone;

	public MemberVO(String id, String pwd, String email, String phone) {
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
	}

	public String takeId() {
		return this.id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
