package org.mdt.crewtaskmanagement.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ErrorPayload(LocalDateTime errorAt,String error,
                           String title,
                           List<String> messages) {
    public static ErrorPayload error( String title,String error, List<String> messages) {
        return new ErrorPayload(LocalDateTime.now(), error,title, messages);
    }
}
