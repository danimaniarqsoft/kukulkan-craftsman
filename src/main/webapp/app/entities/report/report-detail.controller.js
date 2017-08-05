(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('ReportDetailController', ReportDetailController);

    ReportDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Report'];

    function ReportDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Report) {
        var vm = this;

        vm.report = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:reportUpdate', function(event, result) {
            vm.report = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
