(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DataStoreDetailController', DataStoreDetailController);

    DataStoreDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DataStore', 'DataStoreConnection'];

    function DataStoreDetailController($scope, $rootScope, $stateParams, previousState, entity, DataStore, DataStoreConnection) {
        var vm = this;

        vm.dataStore = entity;
        vm.previousState = previousState.name;
        vm.testConnection = testConnection;

        vm.isTestingConnection = false;

        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:dataStoreUpdate', function(event, result) {
            vm.dataStore = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        function testConnection(){
        	DataStoreConnection.connect(vm.dataStore, onConnectionSuccess, onConnectionError);
        }
        
        function onConnectionSuccess (result) {
            vm.isSaving = false;
        }
        
        function onConnectionError () {
            vm.isSaving = false;
        }
    }
})();
