package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;
import org.northstar.bricks.dao.EntryDao;
import org.northstar.bricks.domain.Entry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
 
/**
 * Resource which represents a list of {@link Entry} instances
 *
 * @author David Linsin - linsin@synyx.de
 */
@Decorated
public class Guestbook extends Decorator {
    private List<Entry> entries;
    private Entry newEntry = new Entry();
 
    @Inject
    private EntryDao entryDao;
 
    public Guestbook(EntryDao argEntryDao) {
        entryDao = argEntryDao;
    }
 
    public Guestbook() {
    }

    @Override
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
