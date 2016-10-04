package com.cci.bizcard.plugin;

import android.app.Activity;
import android.content.Intent;

import com.intsig.openapilib.OpenApi;

import org.apache.cordova.CallbackContext;

public class PluginActivity extends Activity {

    private CallbackContext callbackContext;

    public PluginActivity(CallbackContext callbackContext) {

        this.callbackContext = callbackContext;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            String vcfData = data.getStringExtra(OpenApi.EXTRA_KEY_VCF);
            callbackContext.success(vcfData);
        } else {
            callbackContext.error("Error code: " + resultCode);
        }
    }
}
