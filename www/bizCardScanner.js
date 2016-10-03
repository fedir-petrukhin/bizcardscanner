//var exec = require('cordova/exec');

window.scanPhoto = function(success, error) {
    cordova.exec(success, error, "bizCardScanner", "scanPhoto", []);
};

window.isCamCardInstalled = function(success, error) {
    cordova.exec(success, error, "bizCardScanner", "isCamCardInstalled", []);
};

window.isExistAppSupportOpenApi = function(success, error) {
    cordova.exec(success, error, "bizCardScanner", "isExistAppSupportOpenApi", []);
};

window.getOpenApiVersion = function(success, error) {
    cordova.exec(success, error, "bizCardScanner", "getVersion", []);
};
