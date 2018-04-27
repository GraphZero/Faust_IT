package com.faust.intership.users.application.commands;

import lombok.Value;

import java.time.LocalDate;

@Value
public class EditUserCommand {
    private long id;
    private String newUserName;
    private String newPassword;
    private String newFirstName;
    private String newSecondName;
    private LocalDate newBirthdate;
}
