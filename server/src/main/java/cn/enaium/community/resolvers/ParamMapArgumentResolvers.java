/*
 * Copyright (c) 2022 Enaium
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 */

package cn.enaium.community.resolvers;

import cn.enaium.community.annotation.RequestParamMap;
import cn.enaium.community.util.ParamMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * @author Enaium
 */
@Component
public class ParamMapArgumentResolvers implements HandlerMethodArgumentResolver {

    private final ObjectMapper objectMapper;

    public ParamMapArgumentResolvers(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private static boolean isParamMap(MethodParameter parameter) {
        return parameter.getParameterType() == ParamMap.class && parameter.hasParameterAnnotation(RequestParamMap.class);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return isParamMap(parameter);
    }

    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer mavContainer, @NotNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String content = nativeRequest.getReader().lines().collect(Collectors.joining());
        if (content.isBlank()) {
            content = "{}";
        }
        return objectMapper.readValue(content, ParamMap.class);
    }
}
