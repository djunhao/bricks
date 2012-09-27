package org.northstar.bricks.config;

import com.google.inject.Scopes;
import com.google.sitebricks.SitebricksModule;
import com.google.sitebricks.SitebricksServletModule;
import org.northstar.bricks.web.auth.AuthFilter;
import org.northstar.bricks.web.auth.AuthModule;
import org.northstar.bricks.web.auth.LoginAction;
import org.northstar.bricks.web.auth.Logout;
import org.northstar.bricks.web.components.GuestbookNavigation;
import org.northstar.bricks.web.components.NewCard;
import org.northstar.bricks.web.components.Pager;
import org.northstar.bricks.core.dao.*;
import org.northstar.bricks.web.pages.*;
import org.northstar.bricks.test.Forms;
import org.northstar.bricks.test.Hello;
import org.northstar.bricks.web.service.DeleteUser;

/**
 * Configures a Sitebrick Module
 *
 * @author
 */
public class BricksModule extends SitebricksModule {
    @Override
    protected void configureSitebricks() {

        //install(new JpaPersistModule("myFirstJpaUnit"));

        //bind(FlashCache.class).to(HttpSessionFlashCache.class).asEagerSingleton();
        bind(EntryDao.class).to(SimpleEntryDao.class).in(Scopes.SINGLETON);
        bind(UserDao.class).to(OrientUserDao.class).in(Scopes.SINGLETON);
        bind(RoleDao.class).to(OrientRoleDao.class).in(Scopes.SINGLETON);

        //at("/static/default.css").export("bricks.css");
        //at("/static/pager.css").export("pager.css");

        /* Project related page, service and widget */
        at("/").show(Home.class).in(Scopes.SINGLETON);
        at("/home").show(Home.class);
        at("/login").show(Login.class).in(Scopes.SINGLETON);
        at("/loginAction").serve(LoginAction.class).in(Scopes.SINGLETON);
        at("/useradmin/delete/:id").serve(DeleteUser.class).in(Scopes.SINGLETON);
        at("/useradmin/edit/:id").show(EditUser.class).in(Scopes.SINGLETON);
        at("/useradmin/create").show(CreateUser.class).in(Scopes.SINGLETON);
        at("/about").show(About.class).in(Scopes.SINGLETON);

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

        install(new AuthModule());
    }

    @Override
    protected SitebricksServletModule servletModule() {
        return new SitebricksServletModule() {
            @Override
            protected void configurePreFilters() {
                //filter("/useradmin/*").through(AuthFilter.class);
            }
        };
    }
}