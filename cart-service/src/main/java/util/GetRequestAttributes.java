package util;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public class GetRequestAttributes {

    public static String getAuthHeader(final HttpServletRequest request){
     return request.getHeader(HttpHeaders.AUTHORIZATION);
    }
}
