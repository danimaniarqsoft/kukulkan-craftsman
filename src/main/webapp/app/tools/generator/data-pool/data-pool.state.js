(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('data-pool', {
            abstract: true,
            parent: 'tools'
        });
    }
})();
