package com.imocha.common.advice;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.imocha.common.model.PageResponseModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class PageResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return Page.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse) {
        Page<Object> page = (Page<Object>) o;
        // serverHttpResponse.getHeaders().add("X-Total-Count",
        // String.valueOf(page.getTotalElements()));

        log.debug("page result:{}", page);
        PageResponseModel pageModel = new PageResponseModel();
        pageModel.setCurrentPage(page.getNumber());

        // change to resultsLength?
        pageModel.setTotalSize(page.getTotalElements());
        pageModel.setLimit(page.getSize());
        pageModel.setPayload(page.getContent());

        return pageModel;
    }

}
