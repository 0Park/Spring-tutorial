package com.example.filter2.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;

// Spring에서 bean으로 관리됨
@Slf4j
@WebFilter(urlPatterns = "/api/user/*")
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//         전처리 구간
//        br.line의 input stream에서 한번 읽으면 input stream pointer가 파일 끝을 가리켜서 다른 파일을 읽을 때 에러가 뜨게 된다.

        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper httpServletResponse =new ContentCachingResponseWrapper((HttpServletResponse) response);
//        BufferedReader br = httpServletRequest.getReader();
//        br.lines().forEach(line ->{
//            log.info("url:{} , line : {}",url,line);
//        });
        chain.doFilter(httpServletRequest,httpServletResponse);
        String url = httpServletRequest.getRequestURI();

//        후처리
//        request
        String reqContent =new String(httpServletRequest.getContentAsByteArray());
        log.info("request url:{ }, request body: {}",url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();
        // 로그에 나와있는 그대로 출력함
        httpServletResponse.copyBodyToResponse();
        log.info("response status: {},  responsebody: {}",httpStatus,resContent);
        int httpStatusCode = httpServletResponse.getStatus();


    }
}
