package com.eu.gsys.wma.domain.transformers;

public interface BaseTransformer<T, V> {

    T fromModel(V v);
    V toModel(T t);
}
