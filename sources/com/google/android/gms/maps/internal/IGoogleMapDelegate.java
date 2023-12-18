package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import javax.annotation.Nullable;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public interface IGoogleMapDelegate extends IInterface {
    com.google.android.gms.internal.maps.zzl addCircle(CircleOptions circleOptions) throws RemoteException;

    com.google.android.gms.internal.maps.zzo addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException;

    com.google.android.gms.internal.maps.zzaa addMarker(MarkerOptions markerOptions) throws RemoteException;

    void addOnMapCapabilitiesChangedListener(zzak zzakVar) throws RemoteException;

    com.google.android.gms.internal.maps.zzad addPolygon(PolygonOptions polygonOptions) throws RemoteException;

    com.google.android.gms.internal.maps.zzag addPolyline(PolylineOptions polylineOptions) throws RemoteException;

    com.google.android.gms.internal.maps.zzaj addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException;

    void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException;

    void animateCameraWithCallback(IObjectWrapper iObjectWrapper, @Nullable zzd zzdVar) throws RemoteException;

    void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, @Nullable zzd zzdVar) throws RemoteException;

    void clear() throws RemoteException;

    CameraPosition getCameraPosition() throws RemoteException;

    com.google.android.gms.internal.maps.zzr getFocusedBuilding() throws RemoteException;

    void getMapAsync(zzas zzasVar) throws RemoteException;

    com.google.android.gms.internal.maps.zzx getMapCapabilities() throws RemoteException;

    int getMapType() throws RemoteException;

    float getMaxZoomLevel() throws RemoteException;

    float getMinZoomLevel() throws RemoteException;

    Location getMyLocation() throws RemoteException;

    IProjectionDelegate getProjection() throws RemoteException;

    IUiSettingsDelegate getUiSettings() throws RemoteException;

    boolean isBuildingsEnabled() throws RemoteException;

    boolean isIndoorEnabled() throws RemoteException;

    boolean isMyLocationEnabled() throws RemoteException;

    boolean isTrafficEnabled() throws RemoteException;

    void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException;

    void onCreate(Bundle bundle) throws RemoteException;

    void onDestroy() throws RemoteException;

    void onEnterAmbient(Bundle bundle) throws RemoteException;

    void onExitAmbient() throws RemoteException;

    void onLowMemory() throws RemoteException;

    void onPause() throws RemoteException;

    void onResume() throws RemoteException;

    void onSaveInstanceState(Bundle bundle) throws RemoteException;

    void onStart() throws RemoteException;

    void onStop() throws RemoteException;

    void removeOnMapCapabilitiesChangedListener(zzak zzakVar) throws RemoteException;

    void resetMinMaxZoomPreference() throws RemoteException;

    void setBuildingsEnabled(boolean z) throws RemoteException;

    void setContentDescription(@Nullable String str) throws RemoteException;

    boolean setIndoorEnabled(boolean z) throws RemoteException;

    void setInfoWindowAdapter(@Nullable zzi zziVar) throws RemoteException;

    void setLatLngBoundsForCameraTarget(@Nullable LatLngBounds latLngBounds) throws RemoteException;

    void setLocationSource(@Nullable ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException;

    boolean setMapStyle(@Nullable MapStyleOptions mapStyleOptions) throws RemoteException;

    void setMapType(int i) throws RemoteException;

    void setMaxZoomPreference(float f) throws RemoteException;

    void setMinZoomPreference(float f) throws RemoteException;

    void setMyLocationEnabled(boolean z) throws RemoteException;

    void setOnCameraChangeListener(@Nullable zzn zznVar) throws RemoteException;

    void setOnCameraIdleListener(@Nullable zzp zzpVar) throws RemoteException;

    void setOnCameraMoveCanceledListener(@Nullable zzr zzrVar) throws RemoteException;

    void setOnCameraMoveListener(@Nullable zzt zztVar) throws RemoteException;

    void setOnCameraMoveStartedListener(@Nullable zzv zzvVar) throws RemoteException;

    void setOnCircleClickListener(@Nullable zzx zzxVar) throws RemoteException;

    void setOnGroundOverlayClickListener(@Nullable zzz zzzVar) throws RemoteException;

    void setOnIndoorStateChangeListener(@Nullable zzab zzabVar) throws RemoteException;

    void setOnInfoWindowClickListener(@Nullable zzad zzadVar) throws RemoteException;

    void setOnInfoWindowCloseListener(@Nullable zzaf zzafVar) throws RemoteException;

    void setOnInfoWindowLongClickListener(@Nullable zzah zzahVar) throws RemoteException;

    void setOnMapClickListener(@Nullable zzam zzamVar) throws RemoteException;

    void setOnMapLoadedCallback(@Nullable zzao zzaoVar) throws RemoteException;

    void setOnMapLongClickListener(@Nullable zzaq zzaqVar) throws RemoteException;

    void setOnMarkerClickListener(@Nullable zzau zzauVar) throws RemoteException;

    void setOnMarkerDragListener(@Nullable zzaw zzawVar) throws RemoteException;

    void setOnMyLocationButtonClickListener(@Nullable zzay zzayVar) throws RemoteException;

    void setOnMyLocationChangeListener(@Nullable zzba zzbaVar) throws RemoteException;

    void setOnMyLocationClickListener(@Nullable zzbc zzbcVar) throws RemoteException;

    void setOnPoiClickListener(@Nullable zzbe zzbeVar) throws RemoteException;

    void setOnPolygonClickListener(@Nullable zzbg zzbgVar) throws RemoteException;

    void setOnPolylineClickListener(@Nullable zzbi zzbiVar) throws RemoteException;

    void setPadding(int i, int i2, int i3, int i4) throws RemoteException;

    void setTrafficEnabled(boolean z) throws RemoteException;

    void setWatermarkEnabled(boolean z) throws RemoteException;

    void snapshot(zzbv zzbvVar, @Nullable IObjectWrapper iObjectWrapper) throws RemoteException;

    void snapshotForTest(zzbv zzbvVar) throws RemoteException;

    void stopAnimation() throws RemoteException;

    boolean useViewLifecycleWhenInFragment() throws RemoteException;
}
