package org.northstar.bricks.pages;

import com.google.sitebricks.At;

@At("/test")
public class Test {
	private final String name;
		
	public Test(String name){
		this.name = name;
	}
	
	public String getName() {
		
		return name;
	}
}
