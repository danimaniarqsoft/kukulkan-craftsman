<div>
    <h2 data-translate="atlasApp.usuario.home.title">Usuarios</h2>
    <jhi-alert></jhi-alert>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-4 no-padding-left">
                <button class="btn btn-primary" ui-sref="usuario.new" >
                    <span class="glyphicon glyphicon-plus"></span>
                    <span class="hidden-xs-down"  data-translate="atlasApp.usuario.home.createLabel">
                        Create new Usuario
                    </span>
                </button>
            </div>
            <div class="col-xs-8 no-padding-right">
                <form name="searchForm" class="form-inline">
                    <div class="input-group pull-right" >
                        <input type="text" class="form-control" ng-model="vm.searchQuery" id="searchQuery" placeholder="{{ 'atlasApp.usuario.home.search' | translate }}">
                        <span  class="input-group-btn width-min" >
                            <button class="btn btn-info" ng-click="vm.search(vm.searchQuery)">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </span>
                        <span class="input-group-btn width-min" ng-if="vm.currentSearch">
                            <button class="btn btn-info" ng-click="vm.clear()">
                                <span class="glyphicon glyphicon-trash"></span>
                            </button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <br/>
    <div class="table-responsive">
        <table class="jh-table table table-striped">
            <thead>
                <tr jh-sort="vm.predicate" ascending="vm.reverse" callback="vm.transition()">
                    <th jh-sort-by="id"><span data-translate="global.field.id">ID</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="cadena"><span data-translate="atlasApp.usuario.cadena">Cadena</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="entero"><span data-translate="atlasApp.usuario.entero">Entero</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="tLong"><span data-translate="atlasApp.usuario.tLong">T Long</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="tBigDecimal"><span data-translate="atlasApp.usuario.tBigDecimal">T Big Decimal</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="tFloat"><span data-translate="atlasApp.usuario.tFloat">T Float</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="tDouble"><span data-translate="atlasApp.usuario.tDouble">T Double</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="tActivo"><span data-translate="atlasApp.usuario.tActivo">T Activo</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="tLocalDate"><span data-translate="atlasApp.usuario.tLocalDate">T Local Date</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="tZonedDateTime"><span data-translate="atlasApp.usuario.tZonedDateTime">T Zoned Date Time</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="tBlob"><span data-translate="atlasApp.usuario.tBlob">T Blob</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="tAnyBlob"><span data-translate="atlasApp.usuario.tAnyBlob">T Any Blob</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="tImageBlob"><span data-translate="atlasApp.usuario.tImageBlob">T Image Blob</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="tTextBlob"><span data-translate="atlasApp.usuario.tTextBlob">T Text Blob</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th jh-sort-by="tInstant"><span data-translate="atlasApp.usuario.tInstant">T Instant</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="usuario in vm.usuarios track by usuario.id">
                    <td><a ui-sref="usuario-detail({id:usuario.id})">{{usuario.id}}</a></td>
                    <td>{{usuario.cadena}}</td>
                    <td>{{usuario.entero}}</td>
                    <td>{{usuario.tLong}}</td>
                    <td>{{usuario.tBigDecimal}}</td>
                    <td>{{usuario.tFloat}}</td>
                    <td>{{usuario.tDouble}}</td>
                    <td>{{usuario.tActivo}}</td>
                        <td>{{usuario.tLocalDate | date:'mediumDate'}}</td>
                    <td>{{usuario.tZonedDateTime | date:'medium'}}</td>
                    <td>
                        <a ng-if="usuario.tBlob" ng-click="vm.openFile(usuario.tBlobContentType, usuario.tBlob)" data-translate="entity.action.open">open</a>
                        <span ng-if="usuario.tBlob">{{usuario.tBlobContentType}}, {{vm.byteSize(usuario.tBlob)}}</span>
                    </td>
                    <td>
                        <a ng-if="usuario.tAnyBlob" ng-click="vm.openFile(usuario.tAnyBlobContentType, usuario.tAnyBlob)" data-translate="entity.action.open">open</a>
                        <span ng-if="usuario.tAnyBlob">{{usuario.tAnyBlobContentType}}, {{vm.byteSize(usuario.tAnyBlob)}}</span>
                    </td>
                    <td>
                        <a ng-if="usuario.tImageBlob" ng-click="vm.openFile(usuario.tImageBlobContentType, usuario.tImageBlob)">
                            <img data-ng-src="{{'data:' + usuario.tImageBlobContentType + ';base64,' + usuario.tImageBlob}}" style="max-height: 30px;" alt="usuario image"/>
                        </a>
                        <span ng-if="usuario.tImageBlob">{{usuario.tImageBlobContentType}}, {{vm.byteSize(usuario.tImageBlob)}}</span>
                    </td>
                    <td>{{usuario.tTextBlob}}</td>
                    <td>{{usuario.tInstant | date:'medium'}}</td>
                    <td class="text-right">
                        <div class="btn-group flex-btn-group-container">
                            <button type="submit"
                                    ui-sref="usuario-detail({id:usuario.id})"
                                    class="btn btn-info btn-sm">
                                <span class="glyphicon glyphicon-eye-open"></span>
                                <span class="hidden-sm-down" data-translate="entity.action.view"></span>
                            </button>
                            <button type="submit"
                                    ui-sref="usuario.edit({id:usuario.id})"
                                    class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-pencil"></span>
                                <span class="hidden-sm-down" data-translate="entity.action.edit"></span>
                            </button>
                            <button type="submit"
                                    ui-sref="usuario.delete({id:usuario.id})"
                                    class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-remove-circle"></span>
                                <span class="hidden-sm-down" data-translate="entity.action.delete"></span>
                            </button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="text-center">
        <jhi-item-count page="vm.page" total="vm.queryCount" items-per-page="vm.itemsPerPage"></jhi-item-count>
        <uib-pagination class="pagination-sm" total-items="vm.totalItems" items-per-page="vm.itemsPerPage" ng-model="vm.page" ng-change="vm.transition()"></uib-pagination>
    </div>
</div>
