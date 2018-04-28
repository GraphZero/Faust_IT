package com.faust.intership.users.repositories;

import com.faust.intership.users.domain.UsersGroups;

import java.util.List;
import java.util.Optional;

public interface IUserGroupsRepository {
    UsersGroups save(UsersGroups user);
    void deleteById(long id);
    Optional<UsersGroups> findById(long id);
    List<UsersGroups> findAll();
}
