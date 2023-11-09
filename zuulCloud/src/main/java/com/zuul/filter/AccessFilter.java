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
public class AccessFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

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
        return 1;
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
        System.out.println("我是AccessFilter");
        //此时会走ErrorFilter
        //Integer.valueOf("ss");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getParameter("token");
        System.out.println("我是AccessFilter,获取name:"+ctx.get("name"));
        if (token==null){
            //请求结束，不再继续走
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            PrintWriter writer=null;
            try {
                writer=ctx.getResponse().getWriter();
                writer.print("message:401");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (writer!=null){
                    writer.close();
                }
            }
        }else {
            System.out.println("有token");
        }
        return null;
    }
}
