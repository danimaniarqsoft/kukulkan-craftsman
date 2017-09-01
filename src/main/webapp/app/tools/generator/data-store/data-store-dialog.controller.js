(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DataStoreDialogController', DataStoreDialogController);

    DataStoreDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DataStore'];

    function DataStoreDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DataStore) {
        var vm = this;

        vm.dataStore = entity;
        vm.clear = clear;
        vm.save = save;
        vm.isTestingConnection = false;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
        if(vm.isTestingConnection===false){
        	vm.isSaving = true;
        	if (vm.dataStore.id !== null) {
        		DataStore.update(vm.dataStore, onSaveSuccess, onSaveError);
        	} else {
        		DataStore.save(vm.dataStore, onSaveSuccess, onSaveError);
        	}        	
        }else{
        	console.log("testing...")
        }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:dataStoreUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
