package org.northstar.bricks.pages;

import com.google.sitebricks.rendering.Decorated;

@Decorated
public class Flow extends Decorator {

	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	@Override
	public String getPageTitle() {
		return "Persist value页面";
	}
}
