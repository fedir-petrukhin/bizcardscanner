package com.cci.bizcard.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.intsig.openapilib.OpenApi;
import com.intsig.openapilib.OpenApiParams;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Random;

public class BizCardScanPlugin extends CordovaPlugin {

    public static final int CARD_SCAN_CODE = 0x1372;

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        try {
            String apiKey = "4KBgEeNVVH5NPy8hh4NPD0K5";

            OpenApi openApi = OpenApi.instance(apiKey);
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
            } else if ("isCamCardInstalled".equals(action)) {
                callbackContext.success(
                        String.valueOf(openApi.isCamCardInstalled(this.cordova.getActivity().getApplicationContext()))
                );
            } else if ("isExistAppSupportOpenApi".equals(action)) {
                callbackContext.success(
                        String.valueOf(openApi.isExistAppSupportOpenApi(this.cordova.getActivity().getApplicationContext()))
                );
            } else if ("getVersion".equals(action)) {
                callbackContext.success(
                        String.valueOf(openApi.getVersion())
                );
            }
            return false;

        } catch (Exception e) {

            callbackContext.error("Error code: " + e);
            return false;
        }
    }
}
