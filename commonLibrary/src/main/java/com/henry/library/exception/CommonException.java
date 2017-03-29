package com.henry.library.exception;

/**
 * 框架项目异常
 */
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CommonException() {

        super();
    }

    public CommonException(Throwable t) {

        super(t);
    }

    public CommonException(String message) {

        super(message);
    }

    public CommonException(String message, Throwable t) {

        super(message, t);
    }
}
