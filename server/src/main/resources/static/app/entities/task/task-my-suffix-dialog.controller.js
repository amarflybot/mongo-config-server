(function() {
    'use strict';

    angular
        .module('jhipsterSampleApp')
        .controller('TaskMySuffixDialogController', TaskMySuffixDialogController);

    TaskMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Task'];

    function TaskMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Task) {
        var vm = this;

        vm.task = entity;
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
            if (vm.task.id !== null) {
                Task.update(vm.task, onSaveSuccess, onSaveError);
            } else {
                Task.save(vm.task, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterSampleApp:taskUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
