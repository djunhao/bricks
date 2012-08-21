package org.northstar.bricks.components;

import com.google.sitebricks.Show;

import java.util.Arrays;
import java.util.List;

@Show("Decorator.html")
public abstract class Decorator {
    private final List<String> PAGES;

    protected Decorator() {
        PAGES = Arrays.asList("Home", "Flow", "About");
    }

    public List<String> getPages(){
		return PAGES;
	}

	public abstract String getPageTitle();
}
