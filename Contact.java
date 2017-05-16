package implant.lata.com.dumetschool;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class Contact extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contact);
//         setTitle("Contact");
//        setContentView(R.layout.activity_product);
        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.activity_contact, (ViewGroup) findViewById(R.id.container));
        actionBar.setTitle("Contact");
        navItemIndex = 2;
        navigationView.getMenu().getItem(0).setChecked(false);
        navigationView.getMenu().getItem(2).setChecked(true);
        CURRENT_TAG = TAG_CONTACT;
    }
}
