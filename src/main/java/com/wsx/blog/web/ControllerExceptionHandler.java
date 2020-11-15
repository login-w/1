package com.wsx.blog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//拦截一切controller注解的访问
@ControllerAdvice
public class ControllerExceptionHandler {
//    获取一个slf4j日志 对象
    private Logger logger= LoggerFactory.getLogger(this.getClass());
//    声明为异常处理的方法
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e){
//        创建一个modelAndView对象 该对象可以存储发给前端的数据，以及具体跳转的页面
        ModelAndView mv =new ModelAndView();
//        把错误信息存储到日志中
        logger.error("Request url : {},Exception : {}",request.getRequestURL(),e);
//        把错误信息存储到mv中 发到前端，前端可以直接在页面，查看那个地方出错了，便于快速找到错误的地方、并修改
        mv.addObject("url",request.getRequestURL());
        mv.addObject("Exception",e);
        mv.setViewName("error/error");
        return mv;
    }

}
