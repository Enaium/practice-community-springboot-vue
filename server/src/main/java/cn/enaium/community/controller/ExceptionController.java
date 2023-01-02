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

package cn.enaium.community.controller;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.enaium.community.exception.KeyNotExistException;
import cn.enaium.community.exception.ValueNullException;
import cn.enaium.community.model.result.Result;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Enaium
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    private Result<String> exception(HttpServletRequest request, Exception exception) {


        if (exception instanceof HttpMessageNotReadableException || exception instanceof JsonParseException || exception instanceof KeyNotExistException || exception instanceof ValueNullException) {
            exception.printStackTrace();
            return Result.fail(Result.Code.PARAM_ERROR);
        } else if (exception instanceof NotLoginException) {
            return Result.fail(Result.Code.USER_NOT_LOGIN);
        } else if (exception instanceof NotPermissionException){
            return Result.fail(Result.Code.NO_PERMISSION);
        } else if (exception instanceof HttpRequestMethodNotSupportedException) {
            return Result.fail(Result.Code.METHOD_NOT_SUPPORT);
        }

        exception.printStackTrace();
        return Result.fail(Result.Code.FAIL);
    }
}
