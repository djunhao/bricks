package org.northstar.bricks.test;

import com.google.sitebricks.http.Patch;
import com.google.sitebricks.http.Post;
import com.google.sitebricks.http.Put;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.web.components.Decorator;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-10-2
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
@Decorated
public class Test extends Decorator {
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

    @Patch
    public void patch() {
        putMessage = "Submitted via PATCH";
    }

    @Post
    public void post() {
        putMessage = "Submitted via POST";
    }

    public String getPageTitle() {
        return "Form CRUD";
    }

    private static final List<String> NAMES = Arrays.asList("Dhanji", "Josh", "Jody", "Iron Man");

    //property returns a list of names
    public List<String> getNames() {
        return NAMES;
    }

    //try a set this time, returns movies (to demo nested repeat)
    public Set<Movie> getMovies() {
        return new HashSet<Movie>(Arrays.asList(new Movie(), new Movie(), new Movie()));
    }

    public Collection<String> getThings() {
        return NAMES;
    }

    public static class Movie {

        //try a collection this time. same as property Repeat.getNames() from the outer class
        public Collection<String> getActors() {
            return NAMES;
        }
    }
}
