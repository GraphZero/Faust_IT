angular.module('home').controller('UsersController', function($scope, $http, $location, $rootScope, $window, $q, $timeout){

        var x = function() {
            var absUrl = "/findAllUsers";
            var config = {
                method: 'GET',
                url: absUrl,
                accept: "application/json"
            };
            return $http.get(absUrl, "", config)
                .then(
                    function (response) {
                        console.log("Successfully got all users.");
                        return response.data;
                    },
                    function (response) {
                        console.log(response);
                        console.log("Couldn't get users." + response);
                        return [];
                    });
         };
    $scope.users = [{id: 1, username: "TestUsername", password: "TestPassword"},
        {id: 2, username: "TestUsername1", password: "TestPassword1"},
        {id: 3, username: "TestUsername2", password: "TestPassword2"},
        {id: 4, username: "TestUsername", password: "TestPassword3"}];

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
                    console.log(response)
                    console.log("Couldnt add user." + response);
                });
    };

    $scope.deleteUser = function() {
        var absUrl = "/deleteUser";
        var deleteUserCommand = {
            id: $scope.userId
        };
        var config = {
            method: 'POST',
            url: absUrl,
            accept: "application/json"
        };
        return $http.post(absUrl, deleteUserCommand, config)
            .then(
                function (response) {
                    console.log("Successfully deleted user.");
                    $location.path('/');
                },
                function (response) {
                    console.log(response)
                    console.log("Couldnt delete user." + response);
                });
    }


    

});