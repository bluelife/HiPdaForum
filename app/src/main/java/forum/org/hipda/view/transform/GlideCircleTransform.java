package forum.org.hipda.view.transform;

/**
 * Created by slomka.jin on 2015/7/7.
 */
import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import forum.org.hipda.utils.ImageUtils;

public class GlideCircleTransform extends BitmapTransformation {
    public GlideCircleTransform(Context context) {
        super(context);
    }
    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {
        return ImageUtils.getCircularBitmapImage(source);
    }
    @Override
    public String getId() {
        return "Glide_Circle_Transformation";
    }
}