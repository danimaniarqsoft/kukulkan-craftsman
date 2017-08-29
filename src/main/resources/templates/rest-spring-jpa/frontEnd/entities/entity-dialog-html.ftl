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
        <div class="form-group">
            <label class="control-label" data-translate="${projectName}App.usuario.cadena" for="field_cadena">Cadena</label>
            <input type="text" class="form-control" name="cadena" id="field_cadena"
                    ng-model="vm.usuario.cadena"
                    required ng-minlength="2" ng-maxlength="5" ng-pattern="/patter/"/>
            <div ng-show="editForm.cadena.$invalid">
                <p class="help-block"
                    ng-show="editForm.cadena.$error.required" data-translate="entity.validation.required">
                    This field is required.
                </p>
                <p class="help-block"
                    ng-show="editForm.cadena.$error.minlength" data-translate="entity.validation.minlength" translate-value-min="2">
                    This field is required to be at least 2 characters.
                </p>
                <p class="help-block"
                    ng-show="editForm.cadena.$error.maxlength" data-translate="entity.validation.maxlength" translate-value-max="5">
                    This field cannot be longer than 5 characters.
                </p>
                <p class="help-block"
                    ng-show="editForm.cadena.$error.pattern" data-translate="entity.validation.pattern" translate-value-pattern="Cadena">
                    This field should follow pattern for "Cadena".
                </p>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label" data-translate="atlasApp.usuario.entero" for="field_entero">Entero</label>
            <input type="number" class="form-control" name="entero" id="field_entero"
                    ng-model="vm.usuario.entero"
                    />
        </div>
        <div class="form-group">
            <label class="control-label" data-translate="atlasApp.usuario.tLong" for="field_tLong">T Long</label>
            <input type="number" class="form-control" name="tLong" id="field_tLong"
                    ng-model="vm.usuario.tLong"
                    />
        </div>
        <div class="form-group">
            <label class="control-label" data-translate="atlasApp.usuario.tBigDecimal" for="field_tBigDecimal">T Big Decimal</label>
            <input type="number" class="form-control" name="tBigDecimal" id="field_tBigDecimal"
                    ng-model="vm.usuario.tBigDecimal"
                    />
        </div>
        <div class="form-group">
            <label class="control-label" data-translate="atlasApp.usuario.tFloat" for="field_tFloat">T Float</label>
            <input type="number" class="form-control" name="tFloat" id="field_tFloat"
                    ng-model="vm.usuario.tFloat"
                    />
        </div>
        <div class="form-group">
            <label class="control-label" data-translate="atlasApp.usuario.tDouble" for="field_tDouble">T Double</label>
            <input type="number" class="form-control" name="tDouble" id="field_tDouble"
                    ng-model="vm.usuario.tDouble"
                    />
        </div>
        <div class="form-group">
            <label class="control-label" data-translate="atlasApp.usuario.tActivo" for="field_tActivo">T Activo</label>
                <input type="checkbox" name="tActivo" id="field_tActivo"
                          ng-model="vm.usuario.tActivo"/>
        </div>
        <div class="form-group">
            <label class="control-label" data-translate="atlasApp.usuario.tLocalDate" for="field_tLocalDate">T Local Date</label>
                <div class="input-group">
                    <input id="field_tLocalDate" type="text" class="form-control" name="tLocalDate" uib-datepicker-popup="{{dateformat}}" ng-model="vm.usuario.tLocalDate" is-open="vm.datePickerOpenStatus.tLocalDate"
                    />
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-default" ng-click="vm.openCalendar('tLocalDate')"><i class="glyphicon glyphicon-calendar"></i></button>
                    </span>
                </div>
        </div>
        <div class="form-group">
            <label class="control-label" data-translate="atlasApp.usuario.tZonedDateTime" for="field_tZonedDateTime">T Zoned Date Time</label>
                <div class="input-group">
                    <input id="field_tZonedDateTime" type="text" class="form-control" name="tZonedDateTime" datetime-picker="{{dateformat}}" ng-model="vm.usuario.tZonedDateTime" is-open="vm.datePickerOpenStatus.tZonedDateTime"
                    />
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-default" ng-click="vm.openCalendar('tZonedDateTime')"><i class="glyphicon glyphicon-calendar"></i></button>
                    </span>
                </div>
        </div>
        <div class="form-group" ngf-drop ngf-change="vm.setTBlob($file, vm.usuario)">
            <label class="control-label" data-translate="atlasApp.usuario.tBlob" for="field_tBlob">T Blob</label>
            <div>
                <div ng-if="vm.usuario.tBlob" class="help-block clearfix">
                    <a class="pull-left" ng-click="vm.openFile(vm.usuario.tBlobContentType, vm.usuario.tBlob)" data-translate="entity.action.open">open</a><br>
                    <span class="pull-left">{{vm.usuario.tBlobContentType}}, {{vm.byteSize(vm.usuario.tBlob)}}</span>
                    <button type="button" ng-click="vm.usuario.tBlob=null;vm.usuario.tBlobContentType=null;"
                            class="btn btn-default btn-xs pull-right">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                </div>
                <button type="button" ngf-select class="btn btn-default btn-block"
                        ngf-change="vm.setTBlob($file, vm.usuario)" data-translate="entity.action.addblob">
                    Add blob
                </button>
            </div>
            <input type="hidden" class="form-control" name="tBlob" id="field_tBlob"
                    ng-model="vm.usuario.tBlob"
                    required minbytes="234" maxbytes="2345"/>
            <input type="hidden" class="form-control" name="tBlobContentType" id="field_tBlobContentType"
                    ng-model="vm.usuario.tBlobContentType" />
            <div ng-show="editForm.tBlob.$invalid">
                <p class="help-block"
                    ng-show="editForm.tBlob.$error.required" data-translate="entity.validation.required">
                    This field is required.
                </p>
                <p class="help-block"
                   ng-show="editForm.tBlob.$error.minbytes" data-translate="entity.validation.minbytes" translate-value-min="234">
                    This field should be at least 234.
                </p>
                <p class="help-block"
                   ng-show="editForm.tBlob.$error.maxbytes" data-translate="entity.validation.maxbytes" translate-value-max="2345">
                    This field cannot be more than 2345.
                </p>
            </div>
        </div>
        <div class="form-group" ngf-drop ngf-change="vm.setTAnyBlob($file, vm.usuario)">
            <label class="control-label" data-translate="atlasApp.usuario.tAnyBlob" for="field_tAnyBlob">T Any Blob</label>
            <div>
                <div ng-if="vm.usuario.tAnyBlob" class="help-block clearfix">
                    <a class="pull-left" ng-click="vm.openFile(vm.usuario.tAnyBlobContentType, vm.usuario.tAnyBlob)" data-translate="entity.action.open">open</a><br>
                    <span class="pull-left">{{vm.usuario.tAnyBlobContentType}}, {{vm.byteSize(vm.usuario.tAnyBlob)}}</span>
                    <button type="button" ng-click="vm.usuario.tAnyBlob=null;vm.usuario.tAnyBlobContentType=null;"
                            class="btn btn-default btn-xs pull-right">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                </div>
                <button type="button" ngf-select class="btn btn-default btn-block"
                        ngf-change="vm.setTAnyBlob($file, vm.usuario)" data-translate="entity.action.addblob">
                    Add blob
                </button>
            </div>
            <input type="hidden" class="form-control" name="tAnyBlob" id="field_tAnyBlob"
                    ng-model="vm.usuario.tAnyBlob"
                    />
            <input type="hidden" class="form-control" name="tAnyBlobContentType" id="field_tAnyBlobContentType"
                    ng-model="vm.usuario.tAnyBlobContentType" />
        </div>
        <div class="form-group" ngf-drop ngf-change="vm.setTImageBlob($file, vm.usuario)" ngf-pattern="'image/*'">
            <label class="control-label" data-translate="atlasApp.usuario.tImageBlob" for="field_tImageBlob">T Image Blob</label>
            <div>
                <img data-ng-src="{{'data:' + vm.usuario.tImageBlobContentType + ';base64,' + vm.usuario.tImageBlob}}" style="max-height: 100px;" ng-if="vm.usuario.tImageBlob" alt="usuario image"/>
                <div ng-if="vm.usuario.tImageBlob" class="help-block clearfix">
                    <span class="pull-left">{{vm.usuario.tImageBlobContentType}}, {{vm.byteSize(vm.usuario.tImageBlob)}}</span>
                    <button type="button" ng-click="vm.usuario.tImageBlob=null;vm.usuario.tImageBlobContentType=null;"
                            class="btn btn-default btn-xs pull-right">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                </div>
                <button type="button" ngf-select class="btn btn-default btn-block"
                        ngf-change="vm.setTImageBlob($file, vm.usuario)" accept="image/*" data-translate="entity.action.addimage">
                    Add image
                </button>
            </div>
            <input type="hidden" class="form-control" name="tImageBlob" id="field_tImageBlob"
                    ng-model="vm.usuario.tImageBlob"
                    />
            <input type="hidden" class="form-control" name="tImageBlobContentType" id="field_tImageBlobContentType"
                    ng-model="vm.usuario.tImageBlobContentType" />
        </div>
        <div class="form-group">
            <label class="control-label" data-translate="atlasApp.usuario.tTextBlob" for="field_tTextBlob">T Text Blob</label>
                <textarea class="form-control" name="tTextBlob" id="field_tTextBlob"
                    ng-model="vm.usuario.tTextBlob" ></textarea>
        </div>
        <div class="form-group">
            <label class="control-label" data-translate="atlasApp.usuario.tInstant" for="field_tInstant">T Instant</label>
                <div class="input-group">
                    <input id="field_tInstant" type="text" class="form-control" name="tInstant" datetime-picker="{{dateformat}}" ng-model="vm.usuario.tInstant" is-open="vm.datePickerOpenStatus.tInstant"
                    required/>
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-default" ng-click="vm.openCalendar('tInstant')"><i class="glyphicon glyphicon-calendar"></i></button>
                    </span>
                </div>
            <div ng-show="editForm.tInstant.$invalid">
                <p class="help-block"
                    ng-show="editForm.tInstant.$error.required" data-translate="entity.validation.required">
                    This field is required.
                </p>
                <p class="help-block"
                    ng-show="editForm.tInstant.$error.ZonedDateTimelocal" data-translate="entity.validation.ZonedDateTimelocal">
                    This field should be a date and time.
                </p>
            </div>
        </div>

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
