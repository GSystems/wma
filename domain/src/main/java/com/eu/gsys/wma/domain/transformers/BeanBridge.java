package com.eu.gsys.wma.domain.transformers;

public abstract class BeanBridge<M, T> {

	public abstract M toModel(T t);
	public abstract T fromModel(M m);
}
