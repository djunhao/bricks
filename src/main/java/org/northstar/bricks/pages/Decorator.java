package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.sitebricks.Show;
import com.google.sitebricks.headless.Request;

import java.util.Arrays;
import java.util.List;

@Show("Decorator.html")
abstract class Decorator {

    private static final List<String> PAGES = Arrays.asList("Home", "Flow", "About");

    public List<String> getPages() {
        //return page.getPageMap();
        return PAGES;
    }

    @Inject
    Request request;

    public String getPage(Request res) {

        return res.path();
    }

    protected Decorator() {
    }

    public abstract String getPageTitle();

}
