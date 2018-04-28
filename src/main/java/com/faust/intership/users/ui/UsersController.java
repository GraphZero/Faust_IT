package com.faust.intership.users.ui;

import com.faust.intership.users.application.UsersCrudService;
import com.faust.intership.users.application.commands.AddUserCommand;
import com.faust.intership.users.application.commands.DeleteUserCommand;
import com.faust.intership.users.application.commands.EditUserCommand;
import com.faust.intership.users.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersCrudService usersCrudService;

    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody final AddUserCommand addUserCommand){
        System.out.println(addUserCommand);
        if ( usersCrudService.addUser(addUserCommand)){
            return ResponseEntity.ok("Created user");
        } else{
            return new ResponseEntity<>("Couldn't add user account.", HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestBody final DeleteUserCommand deleteUserCommand){
        if ( usersCrudService.deleteUser(deleteUserCommand)){
            return ResponseEntity.ok("Deleted user");
        } else{
            return new ResponseEntity<>("User account doesn't exist.", HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = "/findAllUsers", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return ResponseEntity.ok(usersCrudService.findAllUsers());
    }

    @RequestMapping(path = "/updateUser", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateUser(@RequestBody final EditUserCommand editUserCommand){
        if ( usersCrudService.editUser(editUserCommand)){
            return ResponseEntity.ok("Edited user");
        } else{
            return new ResponseEntity<>("Couldn't edit user account.", HttpStatus.CONFLICT);
        }
    }

}
