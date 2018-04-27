package com.faust.intership.users.repositories;

import com.faust.intership.users.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    User save(User user);
    void deleteById(long id);
    Optional<User> findById(long id);
    List<User> findAll();
}
