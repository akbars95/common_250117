wordApp.controller('wordRegistrationCtrl', function ($scope, $http, myConfig) {

    //declare
    $scope.reset = function () {
        $scope.fr_username = "";
        $scope.fr_password = "";
        $scope.fr_firstname = "";
        $scope.fr_lastname = "";
        $scope.fr_middlename = "";
        $scope.fr_email = "";
        $scope.fr_phone = "";
        $scope.fr_gender = "";
        $scope.fr_date_of_birth = new Date();
        $scope.fr_site_url = "";
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
            dateOfBirth: $scope.fr_date_of_birth,
            siteUrl: $scope.fr_site_url
        };
        var config = {};
        $http.post(myConfig.rootURL + "register", data, config)
            .then(
                function (response) {
                    console.log("OK" + response);
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