// Karma configuration
// http://karma-runner.github.io/0.13/config/configuration-file.html

var sourcePreprocessors = ['coverage'];

function isDebug() {
    return process.argv.indexOf('--debug') >= 0;
}

if (isDebug()) {
    // Disable JS minification if Karma is run with debug option.
    sourcePreprocessors = [];
}

module.exports = function (config) {
    config.set({
        // base path, that will be used to resolve files and exclude
        basePath: 'server/src/test/javascript/'.replace(/[^/]+/g, '..'),

        // testing framework to use (jasmine/mocha/qunit/...)
        frameworks: ['jasmine'],

        // list of files / patterns to load in the browser
        files: [
            // bower:js
            'server/src/main/resources/static/bower_components/jquery/dist/jquery.js',
            'server/src/main/resources/static/bower_components/json3/lib/json3.js',
            'server/src/main/resources/static/bower_components/messageformat/messageformat.js',
            'server/src/main/resources/static/bower_components/angular/angular.js',
            'server/src/main/resources/static/bower_components/angular-aria/angular-aria.js',
            'server/src/main/resources/static/bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
            'server/src/main/resources/static/bower_components/angular-cache-buster/angular-cache-buster.js',
            'server/src/main/resources/static/bower_components/angular-cookies/angular-cookies.js',
            'server/src/main/resources/static/bower_components/ngstorage/ngStorage.js',
            'server/src/main/resources/static/bower_components/angular-loading-bar/build/loading-bar.js',
            'server/src/main/resources/static/bower_components/angular-resource/angular-resource.js',
            'server/src/main/resources/static/bower_components/angular-sanitize/angular-sanitize.js',
            'server/src/main/resources/static/bower_components/angular-ui-router/release/angular-ui-router.js',
            'server/src/main/resources/static/bower_components/bootstrap-ui-datetime-picker/dist/datetime-picker.js',
            'server/src/main/resources/static/bower_components/ng-file-upload/ng-file-upload.js',
            'server/src/main/resources/static/bower_components/ngInfiniteScroll/build/ng-infinite-scroll.js',
            'server/src/main/resources/static/bower_components/angular-mocks/angular-mocks.js',
            // endbower
            'server/src/main/resources/static/app/app.module.js',
            'server/src/main/resources/static/app/app.state.js',
            'server/src/main/resources/static/app/app.constants.js',
            'server/src/main/resources/static/app/**/*.+(js|html)',
            'server/src/test/javascript/spec/helpers/module.js',
            'server/src/test/javascript/spec/helpers/httpBackend.js',
            'server/src/test/javascript/**/!(karma.conf|protractor.conf).js'
        ],


        // list of files / patterns to exclude
        exclude: ['server/src/test/javascript/e2e/**'],

        preprocessors: {
            './**/*.js': sourcePreprocessors
        },

        reporters: ['dots', 'junit', 'coverage', 'progress'],

        junitReporter: {
            outputFile: 'target/test-results/karma/TESTS-results.xml'
        },

        coverageReporter: {
            dir: 'server/target/test-results/coverage',
            reporters: [
                {type: 'lcov', subdir: 'report-lcov'}
            ]
        },

        // web server port
        port: 9876,

        // level of logging
        // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
        logLevel: config.LOG_INFO,

        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: false,

        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: ['PhantomJS'],

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: false,

        // to avoid DISCONNECTED messages when connecting to slow virtual machines
        browserDisconnectTimeout: 10000, // default 2000
        browserDisconnectTolerance: 1, // default 0
        browserNoActivityTimeout: 4 * 60 * 1000 //default 10000
    });
};
