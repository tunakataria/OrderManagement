package com.birlasoft.zuulgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class ResponseLoggingFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    Logger LOG = LoggerFactory.getLogger(ResponseLoggingFilter.class);

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        StringBuilder builder = new StringBuilder();
        HttpServletResponse httpServletResponse = requestContext.getResponse();
        listOf(httpServletResponse.getHeaderNames()).forEach(header -> {
            builder.append(httpServletResponse.getHeader(header) + " ;" + "\n");
        });
        Optional.ofNullable(requestContext.getResponseBody()).ifPresent(
                body -> builder.append(requestContext.getResponseBody() + "\n"));
        LOG.info("Response got from {}", builder);
        return null;
    }


    private List<String> listOf(Collection<String> collection) {
        List<String> list = new ArrayList<>(collection);
        return list;
    }

}

