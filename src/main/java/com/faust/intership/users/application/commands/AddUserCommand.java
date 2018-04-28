package com.faust.intership.users.application.commands;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddUserCommand {
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private LocalDate birthDate;
}
