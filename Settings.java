package implant.lata.com.dumetschool;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Settings extends BaseActivity {
    ArrayList<CatVo> responceVoArrayList;
    boolean[] checkedItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings);
        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.activity_settings, (ViewGroup) findViewById(R.id.container));
        actionBar.setTitle("Settings");
        navItemIndex = 3;
        navigationView.getMenu().getItem(0).setChecked(false);
        navigationView.getMenu().getItem(3).setChecked(true);
        CURRENT_TAG = TAG_SETTING;
        callGetCat();
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
        ListView listView = (ListView) findViewById(R.id.lv_addCat);

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


