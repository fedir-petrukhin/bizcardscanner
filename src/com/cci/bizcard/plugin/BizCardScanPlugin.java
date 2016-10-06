package com.cci.bizcard.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.intsig.openapilib.OpenApi;
import com.intsig.openapilib.OpenApiParams;

import org.apache.cordova.*;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Random;

public class BizCardScanPlugin extends CordovaPlugin {

    public static final int CARD_SCAN_CODE = 0x1372;

    //private OpenApi openApi;
    private CallbackContext callbackContext;

    /*@Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 0) {
            String vcfData = data.getStringExtra(OpenApi.EXTRA_KEY_VCF);
            callbackContext.success(vcfData);
        } else {
            callbackContext.error("Error code: " + resultCode + " "
                    + data.getIntExtra(OpenApi.ERROR_CODE, 200) + data.getStringExtra(OpenApi.ERROR_MESSAGE));
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        this.callbackContext = callbackContext;
        try {
            String apiKey = "4KBgEeNVVH5NPy8hh4NPD0K5";
            OpenApi openApi = OpenApi.instance(apiKey);
            openApi.isCamCardInstalled(this.cordova.getActivity().getApplicationContext());
            openApi.isExistAppSupportOpenApi(this.cordova.getActivity().getApplicationContext());
            OpenApiParams params = new OpenApiParams() {
                {
                    this.setRecognizeLanguage("");
                    this.setReturnCropImage(false);
                }
            };

            if ("scanPhoto".equals(action)) {
                //this.cordova.setActivityResultCallback(this);
                openApi.recognizeCardByCapture(this.cordova.getActivity(), CARD_SCAN_CODE, params);
                PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
                r.setKeepCallback(true);
                callbackContext.sendPluginResult(r);
                return true;
            }
            return false;

        } catch (Exception e) {

            callbackContext.error("Error code: " + e);
            return true;
        }
    }
}
