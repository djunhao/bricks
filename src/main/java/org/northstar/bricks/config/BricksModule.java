package org.northstar.bricks.config;

import com.google.inject.Scopes;
import com.google.inject.multibindings.Multibinder;
import com.google.sitebricks.SitebricksModule;
import com.google.sitebricks.SitebricksServletModule;
import com.google.sitebricks.conversion.Converter;
import com.google.sitebricks.conversion.DateConverters;
import org.northstar.bricks.core.dao.*;
import org.northstar.bricks.core.orientdb.OrientdbFilter;
import org.northstar.bricks.test.Forms;
import org.northstar.bricks.test.Hello;
import org.northstar.bricks.test.Test;
import org.northstar.bricks.web.auth.AuthModule;
import org.northstar.bricks.web.auth.Login;
import org.northstar.bricks.web.auth.Logout;
import org.northstar.bricks.web.components.GuestbookNavigation;
import org.northstar.bricks.web.components.NewCard;
import org.northstar.bricks.web.components.Pager;
import org.northstar.bricks.web.pages.*;
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
        at(URIContext.ROOT).show(Home.class);
        /*at("/home.html").show(Home.class);*/
        at(URIContext.LOGIN_PAGE).show(LoginPage.class);
        at(URIContext.USER_EDIT).show(EditUser.class);
        at(URIContext.USER_CREATE).show(CreateUser.class);
        at(URIContext.ABOUT).show(About.class);

        at(URIContext.USER_DELETE).serve(DeleteUser.class);
        at(URIContext.LOGIN_ACTION).serve(Login.class);
        at(URIContext.LOGOUT_ACTION).serve(Logout.class);

        embed(Pager.class).as("Pager");

        /** Testing page, service and widget */
        at("/flow").show(Flow.class);
        at("/guestbook").show(Guestbook.class);
        at("/guestbook/:id").show(GuestbookEntry.class);
        at("/hello").show(Hello.class);
        at("/count").show(Count.class);
        at("/forms").show(Forms.class);
        at("/test").show(Test.class);

        embed(NewCard.class).as("Card");
        embed(GuestbookNavigation.class).as("navigation");

        install(new AuthModule());

        Multibinder<Converter> converters = Multibinder.newSetBinder(binder(), Converter.class);
        converters.addBinding().toInstance(new DateConverters.DateStringConverter("yyyy-MM-dd"));
    }

    @Override
    protected SitebricksServletModule servletModule() {
        return new SitebricksServletModule() {
            @Override
            protected void configurePreFilters() {
                filter("/*").through(OrientdbFilter.class);
            }
        };
    }
}