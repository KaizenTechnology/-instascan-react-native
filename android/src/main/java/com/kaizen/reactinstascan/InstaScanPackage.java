// InstaScanPackage.java

package com.kaizen.reactinstascan;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

public class InstaScanPackage implements ReactPackage {
    InstaScanViewManager instaScanViewManager;

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        if (instaScanViewManager == null){
            instaScanViewManager = new InstaScanViewManager(reactContext);
        }
        return Arrays.<NativeModule>asList(new InstaScanModule(reactContext,instaScanViewManager));
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
                instaScanViewManager
        );
    }
}
