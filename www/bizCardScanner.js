//var exec = require('cordova/exec');

window.scanPhoto = function(success, error) {
    cordova.exec(success, error, "bizCardScanner", "scanPhoto", []);
};
