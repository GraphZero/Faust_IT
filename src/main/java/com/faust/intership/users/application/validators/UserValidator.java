package com.faust.intership.users.application.validators;

import com.faust.intership.users.application.commands.AddUserCommand;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserValidator implements IUserValidator {

    @Override
    public boolean validateInitialUserData(AddUserCommand addUserCommand) {
        if ( addUserCommand.getUsername() == null ||
                addUserCommand.getPassword() == null ||
                addUserCommand.getFirstName() == null ||
                addUserCommand.getSecondName() == null) return false;
        return
                addUserCommand.getUsername().length() < 30 &&
                addUserCommand.getPassword().length() > 8  &&
                addUserCommand.getBirthDate().isAfter(LocalDate.of(1900, 1, 1));
    }
}
