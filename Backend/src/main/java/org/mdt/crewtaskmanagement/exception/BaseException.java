package org.mdt.crewtaskmanagement.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class BaseException extends Exception {
    private static final long serialVersionUID = 1L;

    private List<String> messages;

    public BaseException(List<String> messages) {
        this.messages = messages;
    }

    public BaseException() {
    }
}
