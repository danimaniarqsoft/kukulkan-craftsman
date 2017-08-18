
<div>
    <h2><span data-translate="${projectName}App.${propertyName}.detail.title">${name}</span> {{vm.${propertyName}.id}}</h2>
    <hr>
    <jhi-alert-error></jhi-alert-error>
    <dl class="dl-horizontal jh-entity-details">
    <#list properties as property>
        <dt><span data-translate="${projectName}App.${property.propertyName}.cadena">${property.propertyName}</span></dt>
        <dd>
            <span>{{vm.${propertyName}.${property.propertyName}}}</span>
        </dd>
    </dl>
	</#list>
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
