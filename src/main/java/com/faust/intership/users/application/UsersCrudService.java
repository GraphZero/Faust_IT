package com.faust.intership.users.application;

import com.faust.intership.users.application.commands.AddUserCommand;
import com.faust.intership.users.application.commands.DeleteUserCommand;
import com.faust.intership.users.application.commands.EditUserCommand;
import com.faust.intership.users.application.validators.IUserValidator;
import com.faust.intership.users.domain.User;
import com.faust.intership.users.domain.dto.UserDto;
import com.faust.intership.users.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersCrudService {
    private final IUserValidator userValidator;
    private final IUserRepository userRepository;

    public boolean addUser(AddUserCommand addUserCommand) {
        if (userValidator.validateInitialUserData(addUserCommand)) {
            User userEntity =
                    User
                            .builder()
                            .userName(addUserCommand.getUserName())
                            .password(addUserCommand.getPassword())
                            .firstName(addUserCommand.getFirstName())
                            .secondName(addUserCommand.getSecondName())
                            .birthdate(addUserCommand.getBirthdate())
                            .build();
            userRepository.save(userEntity);
            log.info("Successfully saved user.");
            return true;
        } else {
            log.warn("Couldn't save user.");
            return false;
        }
    }

    /**
     * Returns false if there is no such user.
     *
     * @param deleteUserCommand
     * @return
     */
    public boolean deleteUser(DeleteUserCommand deleteUserCommand) {
        return userRepository
                .findById(deleteUserCommand.getId())
                .map(userToEdit -> {
                    userRepository.deleteById(userToEdit.getId());
                    log.info("Successfully deleted user.");
                    return true;
                })
                .orElseGet(() -> {
                    log.info("Couldn't delete non existing user.");
                    return false;
                });
    }

    public List<UserDto> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(User::dto)
                .collect(Collectors.toList());
    }

    public boolean editUser(EditUserCommand editUserCommand) {
        return userRepository
                .findById(editUserCommand.getId())
                .map(userToEdit -> {
                            if (editUserFields(userToEdit, editUserCommand)) {
                                log.info("Successfully edited user with id: " + userToEdit.getId());
                                return true;
                            } else {
                                return false;
                            }
                        }
                )
                .orElse(false);
    }

    private boolean editUserFields(User userToEdit, EditUserCommand editUserCommand) {
        AddUserCommand tempUserCommand = new AddUserCommand(
                editUserCommand.getNewUserName(),
                editUserCommand.getNewPassword(),
                editUserCommand.getNewFirstName(),
                editUserCommand.getNewSecondName(),
                editUserCommand.getNewBirthdate());

        if (userValidator.validateInitialUserData(tempUserCommand)) {
            userToEdit.setUserName(editUserCommand.getNewUserName());
            userToEdit.setPassword(editUserCommand.getNewPassword());
            userToEdit.setFirstName(editUserCommand.getNewFirstName());
            userToEdit.setSecondName(editUserCommand.getNewSecondName());
            userToEdit.setBirthdate(editUserCommand.getNewBirthdate());
            return true;
        } else {
            return false;
        }

    }

}
