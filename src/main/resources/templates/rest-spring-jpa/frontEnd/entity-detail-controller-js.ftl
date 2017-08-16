(function() {
    'use strict';

    angular
        .module('${projectName}App')
        .controller('${name}DetailController', ${name}DetailController);

    ${name}DetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', '${name}'];

    function ${name}DetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, ${name}) {
        var vm = this;

        vm.${propertyName} = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('atlasApp:${propertyName}Update', function(event, result) {
            vm.${propertyName} = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
