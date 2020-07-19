package com.abc.lib.model;

public class ErrorResponse {

	private String type;
	private Integer status;
	private String title;
	private String message;
	private String uri;
	
	
	public ErrorResponse(String type, Integer status, String title, String message, String uri) {
		
		this.type = type;
		this.status = status;
		this.title = title;
		this.message = message;
		this.uri = uri;
	}


	public String getType() {
		return type;
	}


	public Integer getStatus() {
		return status;
	}


	public String getTitle() {
		return title;
	}


	public String getMessage() {
		return message;
	}


	public String getUri() {
		return uri;
	}
	
	
	
	
	
}
