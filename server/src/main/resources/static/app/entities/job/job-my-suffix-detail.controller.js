(function() {
    'use strict';

    angular
        .module('jhipsterSampleApp')
        .controller('JobMySuffixDetailController', JobMySuffixDetailController);

    JobMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Job'];

    function JobMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Job) {
        var vm = this;

        vm.job = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterSampleApp:jobUpdate', function(event, result) {
            vm.job = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
