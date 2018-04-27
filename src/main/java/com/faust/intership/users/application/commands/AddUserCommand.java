package com.faust.intership.users.application.commands;

import lombok.Value;

import java.time.LocalDate;

@Value
public class AddUserCommand {
    private String userName;
    private String password;
    private String firstName;
    private String secondName;
    private LocalDate birthdate;
}
