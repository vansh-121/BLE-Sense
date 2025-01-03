package com.example.blegame;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 02\u00020\u0001:\u00010B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00140\u0019H\u0003J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u000eH\u0002J\b\u0010\u001c\u001a\u00020\u0014H\u0002J\u0012\u0010\u001d\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0015J-\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00112\u000e\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0#2\u0006\u0010$\u001a\u00020%H\u0016\u00a2\u0006\u0002\u0010&J\b\u0010\'\u001a\u00020\u0017H\u0003J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020*H\u0002J\u0010\u0010-\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u000bH\u0002J\b\u0010/\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/example/blegame/BLEAppActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "bleAdapter", "Lcom/example/blegame/BluetoothAdapterWrapper;", "bleScanCallback", "Landroid/bluetooth/le/ScanCallback;", "deviceAdapter", "Lcom/example/blegame/BLEDeviceAdapter;", "deviceAddresses", "", "", "deviceList", "", "Lcom/example/blegame/BLEDevice;", "deviceRSSI", "", "", "filteredDeviceList", "isScanning", "", "selectedDeviceType", "filterDevicesBy", "", "predicate", "Lkotlin/Function1;", "handleDeviceClick", "device", "hasPermissions", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "refreshBLEScan", "setupDeviceTypeSpinner", "deviceTypeSpinner", "Landroid/widget/Spinner;", "setupFilterSpinner", "filterSpinner", "showToast", "message", "startBLEScan", "Companion", "app_debug"})
public final class BLEAppActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.blegame.BluetoothAdapterWrapper bleAdapter;
    private com.example.blegame.BLEDeviceAdapter deviceAdapter;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.blegame.BLEDevice> deviceList = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.blegame.BLEDevice> filteredDeviceList = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.Integer> deviceRSSI = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> deviceAddresses = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String selectedDeviceType = "SHT40";
    private boolean isScanning = false;
    @org.jetbrains.annotations.NotNull()
    private final android.bluetooth.le.ScanCallback bleScanCallback = null;
    private static final int REQUEST_CODE_PERMISSIONS = 1002;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.blegame.BLEAppActivity.Companion Companion = null;
    
    public BLEAppActivity() {
        super();
    }
    
    @java.lang.Override()
    @android.annotation.SuppressLint(value = {"MissingInflatedId"})
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupFilterSpinner(android.widget.Spinner filterSpinner) {
    }
    
    private final void setupDeviceTypeSpinner(android.widget.Spinner deviceTypeSpinner) {
    }
    
    @android.annotation.SuppressLint(value = {"NotifyDataSetChanged"})
    private final void filterDevicesBy(kotlin.jvm.functions.Function1<? super com.example.blegame.BLEDevice, java.lang.Boolean> predicate) {
    }
    
    private final void handleDeviceClick(com.example.blegame.BLEDevice device) {
    }
    
    @android.annotation.SuppressLint(value = {"NotifyDataSetChanged"})
    private final void refreshBLEScan() {
    }
    
    private final void startBLEScan() {
    }
    
    private final boolean hasPermissions() {
        return false;
    }
    
    private final void showToast(java.lang.String message) {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/blegame/BLEAppActivity$Companion;", "", "()V", "REQUEST_CODE_PERMISSIONS", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}