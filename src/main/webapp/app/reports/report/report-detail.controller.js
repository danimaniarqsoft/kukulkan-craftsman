(function() {
	'use strict';

	angular.module('kukulkancraftsmanApp').controller('ReportDetailController',
			ReportDetailController);

	ReportDetailController.$inject = [ '$scope', '$rootScope', '$stateParams',
			'previousState', 'DataUtils', 'entity', 'Report' ];

	function ReportDetailController($scope, $rootScope, $stateParams,
			previousState, DataUtils, entity, Report) {
		var vm = this;

		vm.report = entity;
		vm.previousState = previousState.name;
		vm.byteSize = DataUtils.byteSize;
		vm.openFile = DataUtils.openFile;
		vm.tableFooter = [];
		vm.searchIndicator = function(indicadorStatus, id) {
			var i = 0, len = indicadorStatus.length;
			for (; i < len; i++) {
				console.log(indicadorStatus[i].indicator.nombre)
				if (indicadorStatus[i].indicator.id == id) {
					return indicadorStatus[i].state.name;
				}
			}
			return "N/A";
		}

		var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:reportUpdate',
				function(event, result) {
					vm.report = result;
				});
		$scope.$on('$destroy', unsubscribe);
	}
})();
