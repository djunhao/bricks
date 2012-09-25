package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.northstar.bricks.core.domain.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-18
 * Time: 上午10:46
 * To change this template use File | Settings | File Templates.
 */
public class About {
    @Inject
    private OObjectDatabaseTx database;
    public String getPageTitle() {
        return "系统介绍";
    }

    public List<Role> getRoles() {
        String queryString = "select from Role";
        OQuery<Role> command = new OSQLSynchQuery<Role>(queryString);
        List<Role> result = database.query(command);
        database.close();
        return result;
    }
}
