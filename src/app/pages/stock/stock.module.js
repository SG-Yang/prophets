/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('Prophets.pages.stock', ['ngResource'])
      .config(routeConfig).factory('stockService', loadStock);

    function loadStock($resource){
         return $resource('http://localhost:8080/as/stocks/typeId',{
        });
    }

  /** @ngInject */
  function routeConfig($stateProvider,$urlRouterProvider) {
    $stateProvider
        .state('stock', {
          url: '/stock',
          title: 'Stock',
          template : '<ui-view autoscroll="true" autoscroll-body-top></ui-view>',
          abstract: true,
          sidebarMeta: {
                icon: 'ion-android-list',
                order: 0,
          },
        }).state('stock.rareMetals', {
            url: '/rareMetals',
            templateUrl: 'app/pages/stock/stock.html',
            title: 'Rare Metals',
            controller: 'StockPageCtrl',
            sidebarMeta: {
                order: 100,
            },
        }).state('stock.Banking', {
            url: '/banking',
            templateUrl: 'app/pages/stock/stock.html',
            title: 'Banking',
            controller: 'StockPageCtrl',
            sidebarMeta: {
                order: 200,
            },
        });

   //$urlRouterProvider.when('/stock','/stock/rareMetals');
  }

})();
