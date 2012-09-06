package org.northstar.bricks.components;

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

   /* @Inject
    private PagedDataSource source;

    @Get
    void render(){
        int availableRows = source.getTotalRowCount();

        maxPages = ((availableRows - 1) / maxPerPage) + 1;

    }*/

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
        // return !getNext().isEmpty();
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
}
