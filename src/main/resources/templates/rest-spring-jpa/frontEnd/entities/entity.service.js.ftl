<#assign hasLocalDate = false>
(function() {
    'use strict';
    angular
        .module('${projectName}App')
        .factory('${entityCamelCasePlural}', ${entityCamelCasePlural});

    ${entityCamelCasePlural}.$inject = ['$resource'<#if hasTimeProperties == true>, 'DateUtils'</#if>];

    function ${entityCamelCasePlural} ($resource<#if hasTimeProperties == true>, DateUtils</#if>) {
        var resourceUrl = 'api/${entityCamelCasePlural}/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        <#if hasTimeProperties == true>
        			    	<#list properties as property>
				        		<#if property.time == true> 
				        			<#if  property.columnType?contains("TIMESTAMP")>
				        data.${property.name} = DateUtils.convertDateTimeFromServer(data.${property.name});
							    	<#elseif property.columnType?contains("DATE")>
							    	<#assign hasLocalDate = true>
						data.${property.name} = DateUtils.convertLocalDateFromServer(data.${property.name});
							    	</#if>
				            	</#if>
				        	</#list>
        				</#if>
                    }
                    return data;
                }
            },
            <#if hasLocalDate == true>
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
				<#if hasTimeProperties == true>
        			<#list properties as property>
				    	<#if property.time == true> 
				        	<#if  property.columnType?contains("DATE")>
				    copy.${property.name} = DateUtils.convertLocalDateToServer(copy.${property.name});
							</#if>
				    	</#if>
					</#list>
        		</#if>
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
				<#if hasTimeProperties == true>
        			<#list properties as property>
				    	<#if property.time == true> 
				        	<#if  property.columnType?contains("DATE")>
				    copy.${property.name} = DateUtils.convertLocalDateToServer(copy.${property.name});
							</#if>
				    	</#if>
					</#list>
        		</#if>
                    return angular.toJson(copy);
                }
            }
            <#else>
            'update': { method:'PUT' }<% } %>
            </#if>
        });
    }
})();