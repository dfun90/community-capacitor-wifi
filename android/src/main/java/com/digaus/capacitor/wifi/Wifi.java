package com.digaus.capacitor.wifi;

import android.Manifest;
import android.os.Build;

import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

import java.util.Objects;

@CapacitorPlugin(
        name = "Wifi",
        permissions = {
                @Permission(
                        alias = "fineLocation",
                        strings = {Manifest.permission.ACCESS_FINE_LOCATION}
                ),
                @Permission(
                        alias = "wifiState",
                        strings = {Manifest.permission.ACCESS_WIFI_STATE}
                )
        }
)

public class Wifi extends Plugin {

    public static final String WIFI_CHANGE_EVENT = "wifiStatusChange";
    private static final int API_VERSION = Build.VERSION.SDK_INT;
    WifiService wifiService;

    @Override
    public void load() {
        super.load();
        this.wifiService = new WifiService();
        this.wifiService.load(this.bridge);

        // Bind listener for WiFi connection status lifecycle
        WifiService.WifiStatusChangeListener listener = wasLostEvent -> {
            JSObject jsObject = new JSObject();
            jsObject.put("connected", !wasLostEvent);
            notifyListeners(WIFI_CHANGE_EVENT, jsObject);
        };
        wifiService.setStatusChangeListener(listener);
    }

    /**
     * Clean up callback to prevent leaks.
     */
    @Override
    protected void handleOnDestroy() {
        this.wifiService.setStatusChangeListener(null);
    }

    @PluginMethod()
    public void getIP(PluginCall call) {
        if (API_VERSION >= 23 && getPermissionState("fineLocation") != PermissionState.GRANTED) {
            requestPermissionForAlias("fineLocation", call, "accessFineLocation");
        } else {
            this.wifiService.getIP(call);
        }
    }

    /**
     * Gets the SSID for the access point connected to
     */
    @PluginMethod()
    public void getSSID(PluginCall call) {
        if (getPermissionState("fineLocation") != PermissionState.GRANTED) {
            requestPermissionForAlias("fineLocation", call, "accessFineLocation");
        } else {
            this.wifiService.getSSID(call);
        }
    }

    /**
     * Gets the cached nearby WiFi access points
     */
    @PluginMethod()
    public void getScanResults(PluginCall call) {
        if (getPermissionState("fineLocation") != PermissionState.GRANTED) {
            requestPermissionForAlias("fineLocation", call, "accessFineLocation");
        }
        if (getPermissionState("wifiState") != PermissionState.GRANTED) {
            requestPermissionForAlias("wifiState", call, "wifiState");
        } else {
            Logger.debug("Wifibla", "WIFI STATE GRANTED");
            this.wifiService.getScanResults(call);
        }
    }

    @PluginMethod()
    public void connect(PluginCall call) {
        if (!call.getData().has("ssid") || Objects.requireNonNull(call.getString("ssid")).isEmpty()) {
            call.reject("Must provide an ssid");
            return;
        }
        if (API_VERSION >= 23 && getPermissionState("fineLocation") != PermissionState.GRANTED) {
            requestPermissionForAlias("fineLocation", call, "accessFineLocation");
        } else {
            this.wifiService.connect(call);
        }
    }

    @PluginMethod()
    public void connectPrefix(PluginCall call) {
        if (!call.getData().has("ssid")) {
            call.reject("Must provide an ssid");
            return;
        }
        if (API_VERSION >= 23 && getPermissionState("fineLocation") != PermissionState.GRANTED) {
            requestPermissionForAlias("fineLocation", call, "accessFineLocation");
        } else {
            this.wifiService.connectPrefix(call);
        }

    }

    @PluginMethod()
    public void disconnect(PluginCall call) {
        this.wifiService.disconnect(call);
    }

    @PermissionCallback
    private void accessFineLocation(PluginCall call) {
        if (getPermissionState("fineLocation") == PermissionState.GRANTED) {
            if (call.getMethodName().equals("getSSID")) {
                this.wifiService.getSSID(call);
            } else if (call.getMethodName().equals("getIP")) {
                this.wifiService.getIP(call);
            } else if (call.getMethodName().equals("connect")) {
                this.wifiService.connect(call);
            } else if (call.getMethodName().equals("connectPrefix")) {
                this.wifiService.connectPrefix(call);
            }
        } else {
            call.reject("User denied permission");
        }
    }

    @PermissionCallback
    private void wifiState(PluginCall call) {
        Logger.debug("wifiState cb");
        if (getPermissionState("wifiState") == PermissionState.GRANTED) {
            if (call.getMethodName().equals("getScanResults")) {
                this.wifiService.getScanResults(call);
            }
        } else {
            call.reject("User denied permission wifiState");
        }
    }
}
