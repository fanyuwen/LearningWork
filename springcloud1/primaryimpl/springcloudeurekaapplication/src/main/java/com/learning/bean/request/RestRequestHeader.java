package com.learning.bean.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author fanyuwen
 * @date 2019/7/2 10:14
 */
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class RestRequestHeader implements Serializable {
    private static final long serialVersionUID = -5480122073956108769L;
    private String app;
    private int pageSize;
    private int pageNum;
    private List<RestRequestSort> sorts;
    private String ext;

    public RestRequestHeader(String app) {
        this.app = app;
    }

    public RestRequestHeader() {
        this.app = "default";
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<RestRequestSort> getSorts() {
        return sorts;
    }

    public void setSorts(List<RestRequestSort> sorts) {
        this.sorts = sorts;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RestRequestHeader)) {
            return false;
        }
        RestRequestHeader other = (RestRequestHeader) obj;
        String other_app = other.app;
        if (other_app != app && (other_app == null || !other_app.equals(app))) {
            return false;
        }
        int other_pageSize = other.pageSize, other_pageNum = other.pageNum;
        if (other_pageSize != pageSize || other_pageNum != pageNum) {
            return false;
        }
        List<RestRequestSort> other_headers = other.sorts;
        if (other_headers != sorts && (other_headers == null || !other_headers.equals(sorts))) {
            return false;
        }
        String other_ext = other.ext;

        return other_ext == ext || other_ext != null && other_ext.equals(ext);
    }

    @Override
    public int hashCode() {
        int result = (1 << 4) + 1;
        final int value = (1 << 5) - 1;
        result = result * value + (app == null ? 0 : app.hashCode());
        result = result * value + pageSize;
        result = result * value + pageNum;
        result = result * value + (sorts == null ? 0 : sorts.hashCode());
        result = result * value + (ext == null ? 0 : ext.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RestRequestHeader(app=" + getApp() + ", pageSize=" +
                getPageSize() + ", pageNum=" + getPageNum() + ", sorts=" + getSorts() + ", ext=" + getExt() + ")";
    }
}