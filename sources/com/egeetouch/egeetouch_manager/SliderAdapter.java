package com.egeetouch.egeetouch_manager;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;
/* loaded from: classes.dex */
public class SliderAdapter extends RecyclerView.Adapter<SliderViewHolder> {
    private Runnable runnable = new Runnable() { // from class: com.egeetouch.egeetouch_manager.SliderAdapter.3
        @Override // java.lang.Runnable
        public void run() {
            SliderAdapter.this.sliderItems.addAll(SliderAdapter.this.sliderItems);
            SliderAdapter.this.notifyDataSetChanged();
        }
    };
    private List<String> sliderItems;
    private List<String> sliderUrl;
    private ViewPager2 viewPager2;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SliderAdapter(List<String> list, List<String> list2, ViewPager2 viewPager2) {
        this.sliderItems = list;
        this.sliderUrl = list2;
        this.viewPager2 = viewPager2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SliderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SliderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.slide_item_container, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final SliderViewHolder sliderViewHolder, final int i) {
        Picasso.get().load(this.sliderItems.get(i)).into(sliderViewHolder.imageView, new Callback() { // from class: com.egeetouch.egeetouch_manager.SliderAdapter.1
            @Override // com.squareup.picasso.Callback
            public void onError(Exception exc) {
            }

            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
                sliderViewHolder.shimmer.setVisibility(8);
                sliderViewHolder.imageView.setVisibility(0);
            }
        });
        if (i == this.sliderItems.size() - 2) {
            this.viewPager2.post(this.runnable);
        }
        sliderViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.SliderAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse((String) SliderAdapter.this.sliderUrl.get(i % SliderAdapter.this.sliderUrl.size())));
                MainActivity.context.startActivity(intent);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.sliderItems.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class SliderViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView imageView;
        private ShimmerFrameLayout shimmer;

        public SliderViewHolder(View view) {
            super(view);
            this.imageView = (RoundedImageView) view.findViewById(R.id.imageSlide);
            this.shimmer = (ShimmerFrameLayout) view.findViewById(R.id.imgLoad);
            this.imageView.setVisibility(8);
            this.shimmer.setVisibility(0);
        }

        void setImage(SliderItem sliderItem) {
            this.imageView.setImageResource(sliderItem.getImage());
        }
    }
}
