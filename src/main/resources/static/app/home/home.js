'use strict'
var app = angular.module('home', ['ngRoute', 'ngAnimate']);

app.config(function($routeProvider) {
    $routeProvider
        .when("/manageUsers", {
            templateUrl : "/app/users/users.htm",
            controller: 'UsersController'
        })
        .when("/manageUsersGroups", {
            templateUrl : "/app/groups/userGroups.htm",
            controller: 'UserGroupController'
        })
        .when("/addUser", {
            templateUrl : "/app/users/addUser.html",
            controller: 'UsersController'
        })
        .when("/deleteUser", {
            templateUrl : "/app/users/deleteUser.html",
            controller: 'UsersController'
        })
        .when("/editUser", {
            templateUrl : "/app/users/editUser.html",
            controller: 'UsersController'
        })
        .when("/allUsers", {
            templateUrl : "/app/users/listUsers.html",
            controller: 'UsersController'
        })
        .when("/addUserGroup", {
            templateUrl : "/app/groups/addUserGroup.html",
            controller: 'UserGroupController'
        })
        .when("/deleteUserGroup", {
            templateUrl : "/app/groups/deleteUserGroup.html",
            controller: 'UserGroupController'
        })
        .when("/editUserGroup", {
            templateUrl : "/app/groups/editUserGroup.html",
            controller: 'UserGroupController'
        })
        .when("/listUsersGroups", {
            templateUrl : "/app/groups/listUsersGroups.html",
            controller: 'UserGroupController'
        })
        .otherwise({
            templateUrl : "/app/home/main.htm",
            controller: 'UserGroupController'
        });

});