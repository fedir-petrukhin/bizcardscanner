package com.cci.bizcard.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.intsig.openapilib.OpenApi;
import com.intsig.openapilib.OpenApiParams;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Random;

public class BizCardScanPlugin extends CordovaPlugin {

    public static final int CARD_SCAN_CODE = 0x1372;

    private OpenApi openApi;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        String apiKey = "4KBgEeNVVH5NPy8hh4NPD0K5";
        openApi = OpenApi.instance(apiKey);
        openApi.isCamCardInstalled(this.cordova.getActivity().getApplicationContext());
        openApi.isExistAppSupportOpenApi(this.cordova.getActivity().getApplicationContext());
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        try {
            OpenApiParams params = new OpenApiParams() {
                {
                    this.setRecognizeLanguage("");
                    this.setReturnCropImage(false);
                }
            };

            if ("scanPhoto".equals(action)) {
                PluginActivity activity = new PluginActivity(callbackContext);
                openApi.recognizeCardByCapture(activity, CARD_SCAN_CODE, params);
                return true;
            }
            return false;

        } catch (Exception e) {

            callbackContext.error("Error code: " + e);
            return true;
        }
    }
}
