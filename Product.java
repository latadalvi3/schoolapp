package implant.lata.com.dumetschool;

import android.content.Intent;
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

public class Product extends BaseActivity implements ProductOperationDelegate {
    ArrayList<ProductVo> responceVoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar.setTitle("Products");
//        setContentView(R.layout.activity_product);
        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.activity_product, (ViewGroup) findViewById(R.id.container));
        navItemIndex = 1;
        navigationView.getMenu().getItem(0).setChecked(false);
        navigationView.getMenu().getItem(1).setChecked(true);
        CURRENT_TAG = TAG_PRODUCT;
       // setTitle("Product");
        callGetProduct();
        
    }

    private void callGetProduct() {
        String url = "http://www.dumetschool.com/api/product";
        Log.v("LataUrl", url);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    ArrayList<ProductVo> responceVoArrayList = new ArrayList<>();

                    @Override
                    public void onResponse(String response) {
                        // make responceVoArrayList empty for new result
                        Log.v("Lata", "onResponse ");
                        responceVoArrayList.clear();
                        Log.v("Lata", "Responce is: " + response);
                        try {
                            // convert json to array and use for loop to access each car
                            JSONArray jsonArray = new JSONArray(response);
                            for (int index = 0; index < jsonArray.length(); index++) {
                                JSONObject obj = jsonArray.getJSONObject(index);
                                Log.d("Original object", obj.toString());
                                ProductVo productVo = new ProductVo(obj);
                                Log.d("Converted object", productVo.toString());
                                responceVoArrayList.add(productVo);
                            }
                        } catch (Throwable t) {
                            Log.e("Lata", "Could not parse malformed JSON:" + t.toString());
                        }
                        updateListProductData(responceVoArrayList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Lata", "That didn't work! " + error.toString());
                updateListProductData(null);
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void updateListProductData(ArrayList<ProductVo> responceVoArrayList) {
        this.responceVoArrayList = responceVoArrayList;
        ArrayList<Object> listToShow = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.lv_product);
        //checkedItems = new boolean[responceVoArrayList.size()];

        if (responceVoArrayList == null) {
            listToShow.add("No product data available emptys");
        } else if (responceVoArrayList.isEmpty()) {
            listToShow.add("No product data available lata");
        } else {
            for (int index = 0; index < responceVoArrayList.size(); index++) {
                listToShow.add(responceVoArrayList.get(index));
            }
        }
        listView.setAdapter(new ProductAdapter(this, listToShow, this));
    }

    @Override
    public void claimThisDealTapped(ProductVo productVo) {
        Intent intent = new Intent(Product.this, ProductDetail.class);
        intent.putExtra(ProductDetail.productToShowKey, productVo.toJsonObject().toString());
        startActivity(intent);
    }
}


