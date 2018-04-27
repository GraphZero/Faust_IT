package com.faust.intership.users.domain.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

@Value
@Builder
public class UserGroupsDto implements Serializable {
    private String groupName;
    private Set<UserDto> users;
}
