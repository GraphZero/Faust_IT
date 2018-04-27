package com.faust.intership.users.application.validators;

import com.faust.intership.users.application.commands.AddUserCommand;

public interface IUserValidator {
    boolean validateInitialUserData(AddUserCommand addUserCommand);
}
