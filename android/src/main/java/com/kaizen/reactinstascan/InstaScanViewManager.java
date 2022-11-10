package com.kaizen.reactinstascan;

import android.graphics.Color;
import android.graphics.Typeface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.kaizen.instascan.InstaScanResolution;

import java.util.HashMap;
import java.util.Map;

import kotlin.text.UStringsKt;

public class InstaScanViewManager extends SimpleViewManager<KZNInstaScanView> {
    ReactApplicationContext mCallerContext;

    private KZNInstaScanView instaScanView;
    public InstaScanViewManager(ReactApplicationContext reactContext) {
       mCallerContext = reactContext;
    }

    @NonNull
    @Override
    public String getName() {
        return "KZNInstaScanView";
    }

    @NonNull
    @Override
    protected KZNInstaScanView createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        instaScanView = new KZNInstaScanView(themedReactContext);
        return instaScanView;
    }

    public KZNInstaScanView getInstaScanView() {
        return instaScanView;
    }

    @ReactProp(name = "apiKey")
    public void setApiKey(KZNInstaScanView view, String apiKey) {
        view.getConfiguration().setApiKey(apiKey);
    }
    @ReactProp(name = "guideText")
    public void setGuideText(KZNInstaScanView view, String guideText) {
        view.getConfiguration().setGuideText(guideText);
    }

    @ReactProp(name = "zoomFactor", defaultDouble = 2.0)
    public void setZoomLevel(KZNInstaScanView view, double zoomFactor) {
        view.getConfiguration().getSettings().setZoomLevel(zoomFactor);
    }
    @ReactProp(name = "resolution", defaultInt = 2)
    public void setResolution(KZNInstaScanView view, int resolution) {
        InstaScanResolution res = InstaScanResolution.values()[2];
        view.getConfiguration().getSettings().setResolution(res);
    }
    @ReactProp(name = "sampleCount", defaultInt = 2)
    public void setSampleCount(KZNInstaScanView view, int sampleCount) {
        view.getConfiguration().getSettings().setSampleCount(sampleCount);
    }
    @ReactProp(name = "minTextHeight", defaultDouble = 0.5)
    public void setMinTextHeight(KZNInstaScanView view, double minTextHeight) {
        view.getConfiguration().getSettings().setMinTextHeight(minTextHeight);
    }
    @ReactProp(name = "guideAreaWidthRatio", defaultDouble = 0.8)
    public void setGuideAreaWidthRatio(KZNInstaScanView view, double guideAreaWidthRatio) {
        view.getConfiguration().getSettings().setGuideAreaWidthRatio(guideAreaWidthRatio);
    }
    @ReactProp(name = "guideAreaAspectRatio", defaultDouble = 5.0)
    public void setGuideAreaAspectRatio(KZNInstaScanView view, double guideAreaAspectRatio) {
        view.getConfiguration().getSettings().setGuideAreaAspectRatio(guideAreaAspectRatio);
    }

    @ReactProp(name = "minDigits", defaultInt = 6)
    public void setMinDigits(KZNInstaScanView view, int minDigits) {
        view.getConfiguration().getRules().setMinDigits(minDigits);
    }
    @ReactProp(name = "maxDigits", defaultInt = 12)
    public void setMaxDigits(KZNInstaScanView view, int maxDigits) {
        view.getConfiguration().getRules().setMaxDigits(maxDigits);
    }
    @ReactProp(name = "allowedChars")
    public void setMaxDigits(KZNInstaScanView view, String allowedChars) {
        view.getConfiguration().getRules().setAllowedChars(allowedChars);
    }
    @ReactProp(name = "replaceMap")
    public void setReplaceMap(KZNInstaScanView view, ReadableMap replaceMap) {
        HashMap<String,String> map = (HashMap) replaceMap.toHashMap();
        view.getConfiguration().getRules().setReplaceMap(map);
    }

    @ReactProp(name = "overlayColor")
    public void setOverlayColor(KZNInstaScanView view, String overlayColor) {
        view.getConfiguration().getStyle().setOverlayColor(Color.parseColor(overlayColor));
    }
    @ReactProp(name = "validTextHighlightColor")
    public void setValidTextHighlightColor(KZNInstaScanView view, String validTextHighlightColor) {
        view.getConfiguration().getStyle().setValidTextHighlightColor(Color.parseColor(validTextHighlightColor));
    }
    @ReactProp(name = "invalidTextHighlightColor")
    public void setInvalidTextHighlightColor(KZNInstaScanView view, String invalidTextHighlightColor) {
        view.getConfiguration().getStyle().setInvalidTextHighlightColor(Color.parseColor(invalidTextHighlightColor));
    }
    @ReactProp(name = "guideTextColor")
    public void setGuideTextColor(KZNInstaScanView view, String guideTextColor) {
        view.getConfiguration().getStyle().setGuideTextColor(Color.parseColor(guideTextColor));
    }
    @ReactProp(name = "guideTextFontSize", defaultFloat = 16.0f)
    public void guideTextFontSize(KZNInstaScanView view, float guideTextFontSize) {
        view.getConfiguration().getStyle().setGuideTextSize(guideTextFontSize);
    }
    @ReactProp(name = "guideTextFontName")
    public void setGuideTextFontName(KZNInstaScanView view, String guideTextFontName) {
        view.setGuideTextFontName(guideTextFontName);
    }


     public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put(
                "topPincodeRead",
                MapBuilder.of(
                        "phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onPincodeRead")
                )
        ).put("topInstaScanError", MapBuilder.of(
                    "phasedRegistrationNames",
                    MapBuilder.of("bubbled", "onInstaScanError")
                )
        ).put("topInstaScanLoad", MapBuilder.of(
                    "phasedRegistrationNames",
                    MapBuilder.of("bubbled", "onInstaScanLoad")
                )
        ).build();
    }

    @Override
    public void receiveCommand(@NonNull KZNInstaScanView root, String commandId, @Nullable ReadableArray args) {
        super.receiveCommand(root, commandId, args);
        switch (commandId) {
            case "startScan":
                root.startScan();
                break;
            case "stopScan":
                root.stopScan();
                break;
            case "restartScan":
                root.restartScan();
                break;
            case "setTorch":
                root.setTorchStatus(args.getBoolean(0));
                break;
            case "toggleTorch":
                root.toggleTorch();
                break;
            case "updateGuideText":
                root.updateGuideText(args.getString(0));
                break;
        }

    }
}
