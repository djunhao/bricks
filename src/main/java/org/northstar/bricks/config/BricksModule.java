package org.northstar.bricks.config;

import com.google.sitebricks.SitebricksModule;
import org.northstar.bricks.auth.AuthModule;
import org.northstar.bricks.auth.LoginAction;
import org.northstar.bricks.auth.Logout;
import org.northstar.bricks.components.GuestbookNavigation;
import org.northstar.bricks.components.NewCard;
import org.northstar.bricks.components.Pager;
import org.northstar.bricks.dao.*;
import org.northstar.bricks.pages.*;
import org.northstar.bricks.test.Forms;
import org.northstar.bricks.test.Hello;

/**
 * Configures a Sitebrick Module
 *
 * @author
 */
public class BricksModule extends SitebricksModule {
    @Override
    protected void configureSitebricks() {

        //install(new JpaPersistModule("myFirstJpaUnit"));
        //install(new OrientdbModule(BricksConstants.ORIENTDB_URL, BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD));
        install(new AuthModule());

        //bind(FlashCache.class).to(HttpSessionFlashCache.class).asEagerSingleton();
        bind(EntryDao.class).to(SimpleEntryDao.class);
        bind(UserDao.class).to(OrientUserDao.class);
        bind(RoleDao.class).to(OrientRoleDao.class);

        at("static/default.css").export("bricks.css");
        at("static/pager.css").export("pager.css");

        /* Project related page, service and widget */
        at("/").show(Home.class);
        at("/home").show(Home.class);
        at("/login").show(Login.class);
        at("/loginAction").serve(LoginAction.class);
        at("/useradmin/edit/:id").show(EditUser.class);
        at("/useradmin/create").show(CreateUser.class);
        at("/about").show(About.class);

        at("/logout").serve(Logout.class);

        embed(Pager.class).as("Pager");

        /** Testing page, service and widget */
        at("/flow").show(Flow.class);
        at("/guestbook").show(Guestbook.class);
        at("/guestbook/:id").show(GuestbookEntry.class);
        at("/hello").show(Hello.class);
        at("/count").show(Count.class);
        at("/forms").show(Forms.class);

        embed(NewCard.class).as("Card");
        embed(GuestbookNavigation.class).as("navigation");
    }
    /*
    @Override
    protected SitebricksServletModule servletModule() {
        return new SitebricksServletModule() {
            @Override
            protected void configurePreFilters() {
                filter("/*").through(AuthFilter.class);
            }
        };
    }
   */
}