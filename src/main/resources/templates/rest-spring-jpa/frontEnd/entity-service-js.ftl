(function() {
    'use strict';
    angular
        .module('${projectName}App')
        .factory('Usuario', Usuario);

    Usuario.$inject = ['$resource', 'DateUtils'];

    function Usuario ($resource, DateUtils) {
        var resourceUrl =  'api/usuarios/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.tLocalDate = DateUtils.convertLocalDateFromServer(data.tLocalDate);
                        data.tZonedDateTime = DateUtils.convertDateTimeFromServer(data.tZonedDateTime);
                        data.tInstant = DateUtils.convertDateTimeFromServer(data.tInstant);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.tLocalDate = DateUtils.convertLocalDateToServer(copy.tLocalDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.tLocalDate = DateUtils.convertLocalDateToServer(copy.tLocalDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
