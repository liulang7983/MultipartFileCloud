package com.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ming.li
 * @date 2023/11/8 21:21
 */
@Component
public class CustomFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    /*过滤器类型
    pre
    routing
    post
    error
    */
    @Override
    public String filterType() {
        return "pre";
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
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("我是CustomFilter");
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        System.out.println("Method："+request.getMethod());
        logger.info("Method："+request.getMethod()+",URL:"+request.getRequestURI().toString());
        return null;
    }
}
