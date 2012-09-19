package org.northstar.bricks.test;

import com.google.inject.servlet.SessionScoped;

/**
 * Created with IntelliJ IDEA.
 * User: jenny
 * Date: 12-8-16
 * Time: 下午11:27
 * To change this template use File | Settings | File Templates.
 */
@SessionScoped
public class Counter {
    int count = 0;

    public synchronized int increment() {
        return count++;
    }
}
