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
        </#if>
        <#if hasBlobProperties == true>
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        </#if>
        vm.save = save;
          
        <#if hasTimeProperties == true>
        	<#list properties as property>
        	<#if property.time == true> 
        vm.datePickerOpenStatus.${property.propertyName} = false;
            </#if>
        	</#list>
        </#if>

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
            $scope.$emit('${projectName}App:${propertyName}Update', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
        
        <#if hasBlobProperties == true>
        	<#list properties as property>
        	<#if property.blob == true> 
        vm.set${property.propertyName?cap_first} = function ($file, ${propertyName}) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        ${propertyName}.${property.propertyName} = base64Data;
                        ${propertyName}.${property.propertyName}ContentType = $file.type;
                    });
                });
            }
        };
            </#if>
        	</#list>
        </#if>

        <#if hasTimeProperties == true>
        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
        </#if>
    }
})();
