package org.northstar.bricks.pages;import com.google.sitebricks.At;@At("/")public class Example {	private String message = "您好！";	private String name;	public String getMessage() {		return message;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}}