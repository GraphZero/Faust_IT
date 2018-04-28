package com.faust.intership.users.repositories;

import com.faust.intership.users.domain.UsersGroups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserGroupsRepository extends IUserGroupsRepository, JpaRepository<UsersGroups, Long> {
}
