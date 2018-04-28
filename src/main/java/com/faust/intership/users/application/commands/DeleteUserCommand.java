package com.faust.intership.users.application.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteUserCommand {
    private long id;
}
