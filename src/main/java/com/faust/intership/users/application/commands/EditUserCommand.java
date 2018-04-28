package com.faust.intership.users.application.commands;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EditUserCommand {
    private long id;
    private String newUserName;
    private String newPassword;
    private String newFirstName;
    private String newSecondName;
    private LocalDate newBirthdate;
}
