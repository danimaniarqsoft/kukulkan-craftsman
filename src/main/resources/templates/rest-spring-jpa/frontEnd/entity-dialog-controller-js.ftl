(function() {
    'use strict';

    angular
        .module('${name}App')
        .controller('${name}DialogController', ${name}DialogController);

    ${name}DialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', '${name}'];

    function ${name}DialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, ${name}) {
        var vm = this;

        vm.${propertyName} = entity;
        vm.clear = clear;
        <#if hasTimeProperties == true>
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        <#else>
        <#if hasBlobProperties == true>
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        <#else>
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.${propertyName}.id !== null) {
                ${name}.update(vm.${propertyName}, onSaveSuccess, onSaveError);
            } else {
                ${name}.save(vm.${propertyName}, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('atlasApp:${propertyName}Update', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        <#if hasTimeProperties == true>
        vm.datePickerOpenStatus.tLocalDate = false;
        vm.datePickerOpenStatus.tZonedDateTime = false;
        <#else>

        vm.setTBlob = function ($file, ${propertyName}) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        ${propertyName}.tBlob = base64Data;
                        ${propertyName}.tBlobContentType = $file.type;
                    });
                });
            }
        };

        vm.setTAnyBlob = function ($file, ${propertyName}) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        ${propertyName}.tAnyBlob = base64Data;
                        ${propertyName}.tAnyBlobContentType = $file.type;
                    });
                });
            }
        };

        vm.setTImageBlob = function ($file, ${propertyName}) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        ${propertyName}.tImageBlob = base64Data;
                        ${propertyName}.tImageBlobContentType = $file.type;
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
