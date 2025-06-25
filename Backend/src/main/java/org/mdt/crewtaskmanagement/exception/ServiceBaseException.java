package org.mdt.crewtaskmanagement.exception;

import java.util.List;

public class ServiceBaseException extends BaseException{
    private static final long serialVersionUID = 1L;

    public ServiceBaseException(String message) {
        this(List.of(message));
    }

    public ServiceBaseException(List<String> messages) {
        super(messages);
    }
}
