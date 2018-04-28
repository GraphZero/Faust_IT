package com.faust.intership.users.application;

import com.faust.intership.users.domain.UsersGroups;
import com.faust.intership.users.domain.dto.UserGroupsDto;
import com.faust.intership.users.repositories.IUserGroupsRepository;
import com.faust.intership.users.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserGroupsService {
    private final IUserGroupsRepository userGroupsRepository;
    private final IUserRepository userRepository;

    public void addUsersGroup(String userGroupName) {
        userGroupsRepository.save(new UsersGroups(userGroupName));
        log.info("Successfully added user group.");
    }

    public boolean deleteUsersGroup(long id) {
        return userGroupsRepository
                .findById(id)
                .map(usersGroupToEdit -> {
                    userGroupsRepository.deleteById(id);
                    log.info("Successfully deleted user group.");
                    return true;
                })
                .orElseGet(() -> {
                    log.info("Couldn't delete non existing user group.");
                    return false;
                });
    }

    public List<UserGroupsDto> findAllUsersGroups() {
        return userGroupsRepository
                .findAll()
                .stream()
                .map(UsersGroups::dto)
                .collect(Collectors.toList());
    }

    public boolean editUserGroupName(long id, String newGroupName) {
        return userGroupsRepository
                .findById(id)
                .map(usersGroupToEdit -> {
                    usersGroupToEdit.setName(newGroupName);
                    log.info("Changed group " + id + " name to: " + newGroupName);
                    return true;
                })
                .orElseGet(() -> {
                    log.info("Couldn't edit name of the non existing user group.");
                    return false;
                });
    }

    public boolean addUserToGroup(long id, long userId) {
        return userRepository
                .findById(userId)
                .map(user ->
                        userGroupsRepository
                                .findById(id)
                                .map(usersGroup -> {
                                    usersGroup.getMembers().add(user);
                                    log.info("Added user to group " + usersGroup.getName());
                                    return true;
                                })
                                .orElseGet(() -> {
                                    log.info("Couldn't add user to the non existing user group.");
                                    return false;
                                }))
                .orElseGet(() -> {
                    log.warn("Couldn't find user with that id.");
                    return false;
                });
    }

    public boolean deleteUserFromGroup(long id, long userId) {
        return userRepository
                .findById(userId)
                .map(user ->
                        userGroupsRepository
                                .findById(id)
                                .map(usersGroup -> {
                                    if (usersGroup.getMembers().remove(user)) {
                                        log.info("Deleted user from group " + usersGroup.getName());
                                        return true;
                                    } else {
                                        log.warn("No such user in group " + usersGroup.getName());
                                        return false;
                                    }
                                })
                                .orElseGet(() -> {
                                    log.info("Couldn't add user to the non existing user group.");
                                    return false;
                                }))
                .orElseGet(() -> {
                    log.warn("Couldn't find user with that id.");
                    return false;
                });
    }

}
