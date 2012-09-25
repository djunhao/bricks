package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.web.components.Decorator;
import org.northstar.bricks.core.dao.EntryDao;
import org.northstar.bricks.core.domain.Entry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Resource which represents a list of {@link Entry} instances
 *
 * @author David Linsin - linsin@synyx.de
 */
@Decorated
public class Guestbook extends Decorator {

    private List<Entry> entries = new ArrayList<>();
    private Entry newEntry = new Entry();
    @Inject
    private EntryDao entryDao;

    public String getPageTitle() {
        return "Guestbook Demo";
    }

    public List<Entry> getEntries() {
        return entries;
    }

    @Get
    public void load() {
        entries = entryDao.readAll();
    }

    @Post
    public String save() {
        newEntry.setDate(new Date());
        Integer id = entryDao.save(newEntry);
        return String.format("guestbook/%1$s", id);
    }

    public Entry getNewEntry() {
        return newEntry;
    }

    public void setNewEntry(Entry argNewEntry) {
        newEntry = argNewEntry;
    }

    public String getTime() {
        return new SimpleDateFormat().format(new Date());
    }
}
