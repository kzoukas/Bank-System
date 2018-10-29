(function(){
    "use strict";

    var module = angular.module("bankSystem");
    module.component("userInfos", {
        templateUrl: 'js/components/user-infos/user-infos.html',
        controllerAs: "model",
        controller: userInfosController
    });
    userInfosController.$inject=['$scope','userInfoService'];
    function userInfosController($scope,userInfoService){

        $scope.getUserInfos=function() {
            if($scope.customerID){
                userInfoService.getUserInfos($scope.customerID).then(onSuccess, onError);
            }
        };
        var onSuccess=function(data){
            $scope.customer = data;
            $scope.error = '';
        };
        var onError=function(reason){
            $scope.error = reason;
            $scope.customer = {};
        };
    }

}());