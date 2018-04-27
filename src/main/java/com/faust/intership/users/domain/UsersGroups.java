package com.faust.intership.users.domain;

import com.faust.intership.common.infastructure.AbstractEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity
public class UsersGroups extends AbstractEntity{
    private String name;

    @ManyToMany(mappedBy="userLists")
    Set<User> members;

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
