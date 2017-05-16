package implant.lata.com.dumetschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Category extends AppCompatActivity {
    String appNameKey = "app_name";
    String appName = "DUMET";
    String appNameOneKey = "app_last_name";
    String appLasttName = "school";
    TextView txtv1, txtv2;
    Button ok;
    boolean[] checkedItems;

    ArrayList<CatVo> responceVoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        callGetCat();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            appName = extras.getString(appNameKey);
            appNameOneKey = extras.getString(appNameOneKey);

        }
        txtv1 = (TextView) findViewById(R.id.textView2);
        txtv1.setText(appName);

        txtv2 = (TextView)findViewById(R.id.textView3);
        txtv2.setText(appLasttName);


        ok = (Button) findViewById(R.id.button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, Home.class);
                startActivity(intent);

            }
        });
        

    }

    private void callGetCat() {
        String url = getString(R.string.api_base_address)+"api/productcategory";
        Log.v("LataUrl", url);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    ArrayList<CatVo> responceVoArrayList = new ArrayList<>();

                    @Override
                    public void onResponse(String response) {
                        // make responceVoArrayList empty for new result
                        Log.v("Lata","onResponse ");
                        responceVoArrayList.clear();
                        Log.v("Lata","Responce is: " + response);
                        try {
                            // convert json to array and use for loop to access each category
                            JSONArray jsonArray = new JSONArray(response);
                            for (int index = 0; index < jsonArray.length(); index++) {
                                JSONObject obj = jsonArray.getJSONObject(index);
                                Log.d("Original object", obj.toString());
                                // parse json object to CarVo
                                CatVo cat = new CatVo(obj);
                                Log.d("Converted object", cat.toString());
                                // add car object in responceVoArrayList
                                responceVoArrayList.add(cat);
                            }
                        } catch (Throwable t) {
                            Log.e("Lata", "Could not parse malformed JSON:" + t.toString());
                        }
                        updateListData(responceVoArrayList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Lata","That didn't work! "+error.toString());
                updateListData(null);
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void updateListData(ArrayList<CatVo> responceVoArrayList) {
        this.responceVoArrayList = responceVoArrayList;
        ArrayList<Object> listToShow = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.lv_cat_name);

        if (responceVoArrayList == null) {
            listToShow.add("No Category data available");
        } else if (responceVoArrayList.isEmpty()) {
            listToShow.add("No Category data available");
        } else {
            checkedItems = new boolean[responceVoArrayList.size()];
            for (int index = 0; index < responceVoArrayList.size(); index++) {
                listToShow.add(responceVoArrayList.get(index));
            }
        }
        listView.setAdapter(new CategoryAdapter(this, listToShow, this));
    }
}
