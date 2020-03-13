package com.birlasoft.zuulgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Component
public class LoggingFilter extends ZuulFilter {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);
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
    public Object run() throws ZuulException {
        RequestContext requestContext =  RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        requestContext.addZuulRequestHeader("Co-relation-Id", UUID.randomUUID().toString());
        LOG.info("Request arrived {} with URL {}", request, request.getRequestURI());
        return  requestContext;
    }
}
