package com.zhr.student.common;

import com.github.pagehelper.Page;

import java.util.List;

public class MyPage<T> {
    private long total;
    private Integer pageNum;
    private Integer pageSize;
    private List<T> pageList;

    public MyPage() {
    }

    public MyPage(Page<?> page, List<T> ts) {
        this.total = page.getTotal();
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.pageList = ts;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
}
