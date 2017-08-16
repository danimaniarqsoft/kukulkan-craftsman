(function() {
    'use strict';

    angular
        .module('atlasApp')
        .factory('${name}Search', ${name}Search);

    ${name}Search.$inject = ['$resource'];

    function ${name}Search($resource) {
        var resourceUrl =  'api/_search/${propertyNamePlural}/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
