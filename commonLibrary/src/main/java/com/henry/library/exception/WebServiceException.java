package com.henry.library.exception;

/**
 * Web服务异常
 */
public class WebServiceException extends CommonException {

    private static final long serialVersionUID = 1L;

    public WebServiceException() {

        super();
    }

    public WebServiceException(Throwable t) {

        super(t);
    }

    public WebServiceException(String message) {

        super(message);
    }

    public WebServiceException(String message, Throwable t) {

        super(message, t);
    }
}
