package com.faust.intership.users.application.validators;

import com.faust.intership.users.application.commands.AddUserCommand;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserValidator implements IUserValidator {

    @Override
    public boolean validateInitialUserData(AddUserCommand addUserCommand) {
        return
                addUserCommand.getUserName().length() < 30 &&
                addUserCommand.getPassword().length() > 8 && addUserCommand.getPassword().matches("[0-9]") &&
                !addUserCommand.getFirstName().matches("[0-9]") &&
                !addUserCommand.getSecondName().matches("[0-9]") &&
                addUserCommand.getBirthdate().isAfter(LocalDate.of(1900, 0, 1));
    }
}
