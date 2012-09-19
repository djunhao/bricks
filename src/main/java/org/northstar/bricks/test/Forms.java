package org.northstar.bricks.test;

import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-18
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
@Decorated
public class Forms extends Decorator {
    private String text = "initial textfield value";
    private String chosen = "(nothing)";
    private List<String> autobots = Arrays.asList("Bumblebee", "Ultra Magnus", "Optimus Prime", "Kup", "Hot Rod");

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getAutobots() {
        return autobots;
    }

    public void setAutobots(List<String> autobots) {
        this.autobots = autobots;
    }

    public String getChosen() {
        return chosen;
    }

    public void setChosen(String chosen) {
        this.chosen = chosen;
    }
    @Override
    public String getPageTitle() {
        return "form tests";
    }
}
