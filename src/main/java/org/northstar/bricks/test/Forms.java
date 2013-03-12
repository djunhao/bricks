package org.northstar.bricks.test;

import com.google.sitebricks.Show;
import com.google.sitebricks.rendering.Decorated;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.iterator.ORecordIteratorClass;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import org.northstar.bricks.config.BricksConstants;
import org.northstar.bricks.web.components.Decorator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-18
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
@Decorated
@Show("Forms.mvel")
public class Forms extends Decorator {
    final ODatabaseDocumentTx database;

    public Forms() {
        database = new ODatabaseDocumentTx("remote:localhost/petshop");
        //if (!database.exists()) {
        //    database.create();
        //}
    }

    public List<?> getList() {
        database.open(BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        try {
            ORecordIteratorClass<ODocument> result = database.browseClass("Animal");
           /* List<Map> animals = new ArrayList<Map>();
            Map<String, Object> test = new HashMap<String, Object>();
            for (ODocument doc : result) {
                //animals.add(doc);
                test.put("name", doc.field("name"));
                test.put("location", doc.field("location"));
                animals.add(test);
                //System.out.println(doc);
            }
            return animals;*/
            List<ODocument> list = new ArrayList<ODocument>();
            for (ODocument doc : result) {
                list.add(doc);
            }
            return list;

        } finally {
            //database.close();
        }
    }

    @Override
    public String getPageTitle() {
        return "Test Page2";
    }
}
