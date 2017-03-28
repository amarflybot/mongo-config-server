(function() {
    'use strict';

    angular
        .module('jhipsterSampleApp')
        .controller('CountryMySuffixDetailController', CountryMySuffixDetailController);

    CountryMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Country'];

    function CountryMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Country) {
        var vm = this;

        vm.country = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterSampleApp:countryUpdate', function(event, result) {
            vm.country = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
