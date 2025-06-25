package org.mdt.crewtaskmanagement.exception.jwt;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenExpiredException extends AuthenticationException {
   private static final long serialVersionUID = 1L;

   public JwtTokenExpiredException(String message) {
       super(message);
   }
   public JwtTokenExpiredException(String message, Throwable cause) {
       super(message, cause);
   }
}
