
<div>
    <h2><span data-translate="atlasApp.usuario.detail.title">Usuario</span> {{vm.usuario.id}}</h2>
    <hr>
    <jhi-alert-error></jhi-alert-error>
    <dl class="dl-horizontal jh-entity-details">
        <dt><span data-translate="atlasApp.usuario.cadena">Cadena</span></dt>
        <dd>
            <span>{{vm.usuario.cadena}}</span>
        </dd>
        <dt><span data-translate="atlasApp.usuario.entero">Entero</span></dt>
        <dd>
            <span>{{vm.usuario.entero}}</span>
        </dd>
        <dt><span data-translate="atlasApp.usuario.tLong">T Long</span></dt>
        <dd>
            <span>{{vm.usuario.tLong}}</span>
        </dd>
        <dt><span data-translate="atlasApp.usuario.tBigDecimal">T Big Decimal</span></dt>
        <dd>
            <span>{{vm.usuario.tBigDecimal}}</span>
        </dd>
        <dt><span data-translate="atlasApp.usuario.tFloat">T Float</span></dt>
        <dd>
            <span>{{vm.usuario.tFloat}}</span>
        </dd>
        <dt><span data-translate="atlasApp.usuario.tDouble">T Double</span></dt>
        <dd>
            <span>{{vm.usuario.tDouble}}</span>
        </dd>
        <dt><span data-translate="atlasApp.usuario.tActivo">T Activo</span></dt>
        <dd>
            <span>{{vm.usuario.tActivo}}</span>
        </dd>
        <dt><span data-translate="atlasApp.usuario.tLocalDate">T Local Date</span></dt>
        <dd>
            <span>{{vm.usuario.tLocalDate | date:'mediumDate'}}</span>
        </dd>
        <dt><span data-translate="atlasApp.usuario.tZonedDateTime">T Zoned Date Time</span></dt>
        <dd>
            <span>{{vm.usuario.tZonedDateTime | date:'medium'}}</span>
        </dd>
        <dt><span data-translate="atlasApp.usuario.tBlob">T Blob</span></dt>
        <dd>
            <div ng-if="vm.usuario.tBlob">
                <a ng-click="vm.openFile(vm.usuario.tBlobContentType, vm.usuario.tBlob)" data-translate="entity.action.open">open</a>
                {{vm.usuario.tBlobContentType}}, {{vm.byteSize(vm.usuario.tBlob)}}
            </div>
        </dd>
        <dt><span data-translate="atlasApp.usuario.tAnyBlob">T Any Blob</span></dt>
        <dd>
            <div ng-if="vm.usuario.tAnyBlob">
                <a ng-click="vm.openFile(vm.usuario.tAnyBlobContentType, vm.usuario.tAnyBlob)" data-translate="entity.action.open">open</a>
                {{vm.usuario.tAnyBlobContentType}}, {{vm.byteSize(vm.usuario.tAnyBlob)}}
            </div>
        </dd>
        <dt><span data-translate="atlasApp.usuario.tImageBlob">T Image Blob</span></dt>
        <dd>
            <div ng-if="vm.usuario.tImageBlob">
                <a ng-click="vm.openFile(vm.usuario.tImageBlobContentType, vm.usuario.tImageBlob)">
                    <img data-ng-src="{{'data:' + vm.usuario.tImageBlobContentType + ';base64,' + vm.usuario.tImageBlob}}" style="max-width: 100%;" alt="usuario image"/>
                </a>
                {{vm.usuario.tImageBlobContentType}}, {{vm.byteSize(vm.usuario.tImageBlob)}}
            </div>
        </dd>
        <dt><span data-translate="atlasApp.usuario.tTextBlob">T Text Blob</span></dt>
        <dd>
            <span>{{vm.usuario.tTextBlob}}</span>
        </dd>
        <dt><span data-translate="atlasApp.usuario.tInstant">T Instant</span></dt>
        <dd>
            <span>{{vm.usuario.tInstant | date:'medium'}}</span>
        </dd>
    </dl>

    <button type="submit"
            ui-sref="{{ vm.previousState }}"
            class="btn btn-info">
        <span class="glyphicon glyphicon-arrow-left"></span>&nbsp;<span data-translate="entity.action.back"> Back</span>
    </button>

    <button type="button" ui-sref="usuario-detail.edit({id:vm.usuario.id})" class="btn btn-primary">
        <span class="glyphicon glyphicon-pencil"></span>
        <span class="hidden-sm-down" data-translate="entity.action.edit"> Edit</span>
    </button>
</div>
