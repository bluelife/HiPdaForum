package forum.org.hipda.utils;

import android.content.Context;
import android.util.TypedValue;

import java.util.HashMap;

import forum.org.hipda.R;

/**
 * Created by slomka.jin on 2015/7/17.
 */
public class ColorUtils {

    private static HashMap<Integer, Integer> COLOR_IDS = new HashMap<>();

    public static void clear() {
        COLOR_IDS.clear();
    }

    public static int getColorIdByAttr(Context ctx, int attrId) {
        if (COLOR_IDS.containsKey(attrId))
            return COLOR_IDS.get(attrId);

        TypedValue typedValue = new TypedValue();
        ctx.getTheme().resolveAttribute(attrId, typedValue, true);
        int colorId = ctx.getResources().getColor(typedValue.resourceId);
        COLOR_IDS.put(attrId, colorId);

        return colorId;
    }

    public static int getDefaultTextColor(Context ctx) {
        return getColorIdByAttr(ctx, android.R.attr.textColorPrimary);
    }

    public static int getColorAccent(Context ctx) {
        return getColorIdByAttr(ctx, R.attr.colorAccent);
    }

    public static int getColorPrimary(Context ctx) {
        return getColorIdByAttr(ctx, R.attr.colorPrimary);
    }

    public static int getListBackgroundColor(Context ctx) {
        return getColorIdByAttr(ctx, R.attr.list_item_background);
    }

}