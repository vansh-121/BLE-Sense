package com.example.blegame;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\u0018\u0000 .2\u00020\u0001:\u0001.B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000eH\u0002J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\"\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00112\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0012\u0010 \u001a\u00020\u00182\b\u0010!\u001a\u0004\u0018\u00010\"H\u0015J-\u0010#\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00112\u000e\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0%2\u0006\u0010&\u001a\u00020\'H\u0017\u00a2\u0006\u0002\u0010(J\b\u0010)\u001a\u00020\u0018H\u0003J\u0010\u0010*\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u000bH\u0002J\b\u0010,\u001a\u00020\u0018H\u0003J\b\u0010-\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lcom/example/blegame/GameDeviceListActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "bleAdapter", "Lcom/example/blegame/BluetoothAdapterWrapper;", "bleScanCallback", "Landroid/bluetooth/le/ScanCallback;", "deviceAdapter", "Lcom/example/blegame/BLEDeviceAdapter;", "deviceAddresses", "", "", "deviceList", "", "Lcom/example/blegame/BLEDevice;", "deviceRSSI", "", "", "isScanning", "", "selectedDevice", "targetDevices", "", "handleDeviceClick", "", "device", "hasPermissions", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "refreshBLEScan", "showToast", "message", "startBLEScan", "stopBLEScan", "Companion", "app_debug"})
public final class GameDeviceListActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.blegame.BluetoothAdapterWrapper bleAdapter;
    private com.example.blegame.BLEDeviceAdapter deviceAdapter;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.blegame.BLEDevice> deviceList = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.Integer> deviceRSSI = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> deviceAddresses = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String selectedDevice;
    private boolean isScanning = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> targetDevices = null;
    @org.jetbrains.annotations.NotNull()
    private final android.bluetooth.le.ScanCallback bleScanCallback = null;
    private static final int GAMEPLAY_ACTIVITY_REQUEST_CODE = 1001;
    private static final int REQUEST_CODE_PERMISSIONS = 1002;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.blegame.GameDeviceListActivity.Companion Companion = null;
    
    public GameDeviceListActivity() {
        super();
    }
    
    @java.lang.Override()
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void handleDeviceClick(com.example.blegame.BLEDevice device) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    private final void refreshBLEScan() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    private final void startBLEScan() {
    }
    
    private final void stopBLEScan() {
    }
    
    private final boolean hasPermissions() {
        return false;
    }
    
    private final void showToast(java.lang.String message) {
    }
    
    @java.lang.Override()
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/example/blegame/GameDeviceListActivity$Companion;", "", "()V", "GAMEPLAY_ACTIVITY_REQUEST_CODE", "", "REQUEST_CODE_PERMISSIONS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}