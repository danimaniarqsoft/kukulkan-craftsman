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
		vm.report.total = new Array(vm.report.indicators.length).fill(0);
		vm.report.totalPercent= new Array(vm.report.indicators.length).fill(0);
		vm.report.totalImplemented = 0;
		computeReport(vm.report);
		vm.searchIndicator = function(indicadorStatus, id) {
			var i = 0, len = indicadorStatus.length;
			for (; i < len; i++) {
				if (indicadorStatus[i].indicator.id == id) {
					return indicadorStatus[i].state.name;
				}
			}
			return "N/A";
		}
		
		function computeReport(report){
			var indicators = report.indicators;
			var elements = report.elements;
			for (var i = 0; i < elements.length; i++) {
				var indicatorStatus = elements[i].indicatorStatus;
				vm.report.elements[i].statusLabel = [];
				for(var j = 0; j < indicators.length ; j++){
					vm.report.elements[i].statusLabel[j] = findStatusInList(indicatorStatus, indicators[j].id);
					if(vm.report.elements[i].statusLabel[j]==='Liberado'){
						vm.report.total[j]=vm.report.total[j]+1;
						vm.report.totalImplemented= vm.report.totalImplemented+1;
					}
				}
			}
			vm.report.totalImplemented = Math.floor((vm.report.totalImplemented / (elements.length * vm.report.indicators.length)) * 100)+" %";
			for (var i = 0; i < vm.report.total.length; i++) {
				vm.report.totalPercent[i]=Math.floor((vm.report.total[i] / elements.length) * 100)+" %";
			}
		}
		
		function findStatusInList(indicatorStatus,indicatorId){
			for (var i = 0; i < indicatorStatus.length; i++) {
				if(indicatorStatus[i].indicator.id==indicatorId){
					return indicatorStatus[i].state.name;						
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
