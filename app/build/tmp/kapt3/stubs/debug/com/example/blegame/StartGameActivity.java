package com.example.blegame;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0015J\b\u0010\r\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/blegame/StartGameActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "PERMISSION_REQUEST_CODE", "", "bleAdapter", "Lcom/example/blegame/BluetoothAdapterWrapper;", "isLocationEnabled", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "promptEnableLocation", "requestPermissions", "app_debug"})
public final class StartGameActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.blegame.BluetoothAdapterWrapper bleAdapter;
    private final int PERMISSION_REQUEST_CODE = 1001;
    
    public StartGameActivity() {
        super();
    }
    
    @java.lang.Override()
    @android.annotation.SuppressLint(value = {"MissingInflatedId"})
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void requestPermissions() {
    }
    
    private final boolean isLocationEnabled() {
        return false;
    }
    
    private final void promptEnableLocation() {
    }
}