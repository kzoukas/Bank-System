(function(){

    var userInfoService = function($http){
        var getUserInfos = function(id){
            var accountUrl = "/users/"+id ;
            return $http.get(accountUrl)
                .then(function(response){
                    return response.data;
                });
        };
        return {
            getUserInfos: getUserInfos

        };
    };
    var module = angular.module("bankSystem");
    module.factory("userInfoService", userInfoService);

}());
