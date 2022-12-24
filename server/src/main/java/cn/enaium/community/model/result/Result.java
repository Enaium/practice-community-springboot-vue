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

package cn.enaium.community.model.result;

import lombok.Data;

/**
 * @author Enaium
 */
@Data
public class Result<T> {
    private boolean success;
    private int code;
    private String message;

    private T content;

    public Result(boolean success, int code, String message, T content) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public static <T> Result<T> fail() {
        return new Result<>(false, Code.FAIL.code, Code.FAIL.message, null);
    }

    public static <T> Result<T> fail(Code code) {
        return new Result<>(false, code.code, code.message, null);
    }

    public static <T> Result<T> success() {
        return new Result<>(true, Code.SUCCESS.getCode(), Code.SUCCESS.message, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, Code.SUCCESS.getCode(), Code.SUCCESS.message, data);
    }

    public static Result<String> success(Code code) {
        return new Result<>(true, code.getCode(), code.message, null);
    }

    public enum Code {
        SUCCESS(200, "success"),
        FAIL(999, "Fail"),
        PARAM_ERROR(1001, "Param Error"),
        USER_NOT_LOGIN(2001, "User not login"),
        USERNAME_ALREADY_EXIST(2002, "Username already exist"),
        USERNAME_NOT_EXIST(2003, "Username not exist"),
        PASSWORD_NOT_MATCH(2004, "Password not match"),
        USERNAME_IS_BLANK(2005, "Username is blank"),
        PASSWORD_IS_BLANK(2005, "Password is blank");

        private final int code;
        private final String message;

        Code(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getMessageByCode(int code) {
            for (Code value : values()) {
                if (value.getCode().equals(code)) {
                    return value.getMessage();
                }
            }
            return null;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
