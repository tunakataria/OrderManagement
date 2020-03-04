package com.birlasoft.zuulgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


public class PreFilters extends ZuulFilter {
    @Override
    public String filterType() {
        return "Pre";
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
    public Object run() throws ZuulException {
        RequestContext requestContext =  RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        request .getHeader("Content-Type");
        requestContext.addZuulRequestHeader("Co-relation-Id", UUID.randomUUID().toString());
        //requestContext.setRequest(request);
        return  requestContext;
    }
}
