package org.northstar.bricks.config;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-29
 * Time: 下午5:12
 * To change this template use File | Settings | File Templates.
 */
public interface URIContext {
    public static final String ROOT = "/";
    public static final String USER_ROOT = ROOT + "useradmin/";
    public static final String USER_CREATE = USER_ROOT + "create";
    public static final String USER_EDIT = USER_ROOT + "edit/:id";
    public static final String USER_DELETE = USER_ROOT + "delete/:id";
    public static final String LOGIN_PAGE = ROOT + "login";
    public static final String LOGOUT_ACTION = ROOT + "logout";
    public static final String ABOUT = ROOT + "about";
}
