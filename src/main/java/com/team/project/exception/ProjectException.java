package com.team.project.exception;

import com.team.project.constant.ResultEnum;

/**
 * Self-defined exception.
 */
public class ProjectException extends RuntimeException {

    private Integer code = -2;

    public ProjectException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ProjectException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProjectException(String message) {
        super(message);
    }

    public ProjectException(Integer code, Throwable e) {
        super(e);
        this.code = code;
    }

    public ProjectException(Throwable e) {
        super(e);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
