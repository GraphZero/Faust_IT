package com.faust.intership.users.domain;

import com.faust.intership.common.infastructure.AbstractEntity;
import com.faust.intership.users.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User extends AbstractEntity {
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private LocalDate birthdate;

    /**
     * User can have many groups, just as groups can have many users.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_User_Group",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_group_id")
            })
    private Set<UsersGroups> userGroups;

    public UserDto dto(){
        return UserDto.builder()
                .userName(username)
                .password(password)
                .firstName(firstName)
                .secondName(secondName)
                .birthdate(birthdate)
                .groupsNames(userGroups.stream().map(UsersGroups::getName).collect(Collectors.toSet()))
                .build();
    }

    /**
     * Users are the same if the username and id are the same. There shouldn't be 2 users with same username or id.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(getId(), id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, id);
    }

}
