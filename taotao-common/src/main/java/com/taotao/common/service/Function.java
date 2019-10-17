package com.taotao.common.service;

/**
 * 回调函数，由于优化重复代码
 *
 * @param <T>
 * @param <E>
 */
public interface Function<T,E> {
    public T callback(E e);
}
