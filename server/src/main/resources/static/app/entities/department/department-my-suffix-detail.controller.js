(function() {
    'use strict';

    angular
        .module('jhipsterSampleApp')
        .controller('DepartmentMySuffixDetailController', DepartmentMySuffixDetailController);

    DepartmentMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Department'];

    function DepartmentMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Department) {
        var vm = this;

        vm.department = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterSampleApp:departmentUpdate', function(event, result) {
            vm.department = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
