(function() {
    'use strict';

    angular
        .module('${projectName}App')
        .controller('UsuarioDialogController', UsuarioDialogController);

    UsuarioDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Usuario'];

    function UsuarioDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Usuario) {
        var vm = this;

        vm.usuario = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.usuario.id !== null) {
                Usuario.update(vm.usuario, onSaveSuccess, onSaveError);
            } else {
                Usuario.save(vm.usuario, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('atlasApp:usuarioUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.tLocalDate = false;
        vm.datePickerOpenStatus.tZonedDateTime = false;

        vm.setTBlob = function ($file, usuario) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        usuario.tBlob = base64Data;
                        usuario.tBlobContentType = $file.type;
                    });
                });
            }
        };

        vm.setTAnyBlob = function ($file, usuario) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        usuario.tAnyBlob = base64Data;
                        usuario.tAnyBlobContentType = $file.type;
                    });
                });
            }
        };

        vm.setTImageBlob = function ($file, usuario) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        usuario.tImageBlob = base64Data;
                        usuario.tImageBlobContentType = $file.type;
                    });
                });
            }
        };
        vm.datePickerOpenStatus.tInstant = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
