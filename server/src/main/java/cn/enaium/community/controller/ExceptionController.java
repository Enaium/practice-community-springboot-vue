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

import cn.enaium.community.model.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Enaium
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    private Result<String> exception(HttpServletRequest request, Exception exception) {

        if (exception instanceof NullPointerException nullPointerException) {
            if (nullPointerException.getMessage().contains("com.fasterxml.jackson.databind.JsonNode.asText()")) {
                return Result.fail(Result.Code.PARAM_ERROR);
            }
        } else if (exception instanceof HttpMessageNotReadableException) {
            return Result.fail(Result.Code.PARAM_ERROR);
        }


        exception.printStackTrace();
        return Result.fail(Result.Code.FAIL);
    }
}
