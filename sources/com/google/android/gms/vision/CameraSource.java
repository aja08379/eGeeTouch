package com.google.android.gms.vision;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.vision.Frame;
import com.google.firebase.database.core.ValidationPath;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.IdentityHashMap;
import javax.annotation.Nullable;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
public class CameraSource {
    public static final int CAMERA_FACING_BACK = 0;
    public static final int CAMERA_FACING_FRONT = 1;
    private Context zza;
    private final Object zzb;
    @Nullable
    private Camera zzc;
    private int zzd;
    private int zze;
    private Size zzf;
    private float zzg;
    private int zzh;
    private int zzi;
    private boolean zzj;
    @Nullable
    private String zzk;
    @Nullable
    private SurfaceTexture zzl;
    @Nullable
    private Thread zzm;
    private zza zzn;
    private final IdentityHashMap<byte[], ByteBuffer> zzo;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
    /* loaded from: classes2.dex */
    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
    /* loaded from: classes2.dex */
    public interface ShutterCallback {
        void onShutter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
    /* loaded from: classes2.dex */
    public class zzb implements Camera.PreviewCallback {
        private zzb() {
        }

        @Override // android.hardware.Camera.PreviewCallback
        public final void onPreviewFrame(byte[] bArr, Camera camera) {
            CameraSource.this.zzn.zza(bArr, camera);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
    /* loaded from: classes2.dex */
    private class zzc implements Camera.PictureCallback {
        @Nullable
        private PictureCallback zza;

        private zzc() {
        }

        @Override // android.hardware.Camera.PictureCallback
        public final void onPictureTaken(byte[] bArr, Camera camera) {
            PictureCallback pictureCallback = this.zza;
            if (pictureCallback != null) {
                pictureCallback.onPictureTaken(bArr);
            }
            synchronized (CameraSource.this.zzb) {
                if (CameraSource.this.zzc != null) {
                    CameraSource.this.zzc.startPreview();
                }
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
    /* loaded from: classes2.dex */
    private static class zzd implements Camera.ShutterCallback {
        @Nullable
        private ShutterCallback zza;

        private zzd() {
        }

        @Override // android.hardware.Camera.ShutterCallback
        public final void onShutter() {
            ShutterCallback shutterCallback = this.zza;
            if (shutterCallback != null) {
                shutterCallback.onShutter();
            }
        }
    }

    public void release() {
        synchronized (this.zzb) {
            stop();
            this.zzn.zza();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
    /* loaded from: classes2.dex */
    public static class zze {
        private Size zza;
        private Size zzb;

        public zze(Camera.Size size, @Nullable Camera.Size size2) {
            this.zza = new Size(size.width, size.height);
            if (size2 != null) {
                this.zzb = new Size(size2.width, size2.height);
            }
        }

        public final Size zza() {
            return this.zza;
        }

        @Nullable
        public final Size zzb() {
            return this.zzb;
        }
    }

    public CameraSource start() throws IOException {
        synchronized (this.zzb) {
            if (this.zzc != null) {
                return this;
            }
            this.zzc = zza();
            SurfaceTexture surfaceTexture = new SurfaceTexture(100);
            this.zzl = surfaceTexture;
            this.zzc.setPreviewTexture(surfaceTexture);
            this.zzc.startPreview();
            Thread thread = new Thread(this.zzn);
            this.zzm = thread;
            thread.setName("gms.vision.CameraSource");
            this.zzn.zza(true);
            Thread thread2 = this.zzm;
            if (thread2 != null) {
                thread2.start();
            }
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
    /* loaded from: classes2.dex */
    public class zza implements Runnable {
        @Nullable
        private Detector<?> zza;
        private long zze;
        @Nullable
        private ByteBuffer zzg;
        private long zzb = SystemClock.elapsedRealtime();
        private final Object zzc = new Object();
        private boolean zzd = true;
        private int zzf = 0;

        zza(Detector<?> detector) {
            this.zza = detector;
        }

        final void zza() {
            Detector<?> detector = this.zza;
            if (detector != null) {
                detector.release();
                this.zza = null;
            }
        }

        final void zza(boolean z) {
            synchronized (this.zzc) {
                this.zzd = z;
                this.zzc.notifyAll();
            }
        }

        final void zza(byte[] bArr, Camera camera) {
            synchronized (this.zzc) {
                ByteBuffer byteBuffer = this.zzg;
                if (byteBuffer != null) {
                    camera.addCallbackBuffer(byteBuffer.array());
                    this.zzg = null;
                }
                if (!CameraSource.this.zzo.containsKey(bArr)) {
                    Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
                    return;
                }
                this.zze = SystemClock.elapsedRealtime() - this.zzb;
                this.zzf++;
                this.zzg = (ByteBuffer) CameraSource.this.zzo.get(bArr);
                this.zzc.notifyAll();
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            boolean z;
            Frame build;
            ByteBuffer byteBuffer;
            while (true) {
                synchronized (this.zzc) {
                    while (true) {
                        z = this.zzd;
                        if (!z || this.zzg != null) {
                            break;
                        }
                        try {
                            this.zzc.wait();
                        } catch (InterruptedException e) {
                            Log.d("CameraSource", "Frame processing loop terminated.", e);
                            return;
                        }
                    }
                    if (!z) {
                        return;
                    }
                    build = new Frame.Builder().setImageData((ByteBuffer) Preconditions.checkNotNull(this.zzg), CameraSource.this.zzf.getWidth(), CameraSource.this.zzf.getHeight(), 17).setId(this.zzf).setTimestampMillis(this.zze).setRotation(CameraSource.this.zze).build();
                    byteBuffer = this.zzg;
                    this.zzg = null;
                }
                try {
                    ((Detector) Preconditions.checkNotNull(this.zza)).receiveFrame(build);
                } catch (Exception e2) {
                    Log.e("CameraSource", "Exception thrown from receiver.", e2);
                } finally {
                    ((Camera) Preconditions.checkNotNull(CameraSource.this.zzc)).addCallbackBuffer(((ByteBuffer) Preconditions.checkNotNull(byteBuffer)).array());
                }
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
    /* loaded from: classes2.dex */
    public static class Builder {
        private final Detector<?> zza;
        private CameraSource zzb;

        public Builder(Context context, Detector<?> detector) {
            CameraSource cameraSource = new CameraSource();
            this.zzb = cameraSource;
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            }
            if (detector == null) {
                throw new IllegalArgumentException("No detector supplied.");
            }
            this.zza = detector;
            cameraSource.zza = context;
        }

        public Builder setRequestedFps(float f) {
            if (f <= 0.0f) {
                throw new IllegalArgumentException(new StringBuilder(28).append("Invalid fps: ").append(f).toString());
            }
            this.zzb.zzg = f;
            return this;
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            if (i <= 0 || i > 1000000 || i2 <= 0 || i2 > 1000000) {
                throw new IllegalArgumentException(new StringBuilder(45).append("Invalid preview size: ").append(i).append("x").append(i2).toString());
            }
            this.zzb.zzh = i;
            this.zzb.zzi = i2;
            return this;
        }

        public Builder setFacing(int i) {
            if (i != 0 && i != 1) {
                throw new IllegalArgumentException(new StringBuilder(27).append("Invalid camera: ").append(i).toString());
            }
            this.zzb.zzd = i;
            return this;
        }

        public Builder setAutoFocusEnabled(boolean z) {
            this.zzb.zzj = z;
            return this;
        }

        public Builder setFocusMode(String str) {
            if (!str.equals("continuous-video") && !str.equals("continuous-picture")) {
                Log.w("CameraSource", String.format("FocusMode %s is not supported for now.", str));
                this.zzb.zzk = null;
            } else {
                this.zzb.zzk = str;
            }
            return this;
        }

        public CameraSource build() {
            CameraSource cameraSource = this.zzb;
            CameraSource cameraSource2 = this.zzb;
            cameraSource2.getClass();
            cameraSource.zzn = new zza(this.zza);
            return this.zzb;
        }
    }

    public CameraSource start(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this.zzb) {
            if (this.zzc != null) {
                return this;
            }
            Camera zza2 = zza();
            this.zzc = zza2;
            zza2.setPreviewDisplay(surfaceHolder);
            this.zzc.startPreview();
            this.zzm = new Thread(this.zzn);
            this.zzn.zza(true);
            Thread thread = this.zzm;
            if (thread != null) {
                thread.start();
            }
            return this;
        }
    }

    public void stop() {
        synchronized (this.zzb) {
            this.zzn.zza(false);
            Thread thread = this.zzm;
            if (thread != null) {
                try {
                    thread.join();
                } catch (InterruptedException unused) {
                    Log.d("CameraSource", "Frame processing thread interrupted on release.");
                }
                this.zzm = null;
            }
            Camera camera = this.zzc;
            if (camera != null) {
                camera.stopPreview();
                this.zzc.setPreviewCallbackWithBuffer(null);
                try {
                    this.zzc.setPreviewTexture(null);
                    this.zzl = null;
                    this.zzc.setPreviewDisplay(null);
                } catch (Exception e) {
                    String valueOf = String.valueOf(e);
                    Log.e("CameraSource", new StringBuilder(String.valueOf(valueOf).length() + 32).append("Failed to clear camera preview: ").append(valueOf).toString());
                }
                ((Camera) Preconditions.checkNotNull(this.zzc)).release();
                this.zzc = null;
            }
            this.zzo.clear();
        }
    }

    public Size getPreviewSize() {
        return this.zzf;
    }

    public int getCameraFacing() {
        return this.zzd;
    }

    public void takePicture(@Nullable ShutterCallback shutterCallback, @Nullable PictureCallback pictureCallback) {
        synchronized (this.zzb) {
            if (this.zzc != null) {
                zzd zzdVar = new zzd();
                zzdVar.zza = shutterCallback;
                zzc zzcVar = new zzc();
                zzcVar.zza = pictureCallback;
                this.zzc.takePicture(zzdVar, null, null, zzcVar);
            }
        }
    }

    private CameraSource() {
        this.zzb = new Object();
        this.zzd = 0;
        this.zzg = 30.0f;
        this.zzh = 1024;
        this.zzi = ValidationPath.MAX_PATH_LENGTH_BYTES;
        this.zzj = false;
        this.zzo = new IdentityHashMap<>();
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0208  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final android.hardware.Camera zza() throws java.io.IOException {
        int r2;
        android.hardware.Camera.CameraInfo r5;
        int r4;
        int r2;
        int r1 = r17.zzd;
        android.hardware.Camera.CameraInfo r2 = new android.hardware.Camera.CameraInfo();
        int r4 = 0;
        while (true) {
            if (r4 >= android.hardware.Camera.getNumberOfCameras()) {
                r4 = -1;
                break;
            }
            android.hardware.Camera.getCameraInfo(r4, r2);
            if (r2.facing == r1) {
                break;
            }
            r4++;
        }
        if (r4 == -1) {
            throw new java.io.IOException("Could not find requested camera.");
        }
        android.hardware.Camera r1 = android.hardware.Camera.open(r4);
        int r2 = r17.zzh;
        int r5 = r17.zzi;
        android.hardware.Camera.Parameters r6 = r1.getParameters();
        java.util.List<android.hardware.Camera.Size> r7 = r6.getSupportedPreviewSizes();
        java.util.List<android.hardware.Camera.Size> r6 = r6.getSupportedPictureSizes();
        java.util.ArrayList r8 = new java.util.ArrayList();
        for (android.hardware.Camera.Size r10 : r7) {
            float r11 = r10.width / r10.height;
            java.util.Iterator<android.hardware.Camera.Size> r12 = r6.iterator();
            while (true) {
                if (r12.hasNext()) {
                    android.hardware.Camera.Size r13 = r12.next();
                    if (java.lang.Math.abs(r11 - (r13.width / r13.height)) < 0.01f) {
                        r8.add(new com.google.android.gms.vision.CameraSource.zze(r10, r13));
                        break;
                    }
                }
            }
        }
        if (r8.size() == 0) {
            android.util.Log.w("CameraSource", "No preview sizes have a corresponding same-aspect-ratio picture size");
            for (android.hardware.Camera.Size r7 : r7) {
                r8.add(new com.google.android.gms.vision.CameraSource.zze(r7, null));
            }
        }
        java.util.ArrayList r8 = r8;
        int r6 = r8.size();
        int r7 = Integer.MAX_VALUE;
        int r11 = 0;
        int r13 = Integer.MAX_VALUE;
        com.google.android.gms.vision.CameraSource.zze r12 = null;
        while (r11 < r6) {
            java.lang.Object r14 = r8.get(r11);
            r11++;
            com.google.android.gms.vision.CameraSource.zze r14 = (com.google.android.gms.vision.CameraSource.zze) r14;
            com.google.android.gms.common.images.Size r15 = r14.zza();
            int r15 = java.lang.Math.abs(r15.getWidth() - r2) + java.lang.Math.abs(r15.getHeight() - r5);
            if (r15 < r13) {
                r12 = r14;
                r13 = r15;
            }
        }
        com.google.android.gms.vision.CameraSource.zze r2 = (com.google.android.gms.vision.CameraSource.zze) com.google.android.gms.common.internal.Preconditions.checkNotNull(r12);
        if (r2 == null) {
            throw new java.io.IOException("Could not find suitable preview size.");
        }
        com.google.android.gms.common.images.Size r5 = r2.zzb();
        r17.zzf = r2.zza();
        int r2 = (int) (r17.zzg * 1000.0f);
        int[] r8 = null;
        for (int[] r11 : r1.getParameters().getSupportedPreviewFpsRange()) {
            int r13 = java.lang.Math.abs(r2 - r11[0]) + java.lang.Math.abs(r2 - r11[1]);
            if (r13 < r7) {
                r8 = r11;
                r7 = r13;
            }
        }
        int[] r2 = (int[]) com.google.android.gms.common.internal.Preconditions.checkNotNull(r8);
        if (r2 == null) {
            throw new java.io.IOException("Could not find suitable preview frames per second range.");
        }
        android.hardware.Camera.Parameters r6 = r1.getParameters();
        if (r5 != null) {
            r6.setPictureSize(r5.getWidth(), r5.getHeight());
        }
        r6.setPreviewSize(r17.zzf.getWidth(), r17.zzf.getHeight());
        r6.setPreviewFpsRange(r2[0], r2[1]);
        r6.setPreviewFormat(17);
        int r2 = ((android.view.WindowManager) com.google.android.gms.common.internal.Preconditions.checkNotNull((android.view.WindowManager) r17.zza.getSystemService("window"))).getDefaultDisplay().getRotation();
        if (r2 != 0) {
            if (r2 == 1) {
                r2 = 90;
            } else if (r2 == 2) {
                r2 = 180;
            } else if (r2 != 3) {
                android.util.Log.e("CameraSource", new java.lang.StringBuilder(31).append("Bad rotation value: ").append(r2).toString());
            } else {
                r2 = 270;
            }
            r5 = new android.hardware.Camera.CameraInfo();
            android.hardware.Camera.getCameraInfo(r4, r5);
            if (r5.facing != 1) {
                r4 = (r5.orientation + r2) % 360;
                r2 = (360 - r4) % 360;
            } else {
                r4 = ((r5.orientation - r2) + 360) % 360;
                r2 = r4;
            }
            r17.zze = r4 / 90;
            r1.setDisplayOrientation(r2);
            r6.setRotation(r4);
            if (r17.zzk != null) {
                if (r6.getSupportedFocusModes().contains(r17.zzk)) {
                    r6.setFocusMode((java.lang.String) com.google.android.gms.common.internal.Preconditions.checkNotNull(r17.zzk));
                } else {
                    android.util.Log.w("CameraSource", java.lang.String.format("FocusMode %s is not supported on this device.", r17.zzk));
                    r17.zzk = null;
                }
            }
            if (r17.zzk == null && r17.zzj) {
                if (!r6.getSupportedFocusModes().contains("continuous-video")) {
                    r6.setFocusMode("continuous-video");
                    r17.zzk = "continuous-video";
                } else {
                    android.util.Log.i("CameraSource", "Camera auto focus is not supported on this device.");
                }
            }
            r1.setParameters(r6);
            r1.setPreviewCallbackWithBuffer(new com.google.android.gms.vision.CameraSource.zzb());
            r1.addCallbackBuffer(zza(r17.zzf));
            r1.addCallbackBuffer(zza(r17.zzf));
            r1.addCallbackBuffer(zza(r17.zzf));
            r1.addCallbackBuffer(zza(r17.zzf));
            return r1;
        }
        r2 = 0;
        r5 = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(r4, r5);
        if (r5.facing != 1) {
        }
        r17.zze = r4 / 90;
        r1.setDisplayOrientation(r2);
        r6.setRotation(r4);
        if (r17.zzk != null) {
        }
        if (r17.zzk == null) {
            if (!r6.getSupportedFocusModes().contains("continuous-video")) {
            }
        }
        r1.setParameters(r6);
        r1.setPreviewCallbackWithBuffer(new com.google.android.gms.vision.CameraSource.zzb());
        r1.addCallbackBuffer(zza(r17.zzf));
        r1.addCallbackBuffer(zza(r17.zzf));
        r1.addCallbackBuffer(zza(r17.zzf));
        r1.addCallbackBuffer(zza(r17.zzf));
        return r1;
    }

    private final byte[] zza(Size size) {
        byte[] bArr = new byte[((int) Math.ceil(((size.getHeight() * size.getWidth()) * ImageFormat.getBitsPerPixel(17)) / 8.0d)) + 1];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (!wrap.hasArray() || wrap.array() != bArr) {
            throw new IllegalStateException("Failed to create valid buffer for camera source.");
        }
        this.zzo.put(bArr, wrap);
        return bArr;
    }
}
