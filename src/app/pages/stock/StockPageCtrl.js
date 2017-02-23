/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('Prophets.pages.stock')
    .controller('StockPageCtrl', StockPageCtrl);

  /** @ngInject */
  function StockPageCtrl($scope,stockService) {
    $scope.vegetables = stockService.query();
  }

})();
