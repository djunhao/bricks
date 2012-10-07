package org.northstar.bricks.web.pages;

import com.google.inject.Singleton;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.web.components.Decorator;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-18
 * Time: 上午10:46
 * To change this template use File | Settings | File Templates.
 */
@Decorated
@Singleton
public class About extends Decorator {

    public String getPageTitle() {
        return "系统介绍";
    }

    public String getHello() {
        return "hello world!";
    }

}
