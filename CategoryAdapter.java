package implant.lata.com.dumetschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sami on 4/6/2017.
 */

public class CategoryAdapter extends BaseAdapter {
    private ArrayList<Object> catArray;
    private LayoutInflater inflater;
    private static final int Type_Cat = 0;
    private static final int TYPE_DIVIDER = 1;
    Context context;

    public CategoryAdapter(Context context, ArrayList<Object> catArray, Category category){
        this.catArray = catArray;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public CategoryAdapter(Context context, ArrayList<Object> catArray, Settings settings){
        this.catArray = catArray;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return catArray.size();
    }

    @Override
    public Object getItem(int position) {
        return catArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if(convertView == null){
            switch (type){
                case Type_Cat:
                    convertView = inflater.inflate(R.layout.category_single_item, parent, false);
                    break;
                case TYPE_DIVIDER:
                    convertView = inflater.inflate(R.layout.item_header, parent, false);
                    break;
            }
        }
        switch (type){
            case Type_Cat:
                final CatVo catVo = (CatVo) getItem(position);
                CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
                checkBox.setText(catVo.getTitle());
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
    public int getItemViewType(int position) {
        if(getItem(position) instanceof CatVo){
            return Type_Cat;
        }
        return TYPE_DIVIDER;

    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEnabled(int position) {
        return (getItemViewType(position)==Type_Cat);
    }
}
