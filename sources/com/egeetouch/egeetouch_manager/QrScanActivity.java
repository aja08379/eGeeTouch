package com.egeetouch.egeetouch_manager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;
import java.io.IOException;
/* loaded from: classes.dex */
public class QrScanActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    SweetAlertDialog dialog;
    Handler handler;
    SurfaceView surfaceView;
    String scannedText = "";
    boolean isAuthenticating = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_qr_scan);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.handler = new Handler(Looper.getMainLooper());
        initViews();
    }

    private void initViews() {
        this.surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
    }

    private void initialiseDetectorsAndSources() {
        Toast.makeText(getApplicationContext(), "QR scanner started", 0).show();
        this.barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(0).build();
        this.cameraSource = new CameraSource.Builder(this, this.barcodeDetector).setRequestedPreviewSize(1920, 1080).setAutoFocusEnabled(true).build();
        this.surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() { // from class: com.egeetouch.egeetouch_manager.QrScanActivity.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(QrScanActivity.this, "android.permission.CAMERA") == 0) {
                        QrScanActivity.this.cameraSource.start(QrScanActivity.this.surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(QrScanActivity.this, new String[]{"android.permission.CAMERA"}, QrScanActivity.REQUEST_CAMERA_PERMISSION);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                QrScanActivity.this.cameraSource.stop();
            }
        });
        this.barcodeDetector.setProcessor(new AnonymousClass2());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.egeetouch.egeetouch_manager.QrScanActivity$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements Detector.Processor<Barcode> {
        @Override // com.google.android.gms.vision.Detector.Processor
        public void release() {
        }

        AnonymousClass2() {
        }

        @Override // com.google.android.gms.vision.Detector.Processor
        public void receiveDetections(Detector.Detections<Barcode> detections) {
            SparseArray<Barcode> detectedItems = detections.getDetectedItems();
            if (detectedItems.size() != 0) {
                QrScanActivity.this.handler.post(new AnonymousClass1(detectedItems));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.egeetouch.egeetouch_manager.QrScanActivity$2$1  reason: invalid class name */
        /* loaded from: classes.dex */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ SparseArray val$barcodes;

            AnonymousClass1(SparseArray sparseArray) {
                this.val$barcodes = sparseArray;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!((Barcode) this.val$barcodes.valueAt(0)).displayValue.contains("WatchRequestManager:") || QrScanActivity.this.isAuthenticating) {
                    return;
                }
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    QrScanActivity.this.isAuthenticating = true;
                    QrScanActivity.this.scannedText = ((Barcode) this.val$barcodes.valueAt(0)).displayValue;
                    System.out.println("CMDCMD barcode msg: " + QrScanActivity.this.scannedText);
                    QrScanActivity.this.scannedText = QrScanActivity.this.scannedText.replace("WatchRequestManager:", "");
                    System.out.println("CMDCMD barcode msg: " + QrScanActivity.this.scannedText);
                    QrScanActivity.this.dialog = new SweetAlertDialog(QrScanActivity.this, 5).setTitleText("Authenticating Smartwatch");
                    QrScanActivity.this.dialog.showCancelButton(true).setCancelText(QrScanActivity.this.getResources().getString(R.string.cancel)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.QrScanActivity.2.1.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            QrScanActivity.this.dialog.dismiss();
                            QrScanActivity.this.finish();
                        }
                    });
                    QrScanActivity.this.dialog.setCancelable(false);
                    QrScanActivity.this.dialog.setCanceledOnTouchOutside(false);
                    QrScanActivity.this.dialog.show();
                    FirebaseFunctions.getInstance().getHttpsCallable("wear-auth").call().addOnSuccessListener(new OnSuccessListener<HttpsCallableResult>() { // from class: com.egeetouch.egeetouch_manager.QrScanActivity.2.1.3
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public void onSuccess(HttpsCallableResult httpsCallableResult) {
                            System.out.println("CMDCMD success: " + httpsCallableResult.getData().toString());
                            FirebaseDatabase.getInstance().getReference("watchAuth").child(QrScanActivity.this.scannedText).setValue(httpsCallableResult.getData().toString());
                            QrScanActivity.this.dialog.dismiss();
                            QrScanActivity.this.finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() { // from class: com.egeetouch.egeetouch_manager.QrScanActivity.2.1.2
                        @Override // com.google.android.gms.tasks.OnFailureListener
                        public void onFailure(Exception exc) {
                            System.out.println("CMDCMD FAILURE " + exc.toString());
                            QrScanActivity.this.dialog.dismiss();
                            QrScanActivity.this.dialog = new SweetAlertDialog(QrScanActivity.this, 1).setTitleText(QrScanActivity.this.getResources().getString(R.string.error));
                            QrScanActivity.this.dialog.setConfirmText(QrScanActivity.this.getResources().getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.QrScanActivity.2.1.2.1
                                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    QrScanActivity.this.dialog.dismiss();
                                    QrScanActivity.this.isAuthenticating = false;
                                }
                            });
                            QrScanActivity.this.dialog.setCancelable(false);
                            QrScanActivity.this.dialog.setCanceledOnTouchOutside(false);
                            QrScanActivity.this.dialog.show();
                        }
                    });
                    return;
                }
                Toast.makeText(QrScanActivity.this, "You are not signed in", 0).show();
            }
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.cameraSource.release();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();
    }
}
