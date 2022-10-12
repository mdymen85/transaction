package com.platform.transactions.domain.exception;

public abstract class BaseClassException  extends RuntimeException {

    protected abstract String getCode();

}
