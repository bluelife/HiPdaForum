package forum.org.hipda.data.entity;

import android.util.SparseArray;

import forum.org.hipda.data.style.TextStyle;

/**
 * Created by slomka.jin on 2015/7/16.
 */
public class TextStyleHolder {
    private SparseArray<TextStyle> textStyles = new SparseArray<>();

    public void addLevel(int level) {
        if (textStyles.get(level - 1) != null)
            textStyles.put(level, textStyles.get(level - 1).newInstance());
        else
            textStyles.put(level, new TextStyle());
    }

    public void removeLevel(int level) {
        textStyles.remove(level);
    }

    public TextStyle getTextStyle(int level) {
        return textStyles.get(level);
    }

    public void addStyle(int level, String style) {
        if (level > 0 && textStyles.get(level) != null)
            textStyles.get(level).addStyle(style);
    }

    public void setColor(int level, String color) {
        if (level > 0 && textStyles.get(level) != null)
            textStyles.get(level).setColor(color);
    }
}
