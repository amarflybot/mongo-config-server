(function() {
    'use strict';

    angular
        .module('jhipsterSampleApp')
        .controller('TaskMySuffixDetailController', TaskMySuffixDetailController);

    TaskMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Task'];

    function TaskMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Task) {
        var vm = this;

        vm.task = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterSampleApp:taskUpdate', function(event, result) {
            vm.task = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
