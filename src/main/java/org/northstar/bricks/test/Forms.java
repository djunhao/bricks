package org.northstar.bricks.test;

import com.google.sitebricks.rendering.Decorated;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.iterator.ORecordIteratorClass;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.northstar.bricks.config.BricksConstants;
import org.northstar.bricks.web.components.Decorator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-18
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
@Decorated
public class Forms extends Decorator {
    final ODatabaseDocumentTx database;

    public Forms() {
        database = new ODatabaseDocumentTx ("local:databases/petshop");
        if (!database.exists()) {
            database.create();
        }
        database.open(BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        try {
            long cars = database.countClass("Animal");
            if (cars<1){
                ODocument animal = new ODocument("Animal");
                animal.field( "name", "Gaudi" );
                animal.field( "location", "Madrid" );
                animal.save();
            }
        } finally {
            database.close();
        }

    }

    public List<?> getList() {
        database.open(BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        try {
            ORecordIteratorClass<ODocument> result = database.browseClass("Animal");
            List<ODocument> animals = new ArrayList<ODocument>();
            for (ODocument doc : result) {
                animals.add(doc);
            }
            return animals;
        }  finally {
            database.close();
        }
    }
    @Override
    public String getPageTitle() {
        return "Test Page2";
    }
}
