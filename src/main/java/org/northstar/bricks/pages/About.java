package org.northstar.bricks.pages;

import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-18
 * Time: 上午10:46
 * To change this template use File | Settings | File Templates.
 */
@Decorated
public class About extends Decorator {

    @Override
    public String getPageTitle() {
        return "系统介绍";
    }
}
