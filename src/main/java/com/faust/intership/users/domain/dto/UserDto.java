package com.faust.intership.users.domain.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Value
@Builder
public class UserDto implements Serializable {
    private String userName;
    private String password;
    private String firstName;
    private String secondName;
    private LocalDate birthdate;
    private Set<String> groupsNames;
}
