(function() {
    'use strict';

    angular
        .module('jhipsterSampleApp')
        .controller('JobHistoryMySuffixDetailController', JobHistoryMySuffixDetailController);

    JobHistoryMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'JobHistory'];

    function JobHistoryMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, JobHistory) {
        var vm = this;

        vm.jobHistory = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterSampleApp:jobHistoryUpdate', function(event, result) {
            vm.jobHistory = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
