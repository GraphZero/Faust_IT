package com.faust.intership.users.repositories;

import com.faust.intership.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends IUserRepository, JpaRepository<User, Long> {
}
