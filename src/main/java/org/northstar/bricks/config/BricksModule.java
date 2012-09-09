package org.northstar.bricks.config;

import com.google.inject.name.Names;
import com.google.sitebricks.SitebricksModule;
import org.northstar.bricks.components.GuestbookNavigation;
import org.northstar.bricks.components.NewCard;
import org.northstar.bricks.components.Pager;
import org.northstar.bricks.dao.*;
import org.northstar.bricks.pages.*;
import org.northstar.bricks.services.Hello;
import org.northstar.bricks.services.LoginAction;
import org.northstar.bricks.services.Logout;

/**
 * Configures a Sitebrick Module
 *
 * @author
 */
public class BricksModule extends SitebricksModule {
    @Override
    protected void configureSitebricks() {

        //install(new JpaPersistModule("myFirstJpaUnit").addFinder(UserFinder.class));

        //bind(FlashCache.class).to(HttpSessionFlashCache.class).asEagerSingleton();
        bind(EntryDao.class).to(SimpleEntryDao.class);
        bind(UserDao.class).annotatedWith(Names.named("orientdb")).to(OrientUserDao.class);
        bind(RoleDao.class).to(OrientRoleDao.class);

        at("static/default.css").export("bricks.css");
        at("static/pager.css").export("pager.css");

        at("/").show(Home.class);
        at("/flow").show(Flow.class);
        at("/guestbook").show(Guestbook.class);
        at("/guestbook/:id").show(GuestbookEntry.class);
        at("/login").show(Login.class);
        at("/hello").show(Hello.class);
        at("/count").show(Count.class);

        at("/loginAction").serve(LoginAction.class);
        at("/logout").serve(Logout.class);

        embed(NewCard.class).as("Card");
        embed(GuestbookNavigation.class).as("navigation");
        embed(Pager.class).as("Pager");
    }
/*
    @Override
    protected SitebricksServletModule servletModule() {
        return new SitebricksServletModule() {
            @Override
            protected void configurePreFilters() {
                filter("*//*").through(PersistFilter.class);
            }
        };
    }*/

}