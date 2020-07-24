package com.ljm.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liaojiamin
 * @Date:Created in 16:54 2020/7/9
 */
public class PreRequestLogFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(PreRequestLogFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURL().toString()));
        return null;
    }
}
