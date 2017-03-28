(function() {
    'use strict';

    angular
        .module('jhipsterSampleApp')
        .controller('LocationMySuffixDetailController', LocationMySuffixDetailController);

    LocationMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Location'];

    function LocationMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Location) {
        var vm = this;

        vm.location = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterSampleApp:locationUpdate', function(event, result) {
            vm.location = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
