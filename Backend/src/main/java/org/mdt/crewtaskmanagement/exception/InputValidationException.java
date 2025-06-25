package org.mdt.crewtaskmanagement.exception;

import java.util.List;

public class InputValidationException extends  BaseException{
    private static final long serialVersionUID = 1L;

    public InputValidationException(List<String> messages) {
        super(messages);
    }
}
