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
		for(var x = 0; x < 100; x++){
			vm.tableFooter[x] = [];    
		    for(var y = 0; y < 100; y++){ 
		    	vm.tableFooter[x][y] = "";    
		    }    
		}
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
		
		function computeReport(data){
			var indicators = data.indicators;
			var elements = data.elements;
			console.log("iterate");
			for (var i = 0; i < indicators.length; i++) {
				var indicatorStatus = elements[i].indicatorStatus;
				for (var j = 0; i < indicatorStatus.length; i++) {
					if(indicatorStatus[j].indicator.id==indicators[i].id){
						vm.tableFooter[i][j] = indicatorStatus[j].state.name;						
					}
				}	
			}
			
			console.log("iterate"+vm.tableFooter.length);
			for(var i = 0; i < vm.tableFooter.length; i++) {
			    var cube = vm.tableFooter[i];
			    for(var j = 0; j < cube.length; j++) {
			    	console.log("cube[" + i + "][" + j + "] = " + cube[j]);
			    }
			}
			console.log("iterate");

		}

		var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:reportUpdate',
				function(event, result) {
					vm.report = result;
				});
		$scope.$on('$destroy', unsubscribe);
	}
})();
