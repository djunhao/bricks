package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;
import org.northstar.bricks.dao.EntryDao;
import org.northstar.bricks.domain.Entry;

/**
 * Resource which represents a single Entry
 *
 * @author David Linsin - linsin@synyx.de
 */
public class GuestbookEntry {

    private Entry entry = new Entry();
    @Inject
    private EntryDao entryDao;

    @Get
    public void load(@Named("id") String argId) {
        entry = entryDao.read(Integer.valueOf(argId));
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    @Post
    public Guestbook delete(@Named("id") String argId) {
        load(argId);
        entryDao.delete(entry);
        return new Guestbook();
    }

    public String getPageTitle() {
        return "Entry #" + entry.getId();
    }

}
