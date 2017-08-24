<#assign hasLocalDate = false>
(function() {
    'use strict';
    angular
        .module('${projectName}')
        .factory('${name}', ${name});

    ${name}.$inject = ['$resource'<#if hasTimeProperties == true>, 'DateUtils'</#if>];

    function ${name} ($resource<#if hasTimeProperties == true>, 'DateUtils'</#if>) {
        var resourceUrl = 'api/${propertyNamePlural}/:id';

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
				        data.${property.propertyName} = DateUtils.convertDateTimeFromServer(data.${property.propertyName});
							    	<#elseif property.columnType?contains("DATE")>
							    	<#assign hasLocalDate = true>
						data.${property.propertyName} = DateUtils.convertLocalDateFromServer(data.${property.propertyName});
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
				    copy.${property.propertyName} = DateUtils.convertLocalDateToServer(copy.${property.propertyName});
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
				    copy.${property.propertyName} = DateUtils.convertLocalDateToServer(copy.${property.propertyName});
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