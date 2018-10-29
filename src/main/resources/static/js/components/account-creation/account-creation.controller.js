(function(){
    "use strict";

    var module = angular.module("bankSystem");
    module.component("accountCreation", {
        templateUrl: 'js/components/account-creation/account-creation.html',
        controllerAs: "model",
        controller: accountController
    });
    accountController.$inject=['$scope','accountService'];
    function accountController($scope,accountService){

        $scope.account = { initialCredit : 0};

        $scope.saveAccount=function() {
            if($scope.account.customerID) {
                accountService.createAccount($scope.account).then(onSuccess, onError);
            }
        };
        var onSuccess=function(data){
            $scope.error = '';
            $scope.account = { initialCredit : 0};
        };
        var onError=function(reason){
            $scope.error = reason;
            $scope.account = { initialCredit : 0};
        };
    }

}());