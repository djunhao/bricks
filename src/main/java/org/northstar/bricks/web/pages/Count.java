package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.web.components.Decorator;
import org.northstar.bricks.test.Counter;

/**
 * Created with IntelliJ IDEA.
 * User: jenny
 * Date: 12-8-16
 * Time: 下午11:29
 * To change this template use File | Settings | File Templates.
 */
@Decorated
public class Count extends Decorator {
    final Counter counter;

    @Inject
    public Count(Counter counter) {
        this.counter = counter;
    }

    public int getCount() {
        return counter.increment();
    }

    @Override
    public String getPageTitle() {
        return "@SessionScoped Counter";  //To change body of implemented methods use File | Settings | File Templates.
    }
}
