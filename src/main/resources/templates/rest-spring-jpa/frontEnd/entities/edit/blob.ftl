			<div class="form-group" ngf-drop ngf-change="vm.set${property.name?cap_first}($file, vm.${entityCamelCase})">
				<label class="control-label" data-translate="${projectName}App.${entityCamelCase}.${property.name}"
					for="field_${property.name}">${property.name}</label>
				<div>
					<div ng-if="vm.${entityCamelCase}.${property.name}" class="help-block clearfix">
						<a class="pull-left"
							ng-click="vm.openFile(vm.${entityCamelCase}.${property.name}ContentType, vm.${entityCamelCase}.${property.name})"
							data-translate="entity.action.open">open</a>
						<br>
							<span class="pull-left">{{vm.${entityCamelCase}.${property.name}ContentType}},
								{{vm.byteSize(vm.${entityCamelCase}.${property.name})}}
							</span>
							<button type="button"
								ng-click="vm.${entityCamelCase}.${property.name}=null;vm.${entityCamelCase}.${property.name}ContentType=null;"
								class="btn btn-default btn-xs pull-right">
								<span class="glyphicon glyphicon-remove"></span>
							</button>
					</div>
					<button type="button" ngf-select class="btn btn-default btn-block"
						ngf-change="vm.set${property.name?cap_first}($file, vm.${entityCamelCase})" data-translate="entity.action.addblob">
						Add blob
					</button>
				</div>
				<input type="hidden" class="form-control" name="${property.name}" id="field_${property.name}"
					ng-model="vm.${entityCamelCase}.${property.name}" required minbytes="234" maxbytes="2345" />
				<input type="hidden" class="form-control" name="${property.name}ContentType"
					id="field_${property.name}ContentType" ng-model="vm.${entityCamelCase}.${property.name}ContentType" />
				<div ng-show="editForm.${property.name}.$invalid">
					<p class="help-block" ng-show="editForm.${property.name}.$error.required"
						data-translate="entity.validation.required">
						This field is required.
					</p>
					<p class="help-block" ng-show="editForm.${property.name}.$error.minbytes"
						data-translate="entity.validation.minbytes" translate-value-min="234">
						This field should be at least 234.
					</p>
					<p class="help-block" ng-show="editForm.${property.name}.$error.maxbytes"
						data-translate="entity.validation.maxbytes" translate-value-max="2345">
						This field cannot be more than 2345.
					</p>
				</div>
			</div>
