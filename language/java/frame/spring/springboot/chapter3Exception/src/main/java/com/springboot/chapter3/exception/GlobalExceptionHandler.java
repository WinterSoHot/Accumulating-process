package com.springboot.chapter3.exception;


import com.springboot.chapter3.dto.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ModelAndView defaultException(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", e);
        mv.addObject("url", req.getRequestURL());
        return mv;
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public ErrorInfo myExceptionHandler(HttpServletRequest req, MyException e) throws Exception {
        ErrorInfo<String> errorInfo = new ErrorInfo<>();
        errorInfo.setCode(ErrorInfo.ERROR);
        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(req.getRequestURL().toString());
        errorInfo.setData("Error");
        return errorInfo;
    }
}
