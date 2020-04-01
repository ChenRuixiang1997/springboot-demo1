package com.kgc.exam.springbootdemo1.util;

import lombok.Data;

import java.util.Collection;

@Data
public class PageUtils<T> {

    private int currentPage;

    private int pageNo;

    private int pageSize;

    private long totalCount;

    private long totalPage = 0l;

    private Collection<T> currentList;

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageNo() {
        return pageNo * pageSize - pageSize;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalPage() {
        if (totalCount % pageSize == 0) {
            return totalCount / pageSize;
        } else {
            return totalCount / pageSize + 1;
        }
    }

    @Override
    public String toString() {
        return "PageUtils{" +
                "currentPage=" + currentPage +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currentList=" + currentList +
                '}';
    }
}
