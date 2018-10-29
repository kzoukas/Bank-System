(function(){

    var accountService = function($http){
        var createAccount = function(account){
            var accountUrl = "/accounts" ;
            return $http.post(accountUrl,account)
                .then(function(response){
                    return response.data;
                });
        };
        return {
            createAccount: createAccount

        };
    };
    var module = angular.module("bankSystem");
    module.factory("accountService", accountService);

}());
