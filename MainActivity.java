package implant.lata.com.dumetschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String appNameKey = "app_name";
    String appFirstName = "DOMET";
    String appNameOneKey = "app_last_name";
    String appLasttName = "school";
    TextView t1,t2,DemoAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView) findViewById(R.id.textView14);
        t2 = (TextView) findViewById(R.id.textView);

    //}
    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_2);
    t1.setAnimation(animation);
        t2.setAnimation(animation);

    animation.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //When you want delay some time
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //When animate finish, load main activity
            finish();
            Intent intent = new Intent(MainActivity.this, Category.class);
            intent.putExtra(appNameKey, appFirstName);
            intent.putExtra(appNameOneKey,appLasttName);
            startActivity(new Intent(getApplicationContext(),Category.class));
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });
}
}
