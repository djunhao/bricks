package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.sitebricks.rendering.EmbedAs;
import org.northstar.bricks.dao.EntryDao;
import org.northstar.bricks.domain.Entry;

/**
 * Used as a teaser element in the entry site of a guestbook
 *
 * @author David Linsin - linsin@synyx.de
 */
@EmbedAs("navigation")
public class GuestbookNavigation {
    private Entry current;
    @Inject
    private EntryDao entryDao;

    public Entry getCurrent() {
        return current;
    }

    public void setCurrent(Entry argCurrent) {
        current = argCurrent;
    }

    public Entry getNext() {
        return entryDao.read(current.getId() + 1);
    }

    public Entry getPrevious() {
        return entryDao.read(current.getId() - 1);
    }

    public boolean isNextExists() {
        return current.getId() + 1 < entryDao.readAll().size();
    }

    public boolean isPreviousExists() {
        return current.getId() - 1 > 0;
    }
}
