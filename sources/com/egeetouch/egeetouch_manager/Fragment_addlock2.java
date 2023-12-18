package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
/* loaded from: classes.dex */
public class Fragment_addlock2 extends Fragment {
    private static final int RECOVERY_REQUEST = 3;
    public static ListView listview;
    private Handler handle_image;
    ImageView imageView;
    ImageView ip45_imageView;
    LinearLayout ll_ip45;
    private Menu menu;
    View rootView;
    TextView tv_lotoSerial;
    private boolean toggle_image = false;
    Runnable run = new Runnable() { // from class: com.egeetouch.egeetouch_manager.Fragment_addlock2.1
        @Override // java.lang.Runnable
        public void run() {
            System.out.println("Hello checking the runnable !!!");
            if (BLEService.selected_lock_model.equals("GT1000")) {
                if (Fragment_addlock2.this.imageView != null) {
                    if (Fragment_addlock2.this.toggle_image) {
                        Fragment_addlock2.this.toggle_image = false;
                        Fragment_addlock2.this.imageView.setImageResource(R.drawable.tvl_led_off);
                    } else {
                        Fragment_addlock2.this.toggle_image = true;
                        Fragment_addlock2.this.imageView.setImageResource(R.drawable.tvl_led_on);
                    }
                }
            } else if (BLEService.selected_lock_model.equals("GT1001")) {
                if (Fragment_addlock2.this.imageView != null) {
                    if (Fragment_addlock2.this.toggle_image) {
                        Fragment_addlock2.this.toggle_image = false;
                        Fragment_addlock2.this.imageView.setImageResource(R.drawable.tvl_led_off);
                    } else {
                        Fragment_addlock2.this.toggle_image = true;
                        Fragment_addlock2.this.imageView.setImageResource(R.drawable.tvl_led_on);
                    }
                }
            } else if (BLEService.selected_lock_model.equals("GT2000")) {
                ImageView imageView = (ImageView) Fragment_addlock2.this.rootView.findViewById(R.id.imageView_power_on);
                if (imageView != null) {
                    imageView.setImageResource(R.drawable.on_padlock);
                }
            } else if (BLEService.selected_lock_model.equals("GT2002")) {
                if (Fragment_addlock2.this.imageView != null) {
                    if (Fragment_addlock2.this.toggle_image) {
                        Fragment_addlock2.this.toggle_image = false;
                        Fragment_addlock2.this.imageView.setImageResource(R.drawable.padlock_led_off);
                    } else {
                        Fragment_addlock2.this.toggle_image = true;
                        Fragment_addlock2.this.imageView.setImageResource(R.drawable.padlock_led_on);
                    }
                }
            } else if (BLEService.selected_lock_model.equals("GT2003")) {
                if (Fragment_addlock2.this.imageView != null) {
                    if (Fragment_addlock2.this.toggle_image) {
                        Fragment_addlock2.this.toggle_image = false;
                        Fragment_addlock2.this.imageView.setImageResource(R.drawable.padlock_led_off);
                    } else {
                        Fragment_addlock2.this.toggle_image = true;
                        Fragment_addlock2.this.imageView.setImageResource(R.drawable.padlock_led_on);
                    }
                }
            } else if (BLEService.selected_lock_model.equals("GT3000")) {
                ImageView imageView2 = (ImageView) Fragment_addlock2.this.rootView.findViewById(R.id.imageView_power_on);
                if (imageView2 != null) {
                    imageView2.setImageResource(R.drawable.on_luggagelock);
                }
            } else if (BLEService.selected_lock_model.equals("GT3002")) {
                ImageView imageView3 = (ImageView) Fragment_addlock2.this.rootView.findViewById(R.id.imageView_addlock_image);
                if (imageView3 != null) {
                    if (Fragment_addlock2.this.toggle_image) {
                        Fragment_addlock2.this.toggle_image = false;
                        imageView3.setImageResource(R.drawable.lugg_led_off);
                    } else {
                        Fragment_addlock2.this.toggle_image = true;
                        imageView3.setImageResource(R.drawable.lugg_led_on);
                    }
                }
            } else if (BLEService.selected_lock_model.equals("GT2100") || BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                TextView textView = (TextView) Fragment_addlock2.this.rootView.findViewById(R.id.textView_serialNumber);
                LinearLayout linearLayout = (LinearLayout) Fragment_addlock2.this.rootView.findViewById(R.id.ll_ip45);
                Fragment_addlock2 fragment_addlock2 = Fragment_addlock2.this;
                fragment_addlock2.ip45_imageView = (ImageView) fragment_addlock2.rootView.findViewById(R.id.imageView_addlock_ip45);
                if (BLEService.selected_lock_model.equals("GT2500")) {
                    View findViewById = Fragment_addlock2.this.rootView.findViewById(R.id.view_line);
                    ((LinearLayout) Fragment_addlock2.this.rootView.findViewById(R.id.ll_3rd_gen)).setVisibility(8);
                    findViewById.setVisibility(8);
                    ((TextView) Fragment_addlock2.this.rootView.findViewById(R.id.tv_lock_gen)).setText("5th Gen Padlock");
                } else if (BLEService.selected_lock_model.equals("GT2550")) {
                    View findViewById2 = Fragment_addlock2.this.rootView.findViewById(R.id.view_line);
                    ((LinearLayout) Fragment_addlock2.this.rootView.findViewById(R.id.ll_3rd_gen)).setVisibility(8);
                    findViewById2.setVisibility(8);
                    ((TextView) Fragment_addlock2.this.rootView.findViewById(R.id.tv_lock_gen)).setText(Fragment_addlock2.this.getContext().getResources().getString(R.string.smart_anticut));
                }
                try {
                    linearLayout.setVisibility(0);
                    Fragment_addlock2.this.imageView.setVisibility(8);
                    if (!BLEService.parsedIp45SerialNumber.equals("")) {
                        textView.setText(Fragment_addlock2.this.rootView.getResources().getString(R.string.serial_number) + BLEService.parsedIp45SerialNumber);
                    } else {
                        textView.setText("");
                    }
                    if (Fragment_addlock2.this.ip45_imageView != null) {
                        if (Fragment_addlock2.this.toggle_image) {
                            Fragment_addlock2.this.toggle_image = false;
                            Fragment_addlock2.this.ip45_imageView.setImageResource(R.drawable.gt2100_led_off);
                        } else {
                            Fragment_addlock2.this.toggle_image = true;
                            Fragment_addlock2.this.ip45_imageView.setImageResource(R.drawable.gt2100_led_on);
                        }
                    }
                } catch (Exception unused) {
                }
            } else if (BLEService.selected_lock_model.equals("GT5100")) {
                try {
                    if (!BLEService.parsedIp45SerialNumber.equals("")) {
                        Fragment_addlock2.this.tv_lotoSerial.setText(Fragment_addlock2.this.rootView.getResources().getString(R.string.serial_number) + BLEService.parsedIp45SerialNumber);
                    } else {
                        Fragment_addlock2.this.tv_lotoSerial.setText("");
                    }
                    if (Fragment_addlock2.this.imageView != null) {
                        if (Fragment_addlock2.this.toggle_image) {
                            Fragment_addlock2.this.toggle_image = false;
                            Fragment_addlock2.this.imageView.setImageResource(R.drawable.mini_padlock_led_off);
                        } else {
                            Fragment_addlock2.this.toggle_image = true;
                            Fragment_addlock2.this.imageView.setImageResource(R.drawable.mini_padlock_led_on);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Hello checking the tvSerial Exce :" + e.toString());
                    Fragment_addlock2 fragment_addlock22 = Fragment_addlock2.this;
                    fragment_addlock22.tv_lotoSerial = (TextView) fragment_addlock22.rootView.findViewById(R.id.textView_serialNumber_loto);
                }
            }
            Fragment_addlock2.this.handle_image.postDelayed(this, 1000L);
        }
    };

    public static Fragment_addlock2 newInstance() {
        return new Fragment_addlock2();
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("HEY Fragment_addLock2 onCreate called for ");
        System.out.println("HEY clearing parsedIp45SerialNumber");
        BLEService.parsedIp45SerialNumber = "";
        MainActivity.isAddingLockMode = true;
        BLEService.address_blacklist.clear();
        BLEService.service_disconnect = false;
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        System.out.println("HEY Fragment_addLock2 getting destroyed");
        BLEService.address_blacklist.clear();
        BLEService.service_disconnect = false;
        MainActivity.isAddingLockMode = false;
        this.handle_image.removeCallbacks(this.run);
        MainActivity.scanning_new_lock = false;
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        if (is_BLE_device().booleanValue()) {
            MainActivity.stop_scanning = true;
        }
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String str;
        if (menuItem.getItemId() == R.id.action_button_tutorial) {
            if (BLEService.selected_lock_model.equals("GT1000")) {
                String str2 = Helper_PhoneDetails.phonelangauge().equals("ja") ? "https://youtu.be/3YRorE8YkB4" : "https://youtu.be/XMxmr5Bi1OU";
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str2));
                startActivity(intent);
                return true;
            }
            if (BLEService.selected_lock_model.equals("GT1001")) {
                str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video" : "http://www.egeetouch.com/support/video";
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse(str));
                startActivity(intent2);
                return true;
            } else if (BLEService.selected_lock_model.equals("GT2000")) {
                String str3 = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-padlock" : "https://youtu.be/WGyX6n8fJKk";
                Intent intent3 = new Intent("android.intent.action.VIEW");
                intent3.setData(Uri.parse(str3));
                startActivity(intent3);
                return true;
            } else if (BLEService.selected_lock_model.equals("GT2002")) {
                str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video" : "http://www.egeetouch.com/support/video";
                Intent intent4 = new Intent("android.intent.action.VIEW");
                intent4.setData(Uri.parse(str));
                startActivity(intent4);
                return true;
            } else if (BLEService.selected_lock_model.equals("GT2003")) {
                str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video" : "http://www.egeetouch.com/support/video";
                Intent intent5 = new Intent("android.intent.action.VIEW");
                intent5.setData(Uri.parse(str));
                startActivity(intent5);
                return true;
            } else if (BLEService.selected_lock_model.equals("GT3000")) {
                str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video" : "http://www.egeetouch.com/support/video";
                Intent intent6 = new Intent("android.intent.action.VIEW");
                intent6.setData(Uri.parse(str));
                startActivity(intent6);
                return true;
            } else if (BLEService.selected_lock_model.equals("GT3002")) {
                String str4 = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video" : "https://youtu.be/0ttfg_jB4yo";
                Intent intent7 = new Intent("android.intent.action.VIEW");
                intent7.setData(Uri.parse(str4));
                startActivity(intent7);
                return true;
            } else {
                return true;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        System.out.println("HEY Fragment_addLock2 onCreateView called" + BLEService.selected_lock_model);
        if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT1001") || BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2100") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT3002") || BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
            View inflate = layoutInflater.inflate(R.layout.fragment_choose_type2_test, viewGroup, false);
            this.rootView = inflate;
            this.imageView = (ImageView) inflate.findViewById(R.id.imageView_addlock_image);
            System.out.println("Hello checking the fragment_addLock2_test!");
        } else if (BLEService.selected_lock_model.equals("GT2000") || BLEService.selected_lock_model.equals("GT3000")) {
            this.rootView = layoutInflater.inflate(R.layout.fragment_choose_type2_nfc, viewGroup, false);
            System.out.println("Hello checking the fragment_addLock2_nfc!");
        } else if (BLEService.selected_lock_model.equals("GT5100")) {
            View inflate2 = layoutInflater.inflate(R.layout.fragment_choose_type_loto, viewGroup, false);
            this.rootView = inflate2;
            this.tv_lotoSerial = (TextView) inflate2.findViewById(R.id.textView_serialNumber_loto);
            this.imageView = (ImageView) this.rootView.findViewById(R.id.imageView_addlock_image);
        }
        setHasOptionsMenu(true);
        MainActivity.fab.setVisibility(4);
        MainActivity.fab_share.setVisibility(4);
        MainActivity.fab_admin_access_locks.setVisibility(8);
        if (MainActivity.dashboard_listview != null) {
            MainActivity.dashboard_listview.setVisibility(4);
        }
        MainActivity.pullToRefresh.setEnabled(false);
        getActivity().setTitle(getContext().getResources().getString(R.string.scanning));
        Handler handler = new Handler();
        this.handle_image = handler;
        handler.post(this.run);
        return this.rootView;
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private Boolean is_BLE_device() {
        if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT1001") || BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2100") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT3002") || BLEService.selected_lock_model.equals("GT5100") || BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
            return true;
        }
        return false;
    }
}
