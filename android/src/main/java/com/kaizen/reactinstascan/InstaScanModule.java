// InstaScanModule.java

package com.kaizen.reactinstascan;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class InstaScanModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private InstaScanViewManager viewManager;
    public InstaScanModule(ReactApplicationContext reactContext, InstaScanViewManager instaScanViewManager) {
        super(reactContext);
        this.reactContext = reactContext;
        this.viewManager = instaScanViewManager;
    }

    @Override
    public String getName() {
        return "KZNInstaScanView";
    }

    @ReactMethod
    public void startScan() {
        if(viewManager != null){
            viewManager.getInstaScanView().startScan();
        }
    }

    @ReactMethod
    public void stopScan() {
        if(viewManager != null){
            viewManager.getInstaScanView().stopScan();
        }
    }

    @ReactMethod
    public void restartScan() {
        if(viewManager != null){
            viewManager.getInstaScanView().restartScan();
        }
    }

    @ReactMethod
    public void toggleTorch() {
        if(viewManager != null){
            viewManager.getInstaScanView().toggleTorch();
        }
    }

    @ReactMethod
    public void setTorch(boolean on) {
        if(viewManager != null){
            viewManager.getInstaScanView().setTorchStatus(on);
        }
    }

    @ReactMethod
    public void getTorchStatus(Promise promise) {
        if(viewManager != null){
            promise.resolve(viewManager.getInstaScanView().getTorchStatus());
        } else {
            promise.reject("InstaScanInitializationError","KZNInstaScanView is not initialized");
        }
    }

    @ReactMethod
    public void updateGuideText(String guideText) {
        if(viewManager != null){
            viewManager.getInstaScanView().updateGuideText(guideText);
        }
    }
}
