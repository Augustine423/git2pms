package org.mdt.crewtaskmanagement.dto.crew;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String education;
}
