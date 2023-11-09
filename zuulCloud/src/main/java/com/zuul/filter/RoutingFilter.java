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
public class RoutingFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(RoutingFilter.class);

    /*过滤器类型
    pre
    routing
    post
    error
    */
    @Override
    public String filterType() {
        return "route";
    }
    /*顺序
     数字越小，优先级越高
     */
    @Override
    public int filterOrder() {
        return 3;
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
        System.out.println("我是RoutingFilter");
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        return null;
    }
}
