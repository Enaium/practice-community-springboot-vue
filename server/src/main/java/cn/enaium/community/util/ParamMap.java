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

package cn.enaium.community.util;

import java.util.Map;

/**
 * @author Enaium
 */
public class ParamMap<K, V> extends java.util.HashMap<K, V> {

    public ParamMap() {
    }

    public ParamMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    public Boolean getBoolean(K v) {
        return Boolean.parseBoolean(String.valueOf(get(v)));
    }

    public Integer getInt(K v) {
        return Integer.parseInt(String.valueOf(get(v)));
    }

    public Long getLong(K v) {
        return Long.parseLong(String.valueOf(get(v)));
    }

    public String getString(K v) {
        return String.valueOf(get(v));
    }
}
