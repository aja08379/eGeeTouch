package com.egeetouch.egeetouch_manager;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/* loaded from: classes.dex */
public class NewsDynamic extends AppCompatActivity {
    private ChildEventListener childEventListener;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
    ListView listview;
    private DatabaseReference myref;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_news_dynamic);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setTitle(R.string.latest_news_updates);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.listview = (ListView) findViewById(R.id.listview1);
        TextView textView = (TextView) findViewById(R.id.no_connection);
        this.myref = this.database.getReference("news");
        if (isConnected()) {
            this.listview.setVisibility(0);
            textView.setVisibility(8);
        } else {
            this.listview.setVisibility(8);
            textView.setVisibility(0);
        }
        this.childEventListener = new AnonymousClass1();
        System.out.println("PRINT CHECK LIST :" + this.list);
        this.myref.addChildEventListener(this.childEventListener);
    }

    /* renamed from: com.egeetouch.egeetouch_manager.NewsDynamic$1  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements ChildEventListener {
        @Override // com.google.firebase.database.ChildEventListener
        public void onCancelled(DatabaseError databaseError) {
        }

        @Override // com.google.firebase.database.ChildEventListener
        public void onChildMoved(DataSnapshot dataSnapshot, String str) {
        }

        AnonymousClass1() {
        }

        @Override // com.google.firebase.database.ChildEventListener
        public void onChildAdded(DataSnapshot dataSnapshot, String str) {
            GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() { // from class: com.egeetouch.egeetouch_manager.NewsDynamic.1.1
            };
            dataSnapshot.getKey();
            HashMap hashMap = (HashMap) dataSnapshot.getValue(genericTypeIndicator);
            NewsDynamic.this.myref.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.NewsDynamic.1.2
                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot2) {
                    NewsDynamic.this.list = new ArrayList();
                    try {
                        GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator2 = new GenericTypeIndicator<HashMap<String, Object>>() { // from class: com.egeetouch.egeetouch_manager.NewsDynamic.1.2.1
                        };
                        for (DataSnapshot dataSnapshot3 : dataSnapshot2.getChildren()) {
                            NewsDynamic.this.list.add((HashMap) dataSnapshot3.getValue(genericTypeIndicator2));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Collections.reverse(NewsDynamic.this.list);
                    NewsDynamic.this.listview.setAdapter((ListAdapter) new ListViewAdapter(NewsDynamic.this.list));
                    NewsDynamic.this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.egeetouch.egeetouch_manager.NewsDynamic.1.2.2
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                            String obj = ((HashMap) NewsDynamic.this.list.get(i)).get("link").toString();
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setData(Uri.parse(obj));
                            NewsDynamic.this.startActivity(intent);
                        }
                    });
                }
            });
        }

        @Override // com.google.firebase.database.ChildEventListener
        public void onChildChanged(DataSnapshot dataSnapshot, String str) {
            NewsDynamic.this.myref.removeEventListener(NewsDynamic.this.childEventListener);
            NewsDynamic.this.list.clear();
            NewsDynamic.this.myref.addChildEventListener(NewsDynamic.this.childEventListener);
        }

        @Override // com.google.firebase.database.ChildEventListener
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            NewsDynamic.this.myref.removeEventListener(NewsDynamic.this.childEventListener);
            NewsDynamic.this.list.clear();
            NewsDynamic.this.myref.addChildEventListener(NewsDynamic.this.childEventListener);
        }
    }

    /* loaded from: classes.dex */
    public class ListViewAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public ListViewAdapter(ArrayList<HashMap<String, Object>> arrayList) {
            this.data = arrayList;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            ArrayList<HashMap<String, Object>> arrayList = this.data;
            if (arrayList == null) {
                return 0;
            }
            return arrayList.size();
        }

        @Override // android.widget.Adapter
        public HashMap<String, Object> getItem(int i) {
            return this.data.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            System.out.println("This is view");
            LayoutInflater layoutInflater = (LayoutInflater) NewsDynamic.this.getBaseContext().getSystemService("layout_inflater");
            if (view == null) {
                view = layoutInflater.inflate(R.layout.listview_news, (ViewGroup) null);
            }
            final ImageView imageView = (ImageView) view.findViewById(R.id.imgView);
            final ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) view.findViewById(R.id.imgLoad);
            imageView.setVisibility(8);
            shimmerFrameLayout.setVisibility(0);
            ((TextView) view.findViewById(R.id.judul)).setText(this.data.get(i).get("title").toString());
            ((TextView) view.findViewById(R.id.description)).setText(this.data.get(i).get("desc").toString());
            Picasso.get().load(this.data.get(i).get(MessengerShareContentUtility.MEDIA_IMAGE).toString()).into(imageView, new Callback() { // from class: com.egeetouch.egeetouch_manager.NewsDynamic.ListViewAdapter.1
                @Override // com.squareup.picasso.Callback
                public void onError(Exception exc) {
                }

                @Override // com.squareup.picasso.Callback
                public void onSuccess() {
                    shimmerFrameLayout.setVisibility(8);
                    imageView.setVisibility(0);
                }
            });
            return view;
        }
    }

    public void btn_close(View view) {
        finish();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return true;
    }

    public boolean isConnected() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
