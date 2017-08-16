(function() {
    'use strict';

    angular
        .module('atlasApp')
        .controller('${name}DeleteController',${name}DeleteController);

    ${name}DeleteController.$inject = ['$uibModalInstance', 'entity', '${name}'];

    function ${name}DeleteController($uibModalInstance, entity, ${name}) {
        var vm = this;

        vm.${name} = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ${name}.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
