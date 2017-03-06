package com.mtsmda.word.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dminzat on 3/5/2017.
 */
@ControllerAdvice
public class GlobalExceptionHandlerControllerAdvice /*extends ResponseEntityExceptionHandler*/ {

    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleHttpError404(HttpServletRequest request, Exception e)  {
        System.out.println("e = " + e.getMessage());
        return new ModelAndView("404");
    }

}