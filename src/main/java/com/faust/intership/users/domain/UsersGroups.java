package com.faust.intership.users.domain;

import com.faust.intership.common.infastructure.AbstractEntity;
import com.faust.intership.users.domain.dto.UserGroupsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Users_Groups")
public class UsersGroups extends AbstractEntity{
    private String name;

    @ManyToMany(mappedBy="userGroups")
    Set<User> members;

    public UsersGroups(String name) {
        this.name = name;
    }

    public UserGroupsDto dto(){
        return UserGroupsDto.builder()
                .groupName(name)
                .users(members.stream().map(User::dto).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersGroups)) return false;
        UsersGroups usersGroups = (UsersGroups) o;
        return Objects.equals(name, usersGroups.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
