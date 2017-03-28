(function() {
    'use strict';

    angular
        .module('jhipsterSampleApp')
        .controller('CountryMySuffixDialogController', CountryMySuffixDialogController);

    CountryMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Country'];

    function CountryMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Country) {
        var vm = this;

        vm.country = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.country.id !== null) {
                Country.update(vm.country, onSaveSuccess, onSaveError);
            } else {
                Country.save(vm.country, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterSampleApp:countryUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
