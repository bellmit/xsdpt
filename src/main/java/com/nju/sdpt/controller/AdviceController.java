package com.nju.sdpt.controller;

import com.alibaba.fastjson.JSON;
import exception.BaseException;
import exception.HttpCode;
import exception.Result;
import exception.ResultUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author diaolin
 * @date 2020/04/17
 */
@Order
@ControllerAdvice
public class AdviceController{
    private Logger logger = LogManager.getLogger();



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    /** 异常处理 */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity<Result<Object>> exceptionHandler(Exception ex) {
        Result result = new Result();
        if (ex instanceof BaseException) {
            ((BaseException)ex).handler(result);
            //自定义异常只打印2行
            logger.error("此处发生了业务异常：\n==>{}\n==>{}", ex.getLocalizedMessage(),ex.getStackTrace()[0]);
        } else {
            logger.error("此处发生了未知异常：", ex);
            return ResultUtils.wrapFail(HttpCode.INTERNAL_SERVER_ERROR.value());
        }
        result.setTimestamp(System.currentTimeMillis());
        logger.info("response===>" + JSON.toJSON(result));

        return ResponseEntity.ok(result);
    }

//    @ExceptionHandler(value = {java.lang.NullPointerException.class})
//    @ResponseBody
//    public ResponseEntity<Result<Object>>  nullPointExceptionHandler(Exception e){
//        Result result = new Result<>();
//        logger.error("此处发生空指针异常异常：\n==>{}\n==>{}",e.getLocalizedMessage(),e.getStackTrace()[0]);
//        result.setTimestamp(System.currentTimeMillis());
//        logger.info("response===>"+JSON.toJSON(result));
//        return ResponseEntity.ok(result);
//    }
}
