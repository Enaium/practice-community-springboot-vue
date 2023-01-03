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

    public Boolean getBoolean(K k) {
        return Boolean.parseBoolean(String.valueOf(get(k)));
    }

    public Integer getInt(K k) {
        return Integer.parseInt(String.valueOf(get(k)));
    }

    public Long getLong(K k) {
        return Long.parseLong(String.valueOf(get(k)));
    }

    public String getString(K k) {
        return (String) get(k);
    }

    public Boolean getBoolean(K k, V v) {
        return Boolean.parseBoolean(String.valueOf(getOrDefault(k, v)));
    }

    public Integer getInt(K k, V v) {
        return Integer.parseInt(String.valueOf(getOrDefault(k, v)));
    }

    public Long getLong(K k, V v) {
        return Long.parseLong(String.valueOf(getOrDefault(k, v)));
    }

    public String getString(K k, V v) {
        return (String) getOrDefault(k, v);
    }
    public boolean has(Object key) {
        return super.containsKey(key);
    }
}
