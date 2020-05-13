package com.servlet.demo.annotion.model;

public class User {

	private Integer id;
	private String username;
	private String email;
	private String password;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String username, String email, String password) {

		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
