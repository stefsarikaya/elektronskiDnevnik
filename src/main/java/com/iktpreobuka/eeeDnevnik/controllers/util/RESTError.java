package com.iktpreobuka.eeeDnevnik.controllers.util;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;

public class RESTError {
	
	@JsonView(Views.ucenik.class)
	private String message;
	
	@JsonView(Views.ucenik.class)
	private int code;
	
	public RESTError(int code, String message) {
		this.code= code;
		this.message= message;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}


}
