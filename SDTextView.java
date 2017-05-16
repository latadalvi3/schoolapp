package implant.lata.com.dumetschool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Sami on 4/12/2017.
 */

public class SDTextView extends TextView {

    public SDTextView(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/Avenir-Regular.ttf");
        this.setTypeface(face);
        this.setTextColor(Color.parseColor("#030303"));
        this.setLinkTextColor(Color.parseColor("#2962ff"));
        this.setAutoLinkMask(Linkify.ALL);
    }

    public SDTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/Avenir-Regular.ttf");
        this.setTypeface(face);
        this.setTextColor(Color.parseColor("#030303"));
        this.setLinkTextColor(Color.parseColor("#2962ff"));
        this.setAutoLinkMask(Linkify.ALL);
    }

    public SDTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/Avenir-Regular.ttf");
        this.setTypeface(face);
        this.setTextColor(Color.parseColor("#030303"));
        this.setLinkTextColor(Color.parseColor("#2962ff"));
        this.setAutoLinkMask(Linkify.ALL);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
