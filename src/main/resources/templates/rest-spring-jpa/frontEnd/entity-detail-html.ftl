
<div>
    <h2><span data-translate="${projectName}App.${propertyName}.detail.title">${name}</span> {{vm.${propertyName}.id}}</h2>
    <hr>
    <jhi-alert-error></jhi-alert-error>
    <dl class="dl-horizontal jh-entity-details">
    <#list properties as property>
        <dt><span data-translate="${projectName}App.${propertyName}.${property.propertyName}">${property.propertyName}</span></dt>
        <dd>
    	<#if  property.qualifiedName == "java.sql.Blob">
            <div ng-if="vm.${propertyName}.${property.propertyName}">
                <a ng-click="vm.openFile(vm.${propertyName}.${property.propertyName}ContentType, vm.${propertyName}.${property.propertyName})" data-translate="entity.action.open">open</a>
                {{vm.${propertyName}.${property.propertyName}ContentType}}, {{vm.byteSize(vm.${propertyName}.${property.propertyName})}}
            </div>
    	<#elseif property.columnType?contains("TIMESTAMP")>
        	<span>{{vm.${propertyName}.${property.propertyName} | date:'medium'}}</span>
    	<#elseif property.columnType?contains("DATE")>
    		<span>{{vm.${propertyName}.${property.propertyName} | date:'mediumDate'}}</span>
        <#else>
        	<span>{{vm.${propertyName}.${property.propertyName}}}</span>
    	</#if>
        </dd>
	</#list>
    </dl>

    <button type="submit"
            ui-sref="{{ vm.previousState }}"
            class="btn btn-info">
        <span class="glyphicon glyphicon-arrow-left"></span>&nbsp;<span data-translate="entity.action.back"> Back</span>
    </button>

    <button type="button" ui-sref="${propertyName}-detail.edit({id:vm.${propertyName}.id})" class="btn btn-primary">
        <span class="glyphicon glyphicon-pencil"></span>
        <span class="hidden-sm-down" data-translate="entity.action.edit"> Edit</span>
    </button>
</div>
