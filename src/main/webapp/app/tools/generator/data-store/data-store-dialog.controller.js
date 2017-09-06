(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DataStoreDialogController', DataStoreDialogController);

    DataStoreDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DataStore', 'TestConnection', 'AlertService'];

    function DataStoreDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DataStore, TestConnection, AlertService) {
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
	        	TestConnection.testConnection(vm.dataStore, onTestSuccess, onTestError);
	        }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:dataStoreUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError (error) {
        	AlertService.error("error!!!!");
            vm.isSaving = false;
        }
        
        function onTestSuccess (result) {
        	AlertService.error("Exitoso");
            vm.isSaving = false;
        }
        
        function onTestError () {
            vm.isSaving = false;
        }
    }
})();
