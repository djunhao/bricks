package org.northstar.bricks.web.uri;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-28
 * Time: 下午10:20
 * To change this template use File | Settings | File Templates.
 */
public class URIContext {
    public final static String HOME = "/";
    public final static String USER_ROOT = HOME + "useradmin/";
    public final static String USER_EDIT = HOME + USER_ROOT + "edit/:id";
    public final static String USER_CREATE = HOME + USER_ROOT + "create";
    public final static String USER_DELETE = HOME + USER_ROOT + "delete/:id";
    public final static String LOGIN_PAGE = HOME + "login";
    public final static String LOGIN_ACTION = HOME + "loginAction";
    public final static String LOGOUT = HOME + "logout";
}
