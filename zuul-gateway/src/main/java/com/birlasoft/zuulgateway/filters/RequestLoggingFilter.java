package com.birlasoft.zuulgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;


@Component
public class RequestLoggingFilter extends ZuulFilter {
    private static final Logger LOG = LoggerFactory.getLogger(RequestLoggingFilter.class);

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
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        requestContext.addZuulRequestHeader("Co-relation-Id", UUID.randomUUID().toString());
        LOG.info("Request arrived {} with URL {}", request.getMethod(), request.getRequestURI());
        logAllHeader(request);
        printBody(request);
        return requestContext;
    }

    private void logAllHeader(HttpServletRequest request) {
        Enumeration<String> headersNames = request.getHeaderNames();
        StringBuilder headerBuilder = new StringBuilder();
        while (headersNames.hasMoreElements()) {
            String headerName = headersNames.nextElement();
            Enumeration<String> headers = request.getHeaders(headerName);
            StringBuilder allHeaders = new StringBuilder();
            while (headers.hasMoreElements()) {
                allHeaders = new StringBuilder();
                allHeaders.append(headers.nextElement());
                allHeaders.append("; ");
            }
            headerBuilder.append(headerName).append(allHeaders.toString()).append("\r\n");
        }
        LOG.info("With Headers {}", headerBuilder.toString());
    }

    private void printBody(HttpServletRequest request) {
        if (request.getMethod().equalsIgnoreCase("POST")) {
            try {
                BufferedReader bufferedReader = request.getReader();
                bufferedReader.lines().forEach(System.out::println);

            } catch (IOException ioException) {
                LOG.info("Exception occured reading request body: {}", ioException.getMessage());
            }
        }
    }
}
