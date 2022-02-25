package com.pierce.exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class InvalidLoginResponse {
	
	private String username;
	private String password;
	
	public InvalidLoginResponse() {
		log.warn("This is coming from the Invalid Username Class");
		this.username = "Invalid Username";
		this.password = "Invalid Password";

	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
