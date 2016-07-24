package com.altimetric.sample.service.domain;

public class ToDoTaskResult {
	
	private String message;
	
	public ToDoTaskResult(){
		
		
	}
	
	public ToDoTaskResult(String message){
		this.message = message;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
