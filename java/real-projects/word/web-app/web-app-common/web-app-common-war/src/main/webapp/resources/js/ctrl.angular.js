wordApp.controller('wordRegistrationCtrl', function ($scope, $http, myConfig) {

    //declare
    //variables
    $scope.fr_date_of_birth = new Date();
    $scope.datePattern = "dd.MM.yyyy";
    $scope.minDate = new Date($scope.fr_date_of_birth.getFullYear() - 100, 0, 1);
    $scope.maxDate = new Date($scope.fr_date_of_birth.getFullYear() - 5, 0, 1);
    $scope.defDate = new Date($scope.fr_date_of_birth.getFullYear() - 10, $scope.fr_date_of_birth.getMonth(),
        $scope.fr_date_of_birth.getDay());

    //functions
    $scope.reset = function () {
        $scope.fr_username = null;
        $scope.fr_password = null;
        $scope.fr_password2 = null;
        $scope.fr_firstname = null;
        $scope.fr_lastname = null;
        $scope.fr_middlename = null;
        $scope.fr_email = null;
        $scope.fr_phone = null;
        $scope.fr_gender = null;
        $scope.fr_date_of_birth = $scope.fr_date_of_birth;
        $scope.fr_site_url = null;
    };

    $scope.defaultData = function(){
        $scope.fr_username = "ivanov.ion";//ivanov.ion
        $scope.fr_password = $scope.fr_username + "123$II";//ivanov.ion123$II
        $scope.fr_password2 = $scope.fr_password;
        $scope.fr_firstname = "Ivanov";
        $scope.fr_lastname = "Ion";
        $scope.fr_middlename = "Ivanovic";
        $scope.fr_email = $scope.fr_username + "@gmail.com";
        $scope.fr_phone = "069-238-831";
        $scope.fr_gender = "M";
        $scope.fr_date_of_birth = $scope.defDate;
        $scope.fr_site_url = null;
    };

    $scope.registrate = function () {
        var data = {
            username: $scope.fr_username,
            password: $scope.fr_password,
            password2: $scope.fr_password2,
            firstname: $scope.fr_firstname,
            lastname: $scope.fr_lastname,
            middlename: $scope.fr_middlename,
            email: $scope.fr_email,
            phone: $scope.fr_phone,
            gender: $scope.fr_gender,
            dateOfBirth: getDate($scope.fr_date_of_birth),
            siteUrl: $scope.fr_site_url
        };
        var config = {};
        $http.post(myConfig.rootURL + "register", data, config)
            .then(
                function (response) {
                    console.log("OK" + response.data);
                    $scope.reset();
                    // success callback
                },
                function (response) {
                    // failure callback
                    console.log("NOK" + response);
                }
            );

    };

    //call
    $scope.reset();

});