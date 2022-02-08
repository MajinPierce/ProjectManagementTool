package com.pierce.payload;

public class JWTLoginSuccessResponse {

	private boolean success;
	private String token;
	
	public JWTLoginSuccessResponse(boolean success, String token) {
		this.success = success;
		this.token = token;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return ("JWTLoginSuccessResponse{ " + 
				"success = " + success + 
				", token = '" + token + "'" + "}");
	}
	
	
}
