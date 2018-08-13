package com.zhr.student.common.result;

/**
 * 分页查询的结果
 *
 * @author Harry
 */
public class Result<T> {
    private T t;
    private Integer totalPages;

    public Result() {
    }

    public Result(T t) {
        this.t = t;
    }

    public Result(T t, Integer totalPages) {
        this.t = t;
        this.totalPages = totalPages;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public T getT() {
        return t;
    }
}
