package dev.cat.mahmoudelbaz.vaccination;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mahmoudelbaz on 1/18/18.
 */

public class VaccineAdapter extends BaseAdapter{

    private final Context context;
    private ArrayList<Vaccine_item> feedItems;
    private ArrayList<Vaccine_item> tempItems;

    public VaccineAdapter(Context cx, ArrayList<Vaccine_item> feedItems) {
        this.context = cx;
        this.feedItems = feedItems;
        this.tempItems = feedItems;
    }


    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Vaccine_item getItem(int i) {
        return feedItems.get(i);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VaccineAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater li = LayoutInflater.from(context);
            convertView = li.inflate(R.layout.vaccine_item, parent, false);
            viewHolder = new VaccineAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (VaccineAdapter.ViewHolder) convertView.getTag();
        }

        Vaccine_item feedItem = getItem(position);
        if (viewHolder != null) {
            viewHolder.setItem(feedItem);
        }

        return convertView;
    }



    class ViewHolder {

        private TextView nameView;
        private ImageView imageView;

        public ViewHolder(View convertView) {
            nameView = (TextView) convertView.findViewById(R.id.vaccineTitle);
            imageView = (ImageView) convertView.findViewById(R.id.imageVaccine);
            convertView.setTag(this);
        }

        void setItem(Vaccine_item vaccine) {
            nameView.setText(Html.fromHtml(vaccine.getName()));
            nameView.setTextColor(vaccine.getColorId());
            Picasso.with(context).load(vaccine.getImage()).fit().centerCrop().into(imageView);

        }
    }

}

