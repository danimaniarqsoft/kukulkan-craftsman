<form name="editForm" role="form" novalidate ng-submit="vm.save()" show-validation>

    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"
                ng-click="vm.clear()">&times;</button>
        <h4 class="modal-title" id="my${entity}Label" data-translate="${projectName}App.${entityCamelCase}.home.createOrEditLabel">Create or edit a ${entity}</h4>
    </div>
    <div class="modal-body">
        <jhi-alert-error></jhi-alert-error>
        <div class="form-group" ng-show="vm.${entityCamelCase}.id">
            <label for="id" data-translate="global.field.id">ID</label>
            <input type="text" class="form-control" id="id" name="id"
                    ng-model="vm.${entityCamelCase}.id" readonly />
        </div>
        <#list properties as property>
    	<#if  property.qualifiedName == "java.sql.Blob">
    	<#elseif property.columnType?contains("TIMESTAMP")>
        	<#include "./edit/zonedatetime.ftl">
    	<#elseif property.columnType?contains("DATE")>
    		<#include "./edit/localdate.ftl">
        <#else> 
        	<#include "./edit/string.ftl">
    	</#if>
		</#list>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="vm.clear()">
            <span class="glyphicon glyphicon-ban-circle"></span>&nbsp;<span data-translate="entity.action.cancel">Cancel</span>
        </button>
        <button type="submit" ng-disabled="editForm.$invalid || vm.isSaving" class="btn btn-primary">
            <span class="glyphicon glyphicon-save"></span>&nbsp;<span data-translate="entity.action.save">Save</span>
        </button>
    </div>
</form>
