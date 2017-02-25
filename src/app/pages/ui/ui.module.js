/**
 * @author k.danovsky
 * created on 12.01.2016
 */
(function () {
  'use strict';

  angular.module('Prophets.pages.ui', [
    'Prophets.pages.ui.typography',
    'Prophets.pages.ui.buttons',
    'Prophets.pages.ui.icons',
    'Prophets.pages.ui.modals',
    'Prophets.pages.ui.grid',
    'Prophets.pages.ui.alerts',
    'Prophets.pages.ui.progressBars',
    'Prophets.pages.ui.notifications',
    'Prophets.pages.ui.tabs',
    'Prophets.pages.ui.slider',
    'Prophets.pages.ui.panels',
  ])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('ui', {
          url: '/ui',
          template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
          abstract: true,
          title: 'UI Features',
          sidebarMeta: {
            icon: 'ion-android-laptop',
            order: 200,
          },
        });
  }

})();
