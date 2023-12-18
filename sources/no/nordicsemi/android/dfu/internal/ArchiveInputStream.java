package no.nordicsemi.android.dfu.internal;

import android.os.Build;
import android.util.Log;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.UByte;
import no.nordicsemi.android.dfu.internal.manifest.Manifest;
import no.nordicsemi.android.dfu.internal.manifest.ManifestFile;
/* loaded from: classes2.dex */
public class ArchiveInputStream extends InputStream {
    private static final String APPLICATION_BIN = "application.bin";
    private static final String APPLICATION_HEX = "application.hex";
    private static final String APPLICATION_INIT = "application.dat";
    private static final String BOOTLOADER_BIN = "bootloader.bin";
    private static final String BOOTLOADER_HEX = "bootloader.hex";
    private static final String MANIFEST = "manifest.json";
    private static final String SOFTDEVICE_BIN = "softdevice.bin";
    private static final String SOFTDEVICE_HEX = "softdevice.hex";
    private static final String SYSTEM_INIT = "system.dat";
    private static final String TAG = "DfuArchiveInputStream";
    private byte[] applicationBytes;
    private byte[] applicationInitBytes;
    private int applicationSize;
    private byte[] bootloaderBytes;
    private int bootloaderSize;
    private int bytesRead;
    private int bytesReadFromCurrentSource;
    private int bytesReadFromMarkedSource;
    private final CRC32 crc32;
    private byte[] currentSource;
    private final Map<String, byte[]> entries;
    private Manifest manifest;
    private byte[] markedSource;
    private byte[] softDeviceAndBootloaderBytes;
    private byte[] softDeviceBytes;
    private int softDeviceSize;
    private byte[] systemInitBytes;
    private int type;
    private final ZipInputStream zipInputStream;

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x0209 A[Catch: all -> 0x026e, TryCatch #0 {all -> 0x026e, blocks: (B:5:0x0025, B:7:0x002d, B:13:0x003b, B:15:0x005d, B:19:0x0086, B:22:0x0090, B:24:0x0094, B:26:0x0098, B:28:0x00ba, B:29:0x00c1, B:30:0x00e1, B:31:0x00e2, B:32:0x00e9, B:33:0x00ea, B:36:0x00f4, B:38:0x00f8, B:40:0x011a, B:41:0x0121, B:42:0x0141, B:43:0x0142, B:46:0x014c, B:48:0x0150, B:50:0x0154, B:52:0x0158, B:54:0x017a, B:96:0x0259, B:62:0x01b9, B:63:0x01c0, B:55:0x018b, B:56:0x01ab, B:57:0x01ac, B:58:0x01b3, B:16:0x0064, B:17:0x0084, B:65:0x01c3, B:86:0x0229, B:99:0x0266, B:100:0x026d, B:88:0x022c, B:90:0x0238, B:91:0x0242, B:93:0x0246, B:79:0x01fd, B:81:0x0209, B:82:0x0213, B:84:0x0217, B:67:0x01c7, B:69:0x01d3, B:70:0x01dd, B:72:0x01e1), top: B:106:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0217 A[Catch: all -> 0x026e, TryCatch #0 {all -> 0x026e, blocks: (B:5:0x0025, B:7:0x002d, B:13:0x003b, B:15:0x005d, B:19:0x0086, B:22:0x0090, B:24:0x0094, B:26:0x0098, B:28:0x00ba, B:29:0x00c1, B:30:0x00e1, B:31:0x00e2, B:32:0x00e9, B:33:0x00ea, B:36:0x00f4, B:38:0x00f8, B:40:0x011a, B:41:0x0121, B:42:0x0141, B:43:0x0142, B:46:0x014c, B:48:0x0150, B:50:0x0154, B:52:0x0158, B:54:0x017a, B:96:0x0259, B:62:0x01b9, B:63:0x01c0, B:55:0x018b, B:56:0x01ab, B:57:0x01ac, B:58:0x01b3, B:16:0x0064, B:17:0x0084, B:65:0x01c3, B:86:0x0229, B:99:0x0266, B:100:0x026d, B:88:0x022c, B:90:0x0238, B:91:0x0242, B:93:0x0246, B:79:0x01fd, B:81:0x0209, B:82:0x0213, B:84:0x0217, B:67:0x01c7, B:69:0x01d3, B:70:0x01dd, B:72:0x01e1), top: B:106:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0238 A[Catch: all -> 0x026e, TryCatch #0 {all -> 0x026e, blocks: (B:5:0x0025, B:7:0x002d, B:13:0x003b, B:15:0x005d, B:19:0x0086, B:22:0x0090, B:24:0x0094, B:26:0x0098, B:28:0x00ba, B:29:0x00c1, B:30:0x00e1, B:31:0x00e2, B:32:0x00e9, B:33:0x00ea, B:36:0x00f4, B:38:0x00f8, B:40:0x011a, B:41:0x0121, B:42:0x0141, B:43:0x0142, B:46:0x014c, B:48:0x0150, B:50:0x0154, B:52:0x0158, B:54:0x017a, B:96:0x0259, B:62:0x01b9, B:63:0x01c0, B:55:0x018b, B:56:0x01ab, B:57:0x01ac, B:58:0x01b3, B:16:0x0064, B:17:0x0084, B:65:0x01c3, B:86:0x0229, B:99:0x0266, B:100:0x026d, B:88:0x022c, B:90:0x0238, B:91:0x0242, B:93:0x0246, B:79:0x01fd, B:81:0x0209, B:82:0x0213, B:84:0x0217, B:67:0x01c7, B:69:0x01d3, B:70:0x01dd, B:72:0x01e1), top: B:106:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0246 A[Catch: all -> 0x026e, TryCatch #0 {all -> 0x026e, blocks: (B:5:0x0025, B:7:0x002d, B:13:0x003b, B:15:0x005d, B:19:0x0086, B:22:0x0090, B:24:0x0094, B:26:0x0098, B:28:0x00ba, B:29:0x00c1, B:30:0x00e1, B:31:0x00e2, B:32:0x00e9, B:33:0x00ea, B:36:0x00f4, B:38:0x00f8, B:40:0x011a, B:41:0x0121, B:42:0x0141, B:43:0x0142, B:46:0x014c, B:48:0x0150, B:50:0x0154, B:52:0x0158, B:54:0x017a, B:96:0x0259, B:62:0x01b9, B:63:0x01c0, B:55:0x018b, B:56:0x01ab, B:57:0x01ac, B:58:0x01b3, B:16:0x0064, B:17:0x0084, B:65:0x01c3, B:86:0x0229, B:99:0x0266, B:100:0x026d, B:88:0x022c, B:90:0x0238, B:91:0x0242, B:93:0x0246, B:79:0x01fd, B:81:0x0209, B:82:0x0213, B:84:0x0217, B:67:0x01c7, B:69:0x01d3, B:70:0x01dd, B:72:0x01e1), top: B:106:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0266 A[Catch: all -> 0x026e, TRY_ENTER, TryCatch #0 {all -> 0x026e, blocks: (B:5:0x0025, B:7:0x002d, B:13:0x003b, B:15:0x005d, B:19:0x0086, B:22:0x0090, B:24:0x0094, B:26:0x0098, B:28:0x00ba, B:29:0x00c1, B:30:0x00e1, B:31:0x00e2, B:32:0x00e9, B:33:0x00ea, B:36:0x00f4, B:38:0x00f8, B:40:0x011a, B:41:0x0121, B:42:0x0141, B:43:0x0142, B:46:0x014c, B:48:0x0150, B:50:0x0154, B:52:0x0158, B:54:0x017a, B:96:0x0259, B:62:0x01b9, B:63:0x01c0, B:55:0x018b, B:56:0x01ab, B:57:0x01ac, B:58:0x01b3, B:16:0x0064, B:17:0x0084, B:65:0x01c3, B:86:0x0229, B:99:0x0266, B:100:0x026d, B:88:0x022c, B:90:0x0238, B:91:0x0242, B:93:0x0246, B:79:0x01fd, B:81:0x0209, B:82:0x0213, B:84:0x0217, B:67:0x01c7, B:69:0x01d3, B:70:0x01dd, B:72:0x01e1), top: B:106:0x0025 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ArchiveInputStream(java.io.InputStream r6, int r7, int r8) throws java.io.IOException {
        boolean r7;
        byte[] r4;
        byte[] r4;
        byte[] r8;
        byte[] r8;
        boolean r7;
        if (r6.available() > 10485760) {
            throw new java.io.IOException("File too large: " + r6.available() + " bytes (max 10 MB)");
        }
        java.util.zip.ZipInputStream r0 = new java.util.zip.ZipInputStream(r6);
        r5.zipInputStream = r0;
        r5.crc32 = new java.util.zip.CRC32();
        java.util.HashMap r6 = new java.util.HashMap();
        r5.entries = r6;
        r5.bytesRead = 0;
        r5.bytesReadFromCurrentSource = 0;
        try {
            parseZip(r7);
            no.nordicsemi.android.dfu.internal.manifest.Manifest r7 = r5.manifest;
            boolean r2 = true;
            if (r7 != null) {
                if (r7.getApplicationInfo() == null || (r8 != 0 && (r8 & 4) <= 0)) {
                    r7 = false;
                } else {
                    no.nordicsemi.android.dfu.internal.manifest.FileInfo r7 = r5.manifest.getApplicationInfo();
                    r5.applicationBytes = (byte[]) r6.get(r7.getBinFileName());
                    r5.applicationInitBytes = (byte[]) r6.get(r7.getDatFileName());
                    byte[] r4 = r5.applicationBytes;
                    if (r4 == null) {
                        throw new java.io.IOException("Application file " + r7.getBinFileName() + " not found.");
                    }
                    r5.applicationSize = r4.length;
                    r5.currentSource = r4;
                    r7 = true;
                }
                if (r5.manifest.getBootloaderInfo() != null && (r8 == 0 || (r8 & 2) > 0)) {
                    if (r5.systemInitBytes != null) {
                        throw new java.io.IOException("Manifest: softdevice and bootloader specified. Use softdevice_bootloader instead.");
                    }
                    no.nordicsemi.android.dfu.internal.manifest.FileInfo r7 = r5.manifest.getBootloaderInfo();
                    r5.bootloaderBytes = (byte[]) r6.get(r7.getBinFileName());
                    r5.systemInitBytes = (byte[]) r6.get(r7.getDatFileName());
                    byte[] r4 = r5.bootloaderBytes;
                    if (r4 == null) {
                        throw new java.io.IOException("Bootloader file " + r7.getBinFileName() + " not found.");
                    }
                    r5.bootloaderSize = r4.length;
                    r5.currentSource = r4;
                    r7 = true;
                }
                if (r5.manifest.getSoftdeviceInfo() != null && (r8 == 0 || (r8 & 1) > 0)) {
                    no.nordicsemi.android.dfu.internal.manifest.FileInfo r7 = r5.manifest.getSoftdeviceInfo();
                    r5.softDeviceBytes = (byte[]) r6.get(r7.getBinFileName());
                    r5.systemInitBytes = (byte[]) r6.get(r7.getDatFileName());
                    byte[] r4 = r5.softDeviceBytes;
                    if (r4 == null) {
                        throw new java.io.IOException("SoftDevice file " + r7.getBinFileName() + " not found.");
                    }
                    r5.softDeviceSize = r4.length;
                    r5.currentSource = r4;
                    r7 = true;
                }
                if (r5.manifest.getSoftdeviceBootloaderInfo() == null || (r8 != 0 && ((r8 & 1) <= 0 || (r8 & 2) <= 0))) {
                    r2 = r7;
                } else if (r5.systemInitBytes != null) {
                    throw new java.io.IOException("Manifest: The softdevice_bootloader may not be used together with softdevice or bootloader.");
                } else {
                    no.nordicsemi.android.dfu.internal.manifest.SoftDeviceBootloaderFileInfo r7 = r5.manifest.getSoftdeviceBootloaderInfo();
                    r5.softDeviceAndBootloaderBytes = (byte[]) r6.get(r7.getBinFileName());
                    r5.systemInitBytes = (byte[]) r6.get(r7.getDatFileName());
                    if (r5.softDeviceAndBootloaderBytes == null) {
                        throw new java.io.IOException("File " + r7.getBinFileName() + " not found.");
                    }
                    r5.softDeviceSize = r7.getSoftdeviceSize();
                    r5.bootloaderSize = r7.getBootloaderSize();
                    r5.currentSource = r5.softDeviceAndBootloaderBytes;
                }
                if (!r2) {
                    throw new java.io.IOException("Manifest file must specify at least one file.");
                }
            } else {
                if (r8 == 0 || (r8 & 4) > 0) {
                    byte[] r7 = (byte[]) r6.get(no.nordicsemi.android.dfu.internal.ArchiveInputStream.APPLICATION_HEX);
                    r5.applicationBytes = r7;
                    if (r7 == null) {
                        r5.applicationBytes = (byte[]) r6.get(no.nordicsemi.android.dfu.internal.ArchiveInputStream.APPLICATION_BIN);
                    }
                    byte[] r7 = r5.applicationBytes;
                    if (r7 != null) {
                        r5.applicationSize = r7.length;
                        r5.applicationInitBytes = (byte[]) r6.get(no.nordicsemi.android.dfu.internal.ArchiveInputStream.APPLICATION_INIT);
                        r5.currentSource = r5.applicationBytes;
                        r7 = true;
                        if (r8 != 0 || (r8 & 2) > 0) {
                            r4 = (byte[]) r6.get(no.nordicsemi.android.dfu.internal.ArchiveInputStream.BOOTLOADER_HEX);
                            r5.bootloaderBytes = r4;
                            if (r4 == null) {
                                r5.bootloaderBytes = (byte[]) r6.get(no.nordicsemi.android.dfu.internal.ArchiveInputStream.BOOTLOADER_BIN);
                            }
                            r4 = r5.bootloaderBytes;
                            if (r4 != null) {
                                r5.bootloaderSize = r4.length;
                                r5.systemInitBytes = (byte[]) r6.get(no.nordicsemi.android.dfu.internal.ArchiveInputStream.SYSTEM_INIT);
                                r5.currentSource = r5.bootloaderBytes;
                                r7 = true;
                            }
                        }
                        if (r8 != 0 || (r8 & 1) > 0) {
                            r8 = (byte[]) r6.get(no.nordicsemi.android.dfu.internal.ArchiveInputStream.SOFTDEVICE_HEX);
                            r5.softDeviceBytes = r8;
                            if (r8 == null) {
                                r5.softDeviceBytes = (byte[]) r6.get(no.nordicsemi.android.dfu.internal.ArchiveInputStream.SOFTDEVICE_BIN);
                            }
                            r8 = r5.softDeviceBytes;
                            if (r8 != null) {
                                r5.softDeviceSize = r8.length;
                                r5.systemInitBytes = (byte[]) r6.get(no.nordicsemi.android.dfu.internal.ArchiveInputStream.SYSTEM_INIT);
                                r5.currentSource = r5.softDeviceBytes;
                                if (!r2) {
                                    throw new java.io.IOException("The ZIP file must contain an Application, a Soft Device and/or a Bootloader.");
                                }
                            }
                        }
                        r2 = r7;
                        if (!r2) {
                        }
                    }
                }
                r7 = false;
                if (r8 != 0) {
                }
                r4 = (byte[]) r6.get(no.nordicsemi.android.dfu.internal.ArchiveInputStream.BOOTLOADER_HEX);
                r5.bootloaderBytes = r4;
                if (r4 == null) {
                }
                r4 = r5.bootloaderBytes;
                if (r4 != null) {
                }
                if (r8 != 0) {
                }
                r8 = (byte[]) r6.get(no.nordicsemi.android.dfu.internal.ArchiveInputStream.SOFTDEVICE_HEX);
                r5.softDeviceBytes = r8;
                if (r8 == null) {
                }
                r8 = r5.softDeviceBytes;
                if (r8 != null) {
                }
                r2 = r7;
                if (!r2) {
                }
            }
            mark(0);
            r5.type = getContentType();
            r0.close();
        } catch (java.lang.Throwable r6) {
            r5.type = getContentType();
            r5.zipInputStream.close();
            throw r6;
        }
    }

