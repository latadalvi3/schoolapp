package implant.lata.com.dumetschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductDetail extends BaseActivity {
    public static final String productToShowKey = "product_to_show";
    ProductVo productToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product_detail);
        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.activity_product_detail, (ViewGroup) findViewById(R.id.container));
        Intent intent = getIntent();
        String stringObject = intent.getStringExtra(ProductDetail.productToShowKey);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(stringObject);
            productToShow = new ProductVo(jsonObject);
            updateListHomeData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        actionBar.setTitle("Products");
    }

    private void updateListHomeData() {
        ArrayList<Object> listToShow = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.lv_productDetail);

        if (productToShow == null) {
            listToShow.add("No Home data available emptys");
        }  else {
            listToShow.add(productToShow);
        }
        listView.setAdapter(new ProductDetailAdapter(this, listToShow, this));
    }
}
