package org.northstar.bricks.services;

import com.google.sitebricks.http.Post;
import com.google.sitebricks.http.Put;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;

@Decorated
public class Hello extends Decorator {
    private String text = "initial textfield value";
    private String putMessage = "";

    public String getPutMessage() {
        return putMessage;
    }

    public void setPutMessage(String putMessage) {
        this.putMessage = putMessage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Put
    public void put() {
        putMessage = "Submitted via PUT";
    }

    @Post
    public void post() {
        putMessage = "Submitted via POST";
    }

    @Override
    public String getPageTitle() {
        return "Test put method";  //To change body of implemented methods use File | Settings | File Templates.
    }

}
