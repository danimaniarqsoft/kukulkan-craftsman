(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('TestConnection', TestConnection);

    TestConnection.$inject = ['$resource'];

    function TestConnection ($resource) {
        var resourceUrl =  'api/data-stores/testConnection';

        return $resource(resourceUrl, {}, {
            'testConnection': { method:'POST'}
        });
    }
})();
