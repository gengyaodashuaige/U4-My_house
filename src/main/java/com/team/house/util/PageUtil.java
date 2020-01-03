package com.team.house.util;


//封装分页的参数    针对easyUi
public class PageUtil {

    private int page;//因为前端传递的参数叫page
    private int rows;//封装页大小

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
