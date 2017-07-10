(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DataStoreDetailController', DataStoreDetailController);

    DataStoreDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DataStore'];

    function DataStoreDetailController($scope, $rootScope, $stateParams, previousState, entity, DataStore) {
        var vm = this;

        vm.dataStore = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:dataStoreUpdate', function(event, result) {
            vm.dataStore = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
