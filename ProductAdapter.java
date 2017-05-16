package implant.lata.com.dumetschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Sami on 4/7/2017.
 */

public class ProductAdapter extends BaseAdapter {
    private ArrayList<Object> productArray;
    private LayoutInflater inflater;
    private static final int Type_Product = 0;
    private static final int TYPE_DIVIDER = 1;
    private static final String btnTitle = "Clear this deal ";
    Context context;
    ProductOperationDelegate delegate;
    public ProductAdapter(Context context, ArrayList<Object> productArray, Product product){
        this.productArray = productArray;
        this.context = context;
        delegate = product;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productArray.size();
    }

    @Override
    public Object getItem(int position) {
        return productArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case Type_Product:
                    convertView = inflater.inflate(R.layout.product_single, parent, false);
                    break;
                case TYPE_DIVIDER:
                    convertView = inflater.inflate(R.layout.item_header, parent, false);
                    break;
            }
        }
        switch (type) {
            case Type_Product:
                final ProductVo productVo = (ProductVo) getItem(position);
                TextView title = (TextView) convertView.findViewById(R.id.textView7);
                title.setText(productVo.getTitle());

                TextView description = (TextView) convertView.findViewById(R.id.textView11);
                description.setText(productVo.getDescription());

                ImageView productImageView = (ImageView) convertView.findViewById(R.id.iv_product);

                Glide.with(this.context)
                        .load(context.getString(R.string.api_product_image_base_address) + productVo.getImage())
                        .centerCrop()
                        .into(productImageView);

                final Button btn = (Button) convertView.findViewById(R.id.button2);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delegate.claimThisDealTapped(productVo);
                    }
                });

                break;
            case TYPE_DIVIDER:
                String text = (String) getItem(position);
                TextView headerTextView = (TextView) convertView.findViewById(R.id.headerTextView);
                headerTextView.setText(text);
                break;
        }

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position) instanceof ProductVo){
            return Type_Product;
        }
        return TYPE_DIVIDER;
    }

    @Override
    public boolean isEnabled(int position) {
        return (getItemViewType(position)== Type_Product);

    }
}
