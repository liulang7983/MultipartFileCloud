package com.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ming.li
 * @date 2023/11/8 21:21
 */
@Component
public class ErrorFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

    /*过滤器类型
    pre
    routing
    post
    error
    */
    @Override
    public String filterType() {
        return "error";
    }
    /*顺序
     数字越小，优先级越高
     */
    @Override
    public int filterOrder() {
        return 0;
    }
    /*执行条件
      true 开启
      false 关闭
         */
    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("我是ErrorFilter");
        RequestContext rc = RequestContext.getCurrentContext();
        Throwable throwable = rc.getThrowable();
        System.out.println(throwable.getMessage());
        HttpServletRequest request = rc.getRequest();
        rc.setResponseStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        PrintWriter writer=null;
        try {
            writer=rc.getResponse().getWriter();
            writer.print("error:ErrorFilter ");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer!=null){
                writer.close();
            }
        }
        return null;
    }
}
