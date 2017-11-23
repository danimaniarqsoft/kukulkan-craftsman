(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('GrammarController', GrammarController);

    GrammarController.$inject = ['$scope', '$rootScope', 'Principal', 'Auth', 'JhiLanguageService', '$translate', 'Grammar'];

    function GrammarController ($scope, $rootScope, Principal, Auth, JhiLanguageService, $translate, Grammar) {
        var vm = this;

        vm.action = action;
        vm.code ="entity Usuario (usuarios){\n"+
            "\tnombre String required,\n"+
            "\tedad Integer,\n"+
            "\tnumeroCredencial Long,\n"+
            "\tsueldo BigDecimal,\n"+
            "\timpuesto Float,\n"+
            "\timpuestoDetalle Double,\n"+
            "\tactivo Boolean,\n"+
            "\tfechaLocalDate LocalDate,\n"+
            "\tfechaZoneDateTime ZonedDateTime,\n"+
            "\timagen Blob,\n"+
            "\timagenAnyBlob AnyBlob,\n"+
            "\timagenBlob ImageBlob,\n"+
            "\tdesc TextBlob,\n"+
            "\tinstante Instant\n"+
            "}";
        
        vm.editorOptions = {
    			useWrapMode : true,
    			showGutter: true,
    			mode: 'java',
    			theme:'twilight',
    			firstLineNumber: 1,
    			onLoad: vm.aceLoaded
    			};
    		vm.aceLoaded  = function (_editor) {
    			// Options
    		    _editor.setReadOnly(false);
    		};

    	var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:grammarUpdate', function(event, result) {
    			vm.dataStore = result;
          	});
    	
        $scope.$on('$destroy', unsubscribe);
        
        function action(option){
        	Grammar.generateCode(vm.code, onConnectionSuccess, onConnectionError);
        }
        
        function onConnectionSuccess (result) {
        	$scope.$emit('kukulkancraftsmanApp:grammarUpdate', result);
        }
        
        function onConnectionError () {
        }
    }
})();
