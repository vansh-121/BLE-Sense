package com.example.blegame;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 &2\u00020\u0001:\u0001&B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0015J\b\u0010\u0012\u001a\u00020\u000fH\u0014J-\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u000e\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0016\u00a2\u0006\u0002\u0010\u001bJ\u001a\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010 \u001a\u00020\u000fH\u0002J\u0010\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u0018H\u0002J\"\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u00182\b\u0010\u001f\u001a\u0004\u0018\u00010\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/example/blegame/AdvertisingDataActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "advertisingDataDetails", "Landroid/widget/TextView;", "bleAdapter", "Lcom/example/blegame/BluetoothAdapterWrapper;", "deviceInfo", "scanCallback", "Landroid/bluetooth/le/ScanCallback;", "temperatureMeterView", "Lcom/example/blegame/TemperatureViewMeter;", "checkPermissions", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "parseAdvertisingData", "result", "Landroid/bluetooth/le/ScanResult;", "deviceType", "requestPermissions", "showToast", "message", "startRealTimeScan", "selectedDeviceAddress", "selectedDeviceName", "Companion", "app_debug"})
public final class AdvertisingDataActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.blegame.BluetoothAdapterWrapper bleAdapter;
    private android.widget.TextView deviceInfo;
    private android.widget.TextView advertisingDataDetails;
    private com.example.blegame.TemperatureViewMeter temperatureMeterView;
    @org.jetbrains.annotations.Nullable()
    private android.bluetooth.le.ScanCallback scanCallback;
    private static final int REQUEST_CODE_PERMISSIONS = 1;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "AdvertisingDataActivity";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.blegame.AdvertisingDataActivity.Companion Companion = null;
    
    public AdvertisingDataActivity() {
        super();
    }
    
    @java.lang.Override()
    @android.annotation.SuppressLint(value = {"MissingInflatedId", "SetTextI18n"})
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final boolean checkPermissions() {
        return false;
    }
    
    private final void requestPermissions() {
    }
    
    private final void startRealTimeScan(java.lang.String selectedDeviceAddress, java.lang.String selectedDeviceName, java.lang.String deviceType) {
    }
    
    private final java.lang.String parseAdvertisingData(android.bluetooth.le.ScanResult result, java.lang.String deviceType) {
        return null;
    }
    
    private final void showToast(java.lang.String message) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/example/blegame/AdvertisingDataActivity$Companion;", "", "()V", "REQUEST_CODE_PERMISSIONS", "", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}