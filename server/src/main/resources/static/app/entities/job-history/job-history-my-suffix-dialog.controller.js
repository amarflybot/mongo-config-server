(function() {
    'use strict';

    angular
        .module('jhipsterSampleApp')
        .controller('JobHistoryMySuffixDialogController', JobHistoryMySuffixDialogController);

    JobHistoryMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'JobHistory'];

    function JobHistoryMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, JobHistory) {
        var vm = this;

        vm.jobHistory = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.jobHistory.id !== null) {
                JobHistory.update(vm.jobHistory, onSaveSuccess, onSaveError);
            } else {
                JobHistory.save(vm.jobHistory, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterSampleApp:jobHistoryUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.startDate = false;
        vm.datePickerOpenStatus.endDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
