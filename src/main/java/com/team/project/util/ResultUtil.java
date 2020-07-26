package com.team.project.util;


import com.team.project.constant.ResultEnum;
import com.team.project.dto.Result;

/**
 * result encapsulation util
 */
public class ResultUtil {

    public static Result success(Object object) {
        return new Result(ResultEnum.SUCCESS, object);
    }

    public static Result success() {
        return new Result(ResultEnum.SUCCESS);
    }

    public static Result error(Integer code, String message) {
        return new Result(code, message);
    }

    public static Result error(ResultEnum resultEnum) {
        return new Result(resultEnum);
    }

    public static Result error(String message) {
        return new Result(message);
    }

    public static Result error() {
        return new Result(ResultEnum.FAILURE);
    }

}
