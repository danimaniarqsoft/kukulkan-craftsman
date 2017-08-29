
<div>
    <h2><span data-translate="${projectName}App.${entityCamelCase}.detail.title">${entity}</span> {{vm.${entityCamelCase}.id}}</h2>
    <hr>
    <jhi-alert-error></jhi-alert-error>
    <dl class="dl-horizontal jh-entity-details">
    <#list properties as property>
        <dt><span data-translate="${projectName}App.${entityCamelCase}.${property.propertyName}">${property.propertyName}</span></dt>
        <dd>
    	<#if  property.qualifiedName == "java.sql.Blob">
            <div ng-if="vm.${entityCamelCase}.${property.propertyName}">
                <a ng-click="vm.openFile(vm.${entityCamelCase}.${property.propertyName}ContentType, vm.${entityCamelCase}.${property.propertyName})" data-translate="entity.action.open">open</a>
                {{vm.${entityCamelCase}.${property.propertyName}ContentType}}, {{vm.byteSize(vm.${entityCamelCase}.${property.propertyName})}}
            </div>
    	<#elseif property.columnType?contains("TIMESTAMP")>
        	<span>{{vm.${entityCamelCase}.${property.propertyName} | date:'medium'}}</span>
    	<#elseif property.columnType?contains("DATE")>
    		<span>{{vm.${entityCamelCase}.${property.propertyName} | date:'mediumDate'}}</span>
        <#else>
        	<span>{{vm.${entityCamelCase}.${property.propertyName}}}</span>
    	</#if>
        </dd>
	</#list>
    </dl>

    <button type="submit"
            ui-sref="{{ vm.previousState }}"
            class="btn btn-info">
        <span class="glyphicon glyphicon-arrow-left"></span>&nbsp;<span data-translate="entity.action.back"> Back</span>
    </button>

    <button type="button" ui-sref="${entityCamelCase}-detail.edit({id:vm.${entityCamelCase}.id})" class="btn btn-primary">
        <span class="glyphicon glyphicon-pencil"></span>
        <span class="hidden-sm-down" data-translate="entity.action.edit"> Edit</span>
    </button>
</div>