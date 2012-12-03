package org.northstar.bricks.web.components;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-15
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
public class Pager {
    private int maxPerPage;
    private int maxResults;

    public int getMaxPages() {
        return (maxResults - 1) / maxPerPage + 1;
    }

    public void setMaxPerPage(int maxPerPage) {
        this.maxPerPage = maxPerPage;
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public int getMaxResults() {
        return maxResults;
    }
    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }
}
