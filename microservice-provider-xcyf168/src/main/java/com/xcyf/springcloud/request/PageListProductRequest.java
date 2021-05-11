package com.xcyf.springcloud.request;

import java.io.Serializable;

/**
 * @author liaojiamin
 * @Date:Created in 17:12 2021/5/11
 */
public class PageListProductRequest implements Serializable {
    private Integer page;
    private Integer pageSize;
    private Long userID;


    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
