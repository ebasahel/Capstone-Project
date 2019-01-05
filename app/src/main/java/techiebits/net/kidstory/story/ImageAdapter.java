package techiebits.net.kidstory.story;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.google.firebase.storage.StorageReference;
import techiebits.net.kidstory.util.GlideApp;

import java.util.List;

public class ImageAdapter  extends PagerAdapter {
    Context context;
    private List<StorageReference> storyImages;

    ImageAdapter(Context context,List<StorageReference> GalImages){
        this.context=context;
        storyImages = GalImages;
    }
    @Override
    public int getCount() {
        return storyImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view,@NonNull Object object) {
        return view == ((ImageView) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        GlideApp.with(context)
                .load(storyImages.get(position))
                .into(imageView);
        ((ViewPager) container).addView(imageView, params);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position,@NonNull Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
