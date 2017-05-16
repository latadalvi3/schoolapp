package implant.lata.com.dumetschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Blog extends BaseActivity {
    static String readMoreKey = "readMoreToEdit";
    public static final String objectToShowKey = "objectToShowKey";
    int requestCode;
    Item objectToShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_blog);
        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.activity_blog, (ViewGroup) findViewById(R.id.container));
        setTitle("Blog");
        Intent intent = getIntent();
        String stringObject = intent.getStringExtra(Blog.objectToShowKey);

        try {
            JSONObject jsonObject = new JSONObject(stringObject);
            objectToShow = new Item(jsonObject);
            updateListHomeData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        actionBar.setTitle("Blog");
    }

    private void updateListHomeData() {
        ArrayList<Object> listToShow = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.lv_detaolPostListView);
        if (objectToShow == null) {
            listToShow.add("No Home data available emptys");
        }  else {
                listToShow.add(objectToShow);
        }
        listView.setAdapter(new DetailPostAdapter(this, listToShow, this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "SUBJECT");
            intent.putExtra(Intent.EXTRA_TEXT, "TEXT");
            String asd = getString(R.string.api_blog_base_address)+objectToShow.getSlug();
            intent.putExtra(Intent.EXTRA_TEXT, asd);
            startActivity(Intent.createChooser(intent, "Share"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
