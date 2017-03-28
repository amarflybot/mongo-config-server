(function() {
    'use strict';

    angular
        .module('jhipsterSampleApp')
        .controller('EmployeeMySuffixDialogController', EmployeeMySuffixDialogController);

    EmployeeMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Employee'];

    function EmployeeMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Employee) {
        var vm = this;

        vm.employee = entity;
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
            if (vm.employee.id !== null) {
                Employee.update(vm.employee, onSaveSuccess, onSaveError);
            } else {
                Employee.save(vm.employee, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterSampleApp:employeeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.hireDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
