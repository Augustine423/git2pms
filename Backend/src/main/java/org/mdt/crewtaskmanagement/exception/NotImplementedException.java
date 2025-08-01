package org.mdt.crewtaskmanagement.exception;

import java.util.ArrayList;
import java.util.List;

public class NotImplementedException extends BaseException {
    private static final long serialVersionUID = 1L;

    public NotImplementedException(String message) {
        this(List.of(message));
    }

    public NotImplementedException(List<String> messages) {
        super(messages);
    }
}
