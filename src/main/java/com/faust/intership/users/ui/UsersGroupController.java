package com.faust.intership.users.ui;

import com.faust.intership.users.application.UserGroupsService;
import com.faust.intership.users.domain.dto.UserGroupsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersGroupController {
    private final UserGroupsService userGroupsService;

    @RequestMapping(path = "/createUserGroup/{groupName}", method = RequestMethod.POST)
    public ResponseEntity<String> createUserGroup( @PathVariable final String groupName){
        userGroupsService.addUsersGroup(groupName);
        return ResponseEntity.ok("Created user group");

    }

    @RequestMapping(path = "/deleteUserGroup/{groupId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserGroup(@PathVariable final long groupId){
        if ( userGroupsService.deleteUsersGroup(groupId)){
            return ResponseEntity.ok("Deleted user group");
        } else{
            return new ResponseEntity<>("User group account doesn't exist.", HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = "/findAllUsersGroups", method = RequestMethod.GET)
    public ResponseEntity<List<UserGroupsDto>> findAllUsersGroups(){
        return ResponseEntity.ok(userGroupsService.findAllUsersGroups());
    }

    @RequestMapping(path = "/updateNameOfUserGroup", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateUserGroup(@RequestParam final long userGroupId, @RequestParam final String newUserGroupName){
        if ( userGroupsService.editUserGroupName(userGroupId, newUserGroupName)){
            return ResponseEntity.ok("Changed name of the user group.");
        } else{
            return new ResponseEntity<>("Couldn't edit user group.", HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = "/addUserToUserGroup", method = RequestMethod.POST)
    public ResponseEntity<String> addUserToUserGroup(@RequestParam final long userGroupId, @RequestParam final long userId){
        if ( userGroupsService.addUserToGroup(userGroupId, userId)){
            return ResponseEntity.ok("Successfully added user to group.");
        } else{
            return new ResponseEntity<>("Couldn't add user to group.", HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = "/deleteUserFromUserGroup", method = RequestMethod.POST)
    public ResponseEntity<String> deleteUserFromUserGroup(@RequestParam final long userGroupId, @RequestParam final long userId){
        if ( userGroupsService.deleteUserFromGroup(userGroupId, userId)){
            return ResponseEntity.ok("Successfully deleted user from group.");
        } else{
            return new ResponseEntity<>("Couldn't delete user from group.", HttpStatus.CONFLICT);
        }
    }

}