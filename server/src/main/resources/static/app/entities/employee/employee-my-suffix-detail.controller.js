(function() {
    'use strict';

    angular
        .module('jhipsterSampleApp')
        .controller('EmployeeMySuffixDetailController', EmployeeMySuffixDetailController);

    EmployeeMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Employee'];

    function EmployeeMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Employee) {
        var vm = this;

        vm.employee = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterSampleApp:employeeUpdate', function(event, result) {
            vm.employee = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
