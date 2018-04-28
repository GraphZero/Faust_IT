'use strict'
angular.module('home').controller('UserGroupController', function($scope, $http, $location, $window, $q, $timeout){

    $scope.addUser = function() {
        var absUrl = "/createUser";
        var addUserCommand = {
            username: $scope.username,
            password: $scope.password,
            firstName: $scope.firstName,
            secondName: $scope.secondName,
            birthDate: $scope.birthDate
        };
        var config = {
            method: 'POST',
            url: absUrl,
            accept: "application/json"
        };
        return $http.post(absUrl, addUserCommand, config)
            .then(
                function (response) {
                    console.log("Successfully added user.");
                    $location.path('/');
                },
                function (response) {
                    console.log("Couldnt add user." + response);
                });
    }
});