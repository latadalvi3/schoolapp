package implant.lata.com.dumetschool;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sami on 4/8/2017.
 */

public class DetailPostAdapter extends BaseAdapter {
    private ArrayList<Object> homeArray;
    private LayoutInflater inflater;
    private static final int Type_Homeact = 0;
    private static final int TYPE_DIVIDER = 1;
    private static final int textViewMaxLines = 5;
    private static final String seeMoreButtonTitle = "Read more...";
    private static final String showLessButtonTitle = "Read less...";

    Context context;


    public DetailPostAdapter(Context context, ArrayList<Object> homeArray, Blog blog){
        this.homeArray = homeArray;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return homeArray.size();
    }

    @Override
    public Object getItem(int position) {
        return homeArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if(convertView == null) {
            switch (type) {
                case Type_Homeact:
                    convertView = inflater.inflate(R.layout.home_single, parent, false);
                    break;
                case TYPE_DIVIDER:
                    convertView = inflater.inflate(R.layout.item_header, parent, false);
                    break;
            }
        }
        switch (type) {
            case Type_Homeact:
                final Item item = (Item) getItem(position);
                TextView postCat =(TextView) convertView.findViewById(R.id.textView5);
                postCat.setText(item.getUser().getNamaLengkap());

                TextView userName =(TextView) convertView.findViewById(R.id.textView6);
                userName.setText(getShortDateFromDate(item.getCreated()));
                TextView timeLabel = (TextView) convertView.findViewById(R.id.textView9);
                timeLabel.setText(item.getTitle());
                final TextView descriptionLabel = (TextView) convertView.findViewById(R.id.textView10);

                ImageView foto = (ImageView) convertView.findViewById(R.id.imageView3);
                Glide.with(this.context)
                        .load(context.getString(R.string.api_user_image_base_address) + item.getUser().getFoto())
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .override(200, 200)
                        .centerCrop()
                        .into(foto);

                ImageView articleImageView = (ImageView) convertView.findViewById(R.id.imageView4);
                Glide.with(this.context)
                        .load(context.getString(R.string.api_post_image_base_address) + item.getImage())
                        .centerCrop()
                        .into(articleImageView);

                final Button seeMoreButton = (Button) convertView.findViewById(R.id.button3);
                seeMoreButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intent = new Intent(DetailPostAdapter.this, Blog.class);

                    }
                });

                seeMoreButton.setVisibility(View.INVISIBLE);
//                descriptionLabel.setMaxLines(HomeAdapter.textViewMaxLines);
//                seeMoreButton.setText(HomeAdapter.seeMoreButtonTitle);

                // Lata there is some syntax change for android N so we are using this check to find android less than N
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    descriptionLabel.setText(Html.fromHtml(item.getDescription(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    descriptionLabel.setText(Html.fromHtml(item.getDescription()));
                }
                descriptionLabel.setLinksClickable(true);

                break;
            case TYPE_DIVIDER:
                String text = (String) getItem(position);
                TextView headerTextView = (TextView) convertView.findViewById(R.id.headerTextView);
                headerTextView.setText(text);
                break;
        }
        return convertView;
    }

    String getShortDateFromDate(String inputDate) {
        //  we need to parse string date "2013-11-23T23:38:00+0000" from this format yyyy-MM-dd'T'HH:mm:ss.SSSZ
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date date = null;
        try {
            date = dt.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // now we need date in format like 02 October so we are using format "dd MMMM"
        SimpleDateFormat dt1 = new SimpleDateFormat("dd MMMM");
        return dt1.format(date);
    }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position) instanceof Item){
            return Type_Homeact;
        }
        return TYPE_DIVIDER;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEnabled(int position) {
        return (getItemViewType(position) == Type_Homeact);
    }
}

