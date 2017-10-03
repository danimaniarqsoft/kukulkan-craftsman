			<div class="form-group">
				<label class="control-label" data-translate="${projectName}App.${entityCamelCase}.${property.name}"
					for="field_${property.name}">${property.name}</label>
				<input type="number" class="form-control" name="${property.name}"
					id="field_${property.name}" ng-model="vm.${entityCamelCase}.${property.name}" />
			</div>