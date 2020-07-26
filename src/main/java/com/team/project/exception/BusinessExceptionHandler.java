package com.team.project.exception;

import com.team.project.constant.ResultEnum;
import com.team.project.dto.Result;
import com.team.project.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
public class BusinessExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ProjectException.class)
    public Result handleBusinessException(HttpServletRequest req, ProjectException e) {
        logger.error(req.getRequestURL() + " " + e.getMessage(), e);
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(HttpServletRequest req, IllegalArgumentException e) {
        logger.error(req.getRequestURL() + " " + e.getMessage(), e);
        return ResultUtil.error(ResultEnum.PARAMS_ERROR);
    }

    @ExceptionHandler(BindException.class)
    public Result handleBindException(HttpServletRequest req, BindException e) {
        logger.error(req.getRequestURL() + " " + e.getMessage(), e);
        return ResultUtil.error(ResultEnum.PARAMS_ERROR);
    }

    @ExceptionHandler(MultipartException.class)
    public Result handleMultipartException(HttpServletRequest req, MultipartException e) {
        logger.error(req.getRequestURL() + " " + e.getMessage(), e);
        return ResultUtil.error(ResultEnum.BIG_UPLOAD_FILE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
        logger.error(req.getRequestURL() + " " + e.getMessage(), e);
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        //自定义异常信息
        if (!StringUtils.isEmpty(defaultMessage)) {
            return ResultUtil.error(-103, defaultMessage);
        }
        return ResultUtil.error(ResultEnum.PARAMS_ERROR);
    }


    @ExceptionHandler(HttpMessageConversionException.class)
    public Result handleHttpMessageConversionException(HttpServletRequest req, HttpMessageConversionException e) {
        logger.error(req.getRequestURL() + " " + e.getMessage(), e);
        return ResultUtil.error(ResultEnum.PARAMS_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(HttpServletRequest req, MissingServletRequestParameterException e) {
        logger.error(req.getRequestURL() + " " + e.getMessage(), e);
        return ResultUtil.error(ResultEnum.PARAMS_ERROR);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public Result handleMissingServletRequestPartExceptionException(HttpServletRequest req, MissingServletRequestPartException e) {
        logger.error(req.getRequestURL() + " " + e.getMessage(), e);
        return ResultUtil.error(ResultEnum.PARAMS_ERROR);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        for (ConstraintViolation<?> item : violations) {
            logger.error("【Params_error】: {} ", req.getRequestURL() + " " + item.getMessage(), e);
            return ResultUtil.error(-103, item.getMessage());
        }
        return ResultUtil.error(ResultEnum.PARAMS_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(HttpServletRequest req, Exception e) {
        logger.error("【System_exception】: {} ", req.getRequestURL() + " " + e.getMessage(), e);
        return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }


}
