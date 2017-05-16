package implant.lata.com.dumetschool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Sami on 4/12/2017.
 */

public class SDButton extends Button {
    public SDButton(Context context) {
        super(context);
        doBasicSetup(context);
    }


    public SDButton(Context context, AttributeSet attrs) {
        super(context, attrs);
       doBasicSetup(context);
    }

    public SDButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        doBasicSetup(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    void doBasicSetup(Context context) {
        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/Avenir-Regular.ttf");
        this.setTypeface(face);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setLetterSpacing(0.5f);
        }
        this.setBackgroundColor(Color.parseColor("#00c853"));
        this.setPadding(12, 6, 12, 6);
        this.setShadowLayer(0, 2, 5, Color.argb(26, 0, 0, 0));
        this.setTextColor(Color.parseColor("#ffffff"));
        this.setCursorVisible(true);
    }
}
