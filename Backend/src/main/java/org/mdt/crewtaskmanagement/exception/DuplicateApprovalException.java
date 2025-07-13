package org.mdt.crewtaskmanagement.exception;

import java.util.List;

public class DuplicateApprovalException extends BaseException{
    private static final long serialVersionUID = 1L;

    public DuplicateApprovalException(List<String> messages) {
        super(messages);
    }
}
