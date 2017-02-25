/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('Prophets.pages', [
    'ui.router',

    'Prophets.pages.dashboard',
    'Prophets.pages.ui',
    'Prophets.pages.components',
    'Prophets.pages.form',
    'Prophets.pages.tables',
    'Prophets.pages.charts',
    'Prophets.pages.maps',
    'Prophets.pages.profile',
    'Prophets.pages.stock',
  ])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($urlRouterProvider, baSidebarServiceProvider) {
    $urlRouterProvider.otherwise('/dashboard');

    baSidebarServiceProvider.addStaticItem({
      title: 'Pages',
      icon: 'ion-document',
      subMenu: [{
        title: 'Sign In',
        fixedHref: 'auth.html',
        blank: true
      }, {
        title: 'Sign Up',
        fixedHref: 'reg.html',
        blank: true
      }, {
        title: 'User Profile',
        stateRef: 'profile'
      }, {
        title: '404 Page',
        fixedHref: '404.html',
        blank: true
      }]
    });
    /**
    baSidebarServiceProvider.addStaticItem({
      title: 'Stock Lib',
      icon: 'ion-android-list',
      subMenu: [{
        title: 'Bank',
        stateRef:'stock'
      }]
    });
     **/
  }

})();
