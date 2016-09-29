var exec = require('cordova/exec');

exports.scanPhoto = function(success, error) {
    exec(success, error, "bizCardScanner", "scanPhoto", []);
};
