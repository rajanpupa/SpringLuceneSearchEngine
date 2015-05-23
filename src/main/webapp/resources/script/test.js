var app = angular.module('myApp', []);

app.controller('myCtrl', function($scope) {
    $scope.firstName= "Rajan";
    $scope.lastName= "Upadhyay";
    $scope.person={firstName:'rajan',lastName:'upadhyay'};
    $scope.quantity=5;
    $scope.cost=4;
    $scope.operator='*';
    $scope.names = [
                    {name:'Jani',country:'Norway'},
                    {name:'Hege',country:'Sweden'},
                    {name:'Kai',country:'Denmark'},
                    {name:'Rajan',country:'Nepal'},
                    {name:'Lerman',country:'America'},
                    {name:'test',country:'test'}
                ];
});

app.controller('customersCtrl', function($scope, $http) {
    $scope.description="Ajax request Example";
    $http.get("http://localhost:8080/test/customers")
      .success(function(response) {$scope.names = response.records;});
});