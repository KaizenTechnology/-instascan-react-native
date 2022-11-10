package com.kaizen.reactinstascan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.ReactEventEmitter;
import com.kaizen.instascan.InstaScanBitmapUtils;
import com.kaizen.instascan.InstaScanConfiguration;
import com.kaizen.instascan.InstaScanError;
import com.kaizen.instascan.InstaScanResult;
import com.kaizen.instascan.InstaScanView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.UUID;

public class KZNInstaScanView extends InstaScanView {
    public KZNInstaScanView(Context context) {
        super(context);
    }

    public KZNInstaScanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KZNInstaScanView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void startScan(){
        startScan(this.getConfiguration());
    }


    public void setGuideTextFontName(String guideTextFontName){
        Typeface typeface = createTypeface(guideTextFontName);
        if(typeface != null){
            getConfiguration().getStyle().setGuideTextFont(typeface);
        }
    }

    @Override
    protected Activity getActivity() {
       return ((ThemedReactContext)getContext()).getCurrentActivity();
    }

    @Override
    protected void onPincodeReaded(InstaScanResult result) {
        super.onPincodeReaded(result);

        WritableMap event = Arguments.createMap();
        event.putString("pincode",result.getPincode());

        String imageName = UUID.randomUUID() + ".jpeg";

        try {
            String imagePath = InstaScanBitmapUtils.saveBitmapToCache(getContext(),result.getImage(),imageName);
            event.putString("imagePath", imagePath);

        } catch (IOException e) {
            e.printStackTrace();
        }

        event.putString("configuration",result.getConfiguration().toString());


        ReactContext reactContext = (ReactContext)getContext();

        reactContext
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(),"topPincodeRead",event);

    }

    @Override
    protected void onError(InstaScanError error) {
        super.onError(error);
        WritableMap event = Arguments.createMap();
        event.putString("description",error.getMessage());
        event.putInt("code", error.getCode());

        ReactContext reactContext = (ReactContext)getContext();
        reactContext
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(),"topInstaScanError",event);
    }

    @Override
    protected void onLoad() {
      super.onLoad();
      ReactContext reactContext = (ReactContext)getContext();
      WritableMap event = Arguments.createMap();
      reactContext
            .getJSModule(RCTEventEmitter.class)
            .receiveEvent(getId(),"topInstaScanLoad",event);
    }
}
