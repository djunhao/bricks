package org.northstar.bricks.data.dao;

import org.northstar.bricks.data.domain.Entry;

import java.util.List;

/**
 * Interface used to talk to persistent storage
 *
 * @author David Linsin - linsin@synyx.de
 */
public interface EntryDao {

    Integer save(Entry entry);

    Entry read(Integer id);

    List<Entry> readAll();

    void delete(Entry entry);

    int getListSize();
}
