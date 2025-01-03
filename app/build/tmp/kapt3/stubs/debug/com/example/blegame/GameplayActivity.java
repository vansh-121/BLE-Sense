package com.example.blegame;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\u0018\u0000 &2\u00020\u0001:\u0001&B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0015J-\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\u000e\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0017\u00a2\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\u0016H\u0002J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\nH\u0002J\b\u0010$\u001a\u00020\u0016H\u0003J\b\u0010%\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/example/blegame/GameplayActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "bleAdapter", "Lcom/example/blegame/BluetoothAdapterWrapper;", "bleScanCallback", "Landroid/bluetooth/le/ScanCallback;", "checkBoxDeviceFound", "Landroid/widget/CheckBox;", "deviceAddress", "", "deviceMacAddressTextView", "Landroid/widget/TextView;", "deviceName", "deviceNameTextView", "isScanning", "", "lastState", "rssiTextView", "stateTextView", "hasPermissions", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "showDeviceFoundPopup", "showToast", "message", "startBLEScan", "stopBLEScan", "Companion", "app_debug"})
public final class GameplayActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.blegame.BluetoothAdapterWrapper bleAdapter;
    private boolean isScanning = false;
    private android.widget.TextView deviceNameTextView;
    private android.widget.TextView rssiTextView;
    private android.widget.TextView deviceMacAddressTextView;
    private android.widget.TextView stateTextView;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String deviceAddress;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String deviceName;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String lastState;
    private android.widget.CheckBox checkBoxDeviceFound;
    @org.jetbrains.annotations.NotNull()
    private final android.bluetooth.le.ScanCallback bleScanCallback = null;
    private static final int REQUEST_CODE_PERMISSIONS = 1;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.blegame.GameplayActivity.Companion Companion = null;
    
    public GameplayActivity() {
        super();
    }
    
    @java.lang.Override()
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    private final void startBLEScan() {
    }
    
    private final void stopBLEScan() {
    }
    
    private final void showDeviceFoundPopup() {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/blegame/GameplayActivity$Companion;", "", "()V", "REQUEST_CODE_PERMISSIONS", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}