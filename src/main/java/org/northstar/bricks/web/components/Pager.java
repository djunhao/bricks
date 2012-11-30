package org.northstar.bricks.web.components;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-15
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
public class Pager {
    private int page;
    private int maxPerPage;
    private int maxPages;

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isNextExists() {
        return page < maxPages;
    }

    public boolean isPrevExists() {
        return page > 1;
    }

    public void setMaxPerPage(int maxPerPage) {
        this.maxPerPage = maxPerPage;
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public int getMaxResults() {
        return (maxPages - 1) * maxPerPage + 1;
    }
}
