(function() {
    'use strict';

    angular
        .module('jhipsterSampleApp')
        .controller('RegionMySuffixDetailController', RegionMySuffixDetailController);

    RegionMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Region'];

    function RegionMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Region) {
        var vm = this;

        vm.region = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterSampleApp:regionUpdate', function(event, result) {
            vm.region = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
