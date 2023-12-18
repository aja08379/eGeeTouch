package pl.droidsonroids.relinker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import pl.droidsonroids.relinker.ReLinker;
/* loaded from: classes2.dex */
public class ApkLibraryInstaller implements ReLinker.LibraryInstaller {
    private static final int COPY_BUFFER_SIZE = 4096;
    private static final int MAX_TRIES = 5;

    private String[] sourceDirectories(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || applicationInfo.splitSourceDirs == null || applicationInfo.splitSourceDirs.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr = new String[applicationInfo.splitSourceDirs.length + 1];
        strArr[0] = applicationInfo.sourceDir;
        System.arraycopy(applicationInfo.splitSourceDirs, 0, strArr, 1, applicationInfo.splitSourceDirs.length);
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ZipFileInZipEntry {
        public ZipEntry zipEntry;
        public ZipFile zipFile;

        public ZipFileInZipEntry(ZipFile zipFile, ZipEntry zipEntry) {
            this.zipFile = zipFile;
            this.zipEntry = zipEntry;
        }
    }

    private ZipFileInZipEntry findAPKWithLibrary(Context context, String[] strArr, String str, ReLinkerInstance reLinkerInstance) {
        String[] sourceDirectories = sourceDirectories(context);
        int length = sourceDirectories.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            ZipFile zipFile = null;
            if (i2 >= length) {
                return null;
            }
            String str2 = sourceDirectories[i2];
            int i3 = i;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str2), 1);
                    break;
                } catch (IOException unused) {
                    i3 = i4;
                }
            }
            if (zipFile != null) {
                int i5 = i;
                while (true) {
                    int i6 = i5 + 1;
                    if (i5 < 5) {
                        int length2 = strArr.length;
                        int i7 = i;
                        while (i7 < length2) {
                            String str3 = "lib" + File.separatorChar + strArr[i7] + File.separatorChar + str;
                            Object[] objArr = new Object[2];
                            objArr[i] = str3;
                            objArr[1] = str2;
                            reLinkerInstance.log("Looking for %s in APK %s...", objArr);
                            ZipEntry entry = zipFile.getEntry(str3);
                            if (entry != null) {
                                return new ZipFileInZipEntry(zipFile, entry);
                            }
                            i7++;
                            i = 0;
                        }
                        i5 = i6;
                        i = 0;
                    } else {
                        try {
                            zipFile.close();
                            break;
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
            i2++;
            i = 0;
        }
    }

    private String[] getSupportedABIs(Context context, String str) {
        Pattern compile = Pattern.compile("lib" + File.separatorChar + "([^\\" + File.separatorChar + "]*)" + File.separatorChar + str);
        HashSet hashSet = new HashSet();
        for (String str2 : sourceDirectories(context)) {
            try {
                Enumeration<? extends ZipEntry> entries = new ZipFile(new File(str2), 1).entries();
                while (entries.hasMoreElements()) {
                    Matcher matcher = compile.matcher(entries.nextElement().getName());
                    if (matcher.matches()) {
                        hashSet.add(matcher.group(1));
                    }
                }
            } catch (IOException unused) {
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    @Override // pl.droidsonroids.relinker.ReLinker.LibraryInstaller
    public void installLibrary(Context context, String[] strArr, String str, File file, ReLinkerInstance reLinkerInstance) {
        ZipFileInZipEntry findAPKWithLibrary;
        String[] strArr2;
        Closeable closeable;
        InputStream inputStream;
        long copy;
        ZipFileInZipEntry zipFileInZipEntry = null;
        Closeable closeable2 = null;
        try {
            findAPKWithLibrary = findAPKWithLibrary(context, strArr, str, reLinkerInstance);
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (findAPKWithLibrary == null) {
                try {
                    strArr2 = getSupportedABIs(context, str);
                } catch (Exception e) {
                    strArr2 = new String[]{e.toString()};
                }
                throw new MissingLibraryException(str, strArr, strArr2);
            }
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (i >= 5) {
                    reLinkerInstance.log("FATAL! Couldn't extract the library from the APK!");
                    if (findAPKWithLibrary != null) {
                        try {
                            if (findAPKWithLibrary.zipFile != null) {
                                findAPKWithLibrary.zipFile.close();
                                return;
                            }
                            return;
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    return;
                }
                reLinkerInstance.log("Found %s! Extracting...", str);
                try {
                    if (file.exists() || file.createNewFile()) {
                        try {
                            inputStream = findAPKWithLibrary.zipFile.getInputStream(findAPKWithLibrary.zipEntry);
                            try {
                                closeable = new FileOutputStream(file);
                            } catch (FileNotFoundException unused2) {
                                closeable = null;
                            } catch (IOException unused3) {
                                closeable = null;
                            } catch (Throwable th2) {
                                th = th2;
                                closeable = null;
                            }
                            try {
                                copy = copy(inputStream, closeable);
                                closeable.getFD().sync();
                            } catch (FileNotFoundException unused4) {
                                closeSilently(inputStream);
                                closeSilently(closeable);
                                i = i2;
                            } catch (IOException unused5) {
                                closeSilently(inputStream);
                                closeSilently(closeable);
                                i = i2;
                            } catch (Throwable th3) {
                                th = th3;
                                closeable2 = inputStream;
                                closeSilently(closeable2);
                                closeSilently(closeable);
                                throw th;
                            }
                        } catch (FileNotFoundException unused6) {
                            inputStream = null;
                            closeable = null;
                        } catch (IOException unused7) {
                            inputStream = null;
                            closeable = null;
                        } catch (Throwable th4) {
                            th = th4;
                            closeable = null;
                        }
                        if (copy == file.length()) {
                            closeSilently(inputStream);
                            closeSilently(closeable);
                            file.setReadable(true, false);
                            file.setExecutable(true, false);
                            file.setWritable(true);
                            if (findAPKWithLibrary != null) {
                                try {
                                    if (findAPKWithLibrary.zipFile != null) {
                                        findAPKWithLibrary.zipFile.close();
                                        return;
                                    }
                                    return;
                                } catch (IOException unused8) {
                                    return;
                                }
                            }
                            return;
                        }
                        closeSilently(inputStream);
                        closeSilently(closeable);
                    }
                } catch (IOException unused9) {
                }
                i = i2;
            }
        } catch (Throwable th5) {
            th = th5;
            zipFileInZipEntry = findAPKWithLibrary;
            if (zipFileInZipEntry != null) {
                try {
                    if (zipFileInZipEntry.zipFile != null) {
                        zipFileInZipEntry.zipFile.close();
                    }
                } catch (IOException unused10) {
                }
            }
            throw th;
        }
    }

    private long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                j += read;
            } else {
                outputStream.flush();
                return j;
            }
        }
    }

    private void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