    private String validateFilename(String str, String str2) throws IOException {
        String canonicalPath = new File(str).getCanonicalPath();
        if (canonicalPath.startsWith(new File(str2).getCanonicalPath())) {
            return canonicalPath.substring(1);
        }
        throw new IllegalStateException("File is outside extraction target directory.");
    }

    private void parseZip(int i) throws IOException {
        byte[] bArr = new byte[1024];
        String str = null;
        while (true) {
            ZipEntry nextEntry = this.zipInputStream.getNextEntry();
            if (nextEntry == null) {
                break;
            }
            String validateFilename = validateFilename(nextEntry.getName(), ".");
            if (nextEntry.isDirectory()) {
                Log.w(TAG, "A directory found in the ZIP: " + validateFilename + "!");
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = this.zipInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (validateFilename.toLowerCase(Locale.US).endsWith("hex")) {
                    HexInputStream hexInputStream = new HexInputStream(byteArray, i);
                    byteArray = new byte[hexInputStream.available()];
                    hexInputStream.read(byteArray);
                    hexInputStream.close();
                }
                if (MANIFEST.equals(validateFilename)) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        str = new String(byteArray, StandardCharsets.UTF_8);
                    } else {
                        str = new String(byteArray, "UTF-8");
                    }
                } else {
                    this.entries.put(validateFilename, byteArray);
                }
            }
        }
        if (this.entries.isEmpty()) {
            throw new FileNotFoundException("No files found in the ZIP. Check if the URI provided is valid and the ZIP contains required files on root level, not in a directory.");
        }
        if (str != null) {
            Manifest manifest = ((ManifestFile) new Gson().fromJson(str, (Class<Object>) ManifestFile.class)).getManifest();
            this.manifest = manifest;
            if (manifest == null) {
                Log.w(TAG, "Manifest failed to be parsed. Did you add \n-keep class no.nordicsemi.android.dfu.** { *; }\nto your proguard rules?");
                return;
            }
            return;
        }
        Log.w(TAG, "Manifest not found in the ZIP. It is recommended to use a distribution file created with: https://github.com/NordicSemiconductor/pc-nrfutil/ (for Legacy DFU use version 0.5.x)");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.softDeviceBytes = null;
        this.bootloaderBytes = null;
        this.applicationBytes = null;
        this.softDeviceAndBootloaderBytes = null;
        this.applicationSize = 0;
        this.bootloaderSize = 0;
        this.softDeviceSize = 0;
        this.currentSource = null;
        this.bytesReadFromCurrentSource = 0;
        this.bytesRead = 0;
        this.zipInputStream.close();
    }

    @Override // java.io.InputStream
    public int read() {
        byte[] bArr = new byte[1];
        if (read(bArr) == -1) {
            return -1;
        }
        return bArr[0] & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int rawRead = rawRead(bArr, i, i2);
        return (i2 <= rawRead || startNextFile() == null) ? rawRead : rawRead + rawRead(bArr, i + rawRead, i2 - rawRead);
    }

    private int rawRead(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, this.currentSource.length - this.bytesReadFromCurrentSource);
        System.arraycopy(this.currentSource, this.bytesReadFromCurrentSource, bArr, i, min);
        this.bytesReadFromCurrentSource += min;
        this.bytesRead += min;
        this.crc32.update(bArr, i, min);
        return min;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.markedSource = this.currentSource;
        this.bytesReadFromMarkedSource = this.bytesReadFromCurrentSource;
    }

    @Override // java.io.InputStream
    public void reset() {
        byte[] bArr;
        this.currentSource = this.markedSource;
        int i = this.bytesReadFromMarkedSource;
        this.bytesReadFromCurrentSource = i;
        this.bytesRead = i;
        this.crc32.reset();
        if (this.currentSource == this.bootloaderBytes && (bArr = this.softDeviceBytes) != null) {
            this.crc32.update(bArr);
            this.bytesRead += this.softDeviceSize;
        }
        this.crc32.update(this.currentSource, 0, this.bytesReadFromCurrentSource);
    }

    public void fullReset() {
        byte[] bArr;
        byte[] bArr2 = this.softDeviceBytes;
        if (bArr2 != null && (bArr = this.bootloaderBytes) != null && this.currentSource == bArr) {
            this.currentSource = bArr2;
        }
        this.bytesReadFromCurrentSource = 0;
        mark(0);
        reset();
    }

    public int getBytesRead() {
        return this.bytesRead;
    }

    public long getCrc32() {
        return this.crc32.getValue();
    }

    public int getContentType() {
        this.type = 0;
        if (this.softDeviceAndBootloaderBytes != null) {
            this.type = 0 | 3;
        }
        if (this.softDeviceSize > 0) {
            this.type |= 1;
        }
        if (this.bootloaderSize > 0) {
            this.type |= 2;
        }
        if (this.applicationSize > 0) {
            this.type |= 4;
        }
        return this.type;
    }

    public int setContentType(int i) {
        byte[] bArr;
        this.type = i;
        int i2 = i & 4;
        if (i2 > 0 && this.applicationBytes == null) {
            this.type = i & (-5);
        }
        int i3 = i & 3;
        if (i3 == 3) {
            if (this.softDeviceBytes == null && this.softDeviceAndBootloaderBytes == null) {
                this.type &= -2;
            }
            if (this.bootloaderBytes == null && this.softDeviceAndBootloaderBytes == null) {
                this.type &= -2;
            }
        } else if (this.softDeviceAndBootloaderBytes != null) {
            this.type &= -4;
        }
        if (i3 > 0 && (bArr = this.softDeviceAndBootloaderBytes) != null) {
            this.currentSource = bArr;
        } else if ((i & 1) > 0) {
            this.currentSource = this.softDeviceBytes;
        } else if ((i & 2) > 0) {
            this.currentSource = this.bootloaderBytes;
        } else if (i2 > 0) {
            this.currentSource = this.applicationBytes;
        }
        this.bytesReadFromCurrentSource = 0;
        mark(0);
        reset();
        return this.type;
    }

    private byte[] startNextFile() {
        byte[] bArr;
        byte[] bArr2 = this.currentSource;
        if (bArr2 == this.softDeviceBytes && (bArr = this.bootloaderBytes) != null && (this.type & 2) > 0) {
            this.currentSource = bArr;
        } else {
            bArr = this.applicationBytes;
            if (bArr2 != bArr && bArr != null && (this.type & 4) > 0) {
                this.currentSource = bArr;
            } else {
                bArr = null;
                this.currentSource = null;
            }
        }
        this.bytesReadFromCurrentSource = 0;
        return bArr;
    }

    @Override // java.io.InputStream
    public int available() {
        int softDeviceImageSize;
        int i;
        byte[] bArr = this.softDeviceAndBootloaderBytes;
        if (bArr != null && this.softDeviceSize == 0 && this.bootloaderSize == 0 && (this.type & 3) > 0) {
            softDeviceImageSize = bArr.length + applicationImageSize();
            i = this.bytesRead;
        } else {
            softDeviceImageSize = softDeviceImageSize() + bootloaderImageSize() + applicationImageSize();
            i = this.bytesRead;
        }
        return softDeviceImageSize - i;
    }

    public int softDeviceImageSize() {
        if ((this.type & 1) > 0) {
            return this.softDeviceSize;
        }
        return 0;
    }

    public int bootloaderImageSize() {
        if ((this.type & 2) > 0) {
            return this.bootloaderSize;
        }
        return 0;
    }

    public int applicationImageSize() {
        if ((this.type & 4) > 0) {
            return this.applicationSize;
        }
        return 0;
    }

    public byte[] getSystemInit() {
        return this.systemInitBytes;
    }

    public byte[] getApplicationInit() {
        return this.applicationInitBytes;
    }

    public boolean isSecureDfuRequired() {
        Manifest manifest = this.manifest;
        return manifest != null && manifest.isSecureDfuRequired();
    }
}
